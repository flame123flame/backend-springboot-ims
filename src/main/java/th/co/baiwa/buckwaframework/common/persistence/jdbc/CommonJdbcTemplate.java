package th.co.baiwa.buckwaframework.common.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Taechapon Himarat (Su)
 * @since: Sep 7, 2012
 */
public class CommonJdbcTemplate extends JdbcTemplate {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonJdbcTemplate.class);
	
	public CommonJdbcTemplate(DataSource dataSource) {
		super(dataSource);
	}
	
	//--------------------------------------------------------------------------------
	// Methods dealing with insert prepared statements
	//--------------------------------------------------------------------------------
	
	/**
	 * Single SQL insert statement via a prepared statement, binding the given arguments.
	 * @param sql SQL containing bind parameters
	 * @param params arguments to bind to the query
	 * (leaving it to the PreparedStatement to guess the corresponding SQL type);
	 * may also contain {@link SqlParameterValue} objects which indicate not
	 * only the argument value but also the SQL type and optionally the scale
	 * @return the number of rows affected
	 * @throws DataAccessException if there is any problem issuing the insert
	 */
	public int insert(final String sql, final Object... params) throws DataAccessException {
		return super.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql);
				preparePs(ps, params);
				return ps;
			}
		});
	}
	
	/**
	 * Single SQL insert statement via a prepared statement, binding the given arguments.
	 * Generated keys will be put into the given KeyHolder.
	 * @param sql SQL containing bind parameters
	 * @param params arguments to bind to the query
	 * (leaving it to the PreparedStatement to guess the corresponding SQL type);
	 * may also contain {@link SqlParameterValue} objects which indicate not
	 * only the argument value but also the SQL type and optionally the scale
	 * @return the long value of KeyHolder
	 * @throws DataAccessException if there is any problem issuing the insert
	 */
	public Long insertWithKeyHolder(final String sql, final Object... params) throws DataAccessException {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		super.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				preparePs(ps, params);
				return ps;
			}
		}, keyHolder);
		Long key = keyHolder.getKey().longValue();
		
		return key;
	}
	
	/**
	 * Prepare PreparedStatement from Array of Object following Object Type.
	 * @param ps the PreparedStatement Object
	 * @param params Array of Object will be set in PreparedStatement
	 */
	public void preparePs(PreparedStatement ps, Object[] params) throws SQLException {
		int i = 1;
		for (Object o : params) {
			if (o instanceof String) {
				ps.setString(i, (String) o);
			} else if (o instanceof Number) {
				if (o instanceof Byte) {
					ps.setByte(i, (Byte) o);
				} else if (o instanceof Short) {
					ps.setShort(i, (Short) o);
				} else if (o instanceof Integer) {
					ps.setInt(i, (Integer) o);
				} else if (o instanceof Long) {
					ps.setLong(i, (Long) o);
				} else if (o instanceof Float) {
					ps.setFloat(i, (Float) o);
				} else if (o instanceof Double) {
					ps.setDouble(i, (Double) o);
				} else if (o instanceof java.math.BigDecimal) {
					ps.setBigDecimal(i, (java.math.BigDecimal) o);
				}
			} else if (o instanceof java.util.Date) {
				ps.setTimestamp(i, new java.sql.Timestamp(((java.util.Date) o).getTime()));
			} else if (o instanceof java.time.temporal.Temporal) {
				if (o instanceof java.time.LocalDate) {
					ps.setDate(i, java.sql.Date.valueOf((java.time.LocalDate) o));
				} else if (o instanceof java.time.LocalTime) {
					ps.setTime(i, java.sql.Time.valueOf((java.time.LocalTime) o));
				} else if (o instanceof java.time.LocalDateTime) {
					ps.setTimestamp(i, java.sql.Timestamp.valueOf((java.time.LocalDateTime) o));
				} else if (o instanceof java.time.ZonedDateTime) {
					ps.setObject(i, ((java.time.ZonedDateTime) o).toInstant());
				}
			} else if (o instanceof Object) {
				ps.setObject(i, o);
			} else if (o == null) {
				ps.setNull(i, Types.NULL);
			}
			i++;
		}
	}

	//--------------------------------------------------------------------------------
	// Methods dealing with batch prepared statements
	//--------------------------------------------------------------------------------
	
	/**
	 * Execute a batch using the supplied SQL statement with the batch of supplied arguments.
	 * <p>Note Recommend using with {@link Transactional @Transactional} in Service Layer for fully batch process.
	 * @param sql the SQL statement to execute
	 * @param pss object to set parameters on the PreparedStatement
	 * @return an array containing the numbers of rows affected by each round update in the batch
	 */
	@Override
	public <T> int[][] batchUpdate(String sql, final Collection<T> batchArgs, final int batchSize,
			final ParameterizedPreparedStatementSetter<T> pss) throws DataAccessException {
		logger.info("Batch Process Start");
		long start = System.currentTimeMillis();
		
		Connection connection = null;
		PreparedStatement ps = null;
		List<int[]> rowsAffected = new ArrayList<int[]>();
		int[][] result = null;
		try {
			boolean batchSupported = true;
			connection = DataSourceUtils.getConnection(super.getDataSource());
			if (!JdbcUtils.supportsBatchUpdates(connection)) {
				batchSupported = false;
				logger.warn("JDBC Driver does not support Batch updates; resorting to single statement execution");
			}
			
			ps = connection.prepareStatement(sql.toString());
			if (batchSupported) {
				int count = 0;
				for (T obj : batchArgs) {
					count++;
					pss.setValues(ps, obj);
					ps.addBatch();
					if (count % batchSize == 0) {
						logger.info("## ps.executeBatch() at count={}", count);
						count = 0;
						rowsAffected.add(ps.executeBatch());
						ps.clearBatch();
					}
				}
				if (count > 0) {
					logger.info("## ps.executeBatch() at count={}", count);
					rowsAffected.add(ps.executeBatch());
					ps.clearBatch();
				}
			} else {
				for (T obj : batchArgs) {
					pss.setValues(ps, obj);
					int i = ps.executeUpdate();
					rowsAffected.add(new int[] {i});
				}
			}
			
			result = new int[rowsAffected.size()][];
			for (int i = 0; i < result.length; i++) {
				result[i] = rowsAffected.get(i);
			}
		} catch (SQLException e) {
			throw translateException("PreparedStatementCallback", sql, e);
		} finally {
			JdbcUtils.closeStatement(ps);
			DataSourceUtils.releaseConnection(connection, super.getDataSource());
		}
		
		long end = System.currentTimeMillis();
		logger.info("Batch Process Success, using {} seconds", (float) (end - start) / 1000F);
		return result;
	}
	
	public long findOracleSeqBySeqName(String seqName) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ").append(seqName).append(".NEXTVAL FROM DUAL");
		return queryForObject(sql.toString(), Long.class);
	}
}
