package th.go.excise.ims.ia.util;

import org.springframework.stereotype.Component;

import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.vo.ExciseDepartmentVo;
import th.go.excise.ims.preferences.vo.ExciseDepartment;

@Component
public class ExciseDepartmentUtil {

	public static ExciseDepartmentVo getExciseDepartment(String officeCode) {

		ExciseDepartmentVo vo = new ExciseDepartmentVo();

		if (!"00".equals(officeCode.substring(0, 2))) {
			ExciseDepartment sector = ApplicationCache.getExciseDepartment(officeCode.substring(0, 2) + "0000");
			vo.setSector(sector.getDeptName());
			if (!"0000".equals(officeCode.substring(2, 6))) {
				ExciseDepartment area = ApplicationCache.getExciseDepartment(officeCode.substring(0, 4) + "00");
				vo.setArea(area.getDeptName());
				if (!"00".equals(officeCode.substring(4, 6))) {
					ExciseDepartment branch = ApplicationCache.getExciseDepartment(officeCode);
					vo.setBranch(branch.getDeptName().split(" ")[1]);
				}
			}
			vo.setOfficeCode(officeCode);
			ExciseDepartment offShortName = ApplicationCache.getExciseDepartment(officeCode);
			vo.setOffShortName(offShortName.getDeptShortName());
		} else {
			ExciseDepartment sector = ApplicationCache.getExciseDepartment(officeCode);
			vo.setSector(sector.getDeptName());
			vo.setOfficeCode(officeCode);
			ExciseDepartment offShortName = ApplicationCache.getExciseDepartment(officeCode);
			vo.setOffShortName(offShortName.getDeptShortName());
		}

		return vo;
	}

	public static ExciseDepartmentVo getExciseDepartmentFull(String officeCode) {

		ExciseDepartmentVo vo = new ExciseDepartmentVo();

		if (!"00".equals(officeCode.substring(0, 2))) {
			ExciseDepartment sector = ApplicationCache.getExciseDepartment(officeCode.substring(0, 2) + "0000");
			vo.setSector(sector.getDeptName());
			if (!"0000".equals(officeCode.substring(2, 6))) {
				ExciseDepartment area = ApplicationCache.getExciseDepartment(officeCode.substring(0, 4) + "00");
				vo.setArea(area.getDeptName());
				if (!"00".equals(officeCode.substring(4, 6))) {
					ExciseDepartment branch = ApplicationCache.getExciseDepartment(officeCode);
					vo.setBranch(branch.getDeptName());
				}
			}
			vo.setOfficeCode(officeCode);
			ExciseDepartment offShortName = ApplicationCache.getExciseDepartment(officeCode);
			vo.setOffShortName(offShortName.getDeptShortName());
		} else {
			ExciseDepartment sector = ApplicationCache.getExciseDepartment(officeCode);
			vo.setSector(sector.getDeptName());
			vo.setOfficeCode(officeCode);
			ExciseDepartment offShortName = ApplicationCache.getExciseDepartment(officeCode);
			vo.setOffShortName(offShortName.getDeptShortName());
		}

		return vo;
	}

}
