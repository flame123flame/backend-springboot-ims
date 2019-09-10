package th.co.baiwa.buckwaframework.accesscontrol.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import th.co.baiwa.buckwaframework.accesscontrol.vo.MenuVo;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;

public class MenuRepositoryImpl implements MenuRepositoryCustom {

	private static final Logger logger = LoggerFactory.getLogger(MenuRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

//	@Override
//	public List<MenuVo> listMenu() {
//		logger.info("call listMenu");
//		StringBuilder sql = new StringBuilder();
//		sql.append(" WITH t1(MENU_ID, parent_id, lvl, root_id, MENU_CODE, MENU_NAME, URL, SORTING_ORDER) AS ( ");
//		sql.append("   SELECT MENU_ID, ");
//		sql.append("          parent_id, ");
//		sql.append("          1 AS lvl, ");
//		sql.append("          MENU_ID AS root_id, ");
//		sql.append("          MENU_CODE, ");
//		sql.append("          MENU_NAME, ");
//		sql.append("          URL, ");
//		sql.append("          SORTING_ORDER ");
//		sql.append("   FROM   ADM_MENU ");
//		sql.append("   WHERE  parent_id IS NULL ");
//		sql.append("   UNION ALL ");
//		sql.append("   SELECT t2.MENU_ID, ");
//		sql.append("          t2.parent_id, ");
//		sql.append("          lvl+1, ");
//		sql.append("          t1.root_id, ");
//		sql.append("          t2.MENU_CODE, ");
//		sql.append("          t2.MENU_NAME,          ");
//		sql.append("          t2.URL, ");
//		sql.append("          t2.SORTING_ORDER ");
//		sql.append("   FROM   ADM_MENU t2, t1 ");
//		sql.append("   WHERE  t2.parent_id = t1.MENU_ID ");
//		sql.append(" ) ");
//		sql.append(" SEARCH DEPTH FIRST BY MENU_ID SET order1 ");
//		sql.append(" SELECT MENU_ID, ");
//		sql.append("        parent_id, ");
//		sql.append("        RPAD('.', (lvl-1)*2, '.') || MENU_ID AS tree, ");
//		sql.append("        lvl, ");
//		sql.append("        root_id, ");
//		sql.append("        MENU_CODE, ");
//		sql.append("        MENU_NAME, ");
//		sql.append("        URL, ");
//		sql.append("        SORTING_ORDER ");
//		sql.append(" FROM t1 ");
//		sql.append(" ORDER BY order1 ");
//
//		List<MenuVo> list = commonJdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(MenuVo.class));
//		return list;
//
//	}

	@Override
	public List<MenuVo> findByRolesAndSubdeptLevel(List<String> roleList, String subdeptLevel) {
		logger.info("findByRolesAndSubdeptLevel");
		
		StringBuilder sql = new StringBuilder();
		List<Object> paramList = new ArrayList<>();
		
		sql.append(" WITH T1 (MENU_ID, PARENT_ID, LVL, ROOT_ID, MENU_CODE, MENU_NAME, URL, SORTING_ORDER, SUBDEPT_LEVEL) ");
		sql.append(" AS ( ");
		sql.append("   SELECT AM.MENU_ID ");
		sql.append("     ,AM.PARENT_ID ");
		sql.append("     ,1 AS LVL ");
		sql.append("     ,AM.MENU_ID AS ROOT_ID ");
		sql.append("     ,AM.MENU_CODE ");
		sql.append("     ,AM.MENU_NAME ");
		sql.append("     ,AM.URL ");
		sql.append("     ,AM.SORTING_ORDER ");
		sql.append("     ,AM.SUBDEPT_LEVEL ");
		sql.append("   FROM ADM_MENU AM ");
		sql.append("   WHERE AM.PARENT_ID IS NULL ");
		sql.append("     AND AM.IS_DELETED = 'N' ");
		sql.append("    ");
		sql.append("   UNION ALL ");
		sql.append("    ");
		sql.append("   SELECT T2.MENU_ID ");
		sql.append("     ,T2.PARENT_ID ");
		sql.append("     ,LVL + 1 ");
		sql.append("     ,T1.ROOT_ID ");
		sql.append("     ,T2.MENU_CODE ");
		sql.append("     ,T2.MENU_NAME ");
		sql.append("     ,T2.URL ");
		sql.append("     ,T2.SORTING_ORDER ");
		sql.append("     ,T2.SUBDEPT_LEVEL ");
		sql.append("   FROM ADM_MENU T2, T1 ");
		sql.append("   WHERE T2.PARENT_ID = T1.MENU_ID ");
		sql.append("     AND T2.IS_DELETED = 'N' ");
		sql.append(" ) ");
		sql.append(" SELECT T1.MENU_ID ");
		sql.append("   ,T1.PARENT_ID ");
		sql.append("   ,RPAD('.', (T1.LVL - 1) * 2, '.') || T1.MENU_ID AS TREE ");
		sql.append("   ,T1.LVL ");
		sql.append("   ,T1.ROOT_ID ");
		sql.append("   ,T1.MENU_CODE ");
		sql.append("   ,T1.MENU_NAME ");
		sql.append("   ,T1.URL ");
		sql.append("   ,T1.SORTING_ORDER ");
		sql.append("   ,T1.SUBDEPT_LEVEL ");
		sql.append(" FROM T1 ");
		sql.append(" INNER JOIN ADM_ROLE_MENU ARM ON ARM.MENU_CODE = T1.MENU_CODE ");
		sql.append(" WHERE ARM.ROLE_CODE IN (").append(StringUtils.repeat("?", ",", roleList.size())).append(") ");
		paramList.addAll(roleList);
		sql.append("   AND ARM.IS_DELETED = 'N' ");
		
		if (StringUtils.isNotEmpty(subdeptLevel)) {
			sql.append("   AND T1.SUBDEPT_LEVEL LIKE ? ");
			paramList.add("%" + subdeptLevel + "%");
		}
		
		sql.append(" GROUP BY T1.MENU_ID, T1.PARENT_ID, T1.LVL, T1.ROOT_ID, T1.MENU_CODE, T1.MENU_NAME, T1.URL, T1.SORTING_ORDER, T1.SUBDEPT_LEVEL ");
		sql.append(" ORDER BY LVL, T1.SORTING_ORDER ");
		
		
		List<MenuVo> menuList = commonJdbcTemplate.query(sql.toString(), paramList.toArray(), new BeanPropertyRowMapper(MenuVo.class));
		
		return menuList;
	}

}
