package me.zhoubl.zfinal.web.common.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import me.zhoubl.zfinal.common.page.DataTableColumn;
import me.zhoubl.zfinal.common.page.DataTableJson;
import me.zhoubl.zfinal.common.page.Page;
import me.zhoubl.zfinal.common.page.Query;
import me.zhoubl.zfinal.common.web.WebCommonConfig;
import me.zhoubl.zfinal.synthesis.entity.SysUser;
import me.zhoubl.zfinal.web.common.Constant;
import me.zhoubl.zfinal.web.common.service.ListQueryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

/**
 * Created by zhoubl on 2017/4/21.
 */
@Service
public class ListQueryServiceImpl implements ListQueryService {

	@Autowired
	public JdbcTemplate jdbcTemplate;

	@Override
	public Long getDataTableListSize(Page page, SysUser sysUser, DataTableJson dataTableJson, String queryName,
									 String sqlCondition, String queryStatement) {
		Query query = getQuery(dataTableJson, queryName);
		if (query != null) {
			if (Strings.isNullOrEmpty(query.getNextSql())) {
				StringBuilder sql = new StringBuilder("select count(a.id) ");
				sql.append(query.getSql());
				if (!Strings.isNullOrEmpty(queryStatement)) {
					if (sql.indexOf(" where ") == -1) {
						sql.append(" where 1=1 ");
					}
					sql.append(" and (" + queryStatement + ")");
				} else {
					if (!Strings.isNullOrEmpty(sqlCondition)) {
						if (sql.indexOf(" where ") == -1) {
							sql.append(" where 1=1 ");
						}
						sql.append(" and (" + sqlCondition + ")");
					}
				}

				sql = replaceSqlParam(sysUser, sql.toString());

				return jdbcTemplate.queryForObject(sql.toString(), Long.class);

			} else {
				StringBuilder sql = new StringBuilder("select count(a.id) ");
				sql.append(query.getSql());
				if (!Strings.isNullOrEmpty(queryStatement)) {
					if (sql.indexOf(" where ") == -1) {
						sql.append(" where 1=1 ");
					}
					sql.append(" and (" + queryStatement + ")");
				} else {
					if (!Strings.isNullOrEmpty(sqlCondition)) {
						if (sql.indexOf(" where ") == -1) {
							sql.append(" where 1=1 ");
						}
						sql.append(" and (" + sqlCondition + ")");
					}
				}

				sql.append(" " + query.getNextSql());

				sql = replaceSqlParam(sysUser, sql.toString());

				sql.insert(0, "select count(*) from ( ");
				sql.insert(sql.length(), " ) __table__ ");

				return jdbcTemplate.queryForObject(sql.toString(), Long.class);

			}
		}
		return 0L;
	}

	@Override
	public List<Map<String, Object>> getDataTableList(Page page, SysUser sysUser, DataTableJson dataTableJson,
			String queryName, String sqlCondition, String queryStatement) {
		Query query = getQuery(dataTableJson, queryName);
		List<Map<String, Object>> results = null;
		if (query != null) {
			StringBuilder columnSql = new StringBuilder();
			columnSql.append(",a.id as __a_id1");
			columnSql.append(",a.id as __a_id2");
			for (DataTableColumn column : dataTableJson.getColumns()) {
				if (column.getVisible()) {
					columnSql.append("," + column.getColumn());
				}
			}
			if (columnSql.length() > 0) {
				StringBuilder sql = new StringBuilder("select ");
				sql.append(columnSql.substring(1) + " ");
				sql.append(query.getSql());
				if (!Strings.isNullOrEmpty(queryStatement)) {
					if (sql.indexOf(" where ") == -1) {
						sql.append(" where 1=1 ");
					}
					sql.append(" and (" + queryStatement + ")");
				} else {
					if (!Strings.isNullOrEmpty(sqlCondition)) {
						if (sql.indexOf(" where ") == -1) {
							sql.append(" where 1=1 ");
						}
						sql.append(" and (" + sqlCondition + ")");
					}
				}

				if (!Strings.isNullOrEmpty(query.getNextSql())) {
					sql.append(" " + query.getNextSql());
				}

				if (!Strings.isNullOrEmpty(page.getOrderBy())) {
					sql.append(" order by " + page.getOrderBy());
				} else {
					if (!Strings.isNullOrEmpty(query.getOrderBy())) {
						sql.append(" order by " + query.getOrderBy());
					}
				}

				sql = replaceSqlParam(sysUser, sql.toString());

				sql.append(" limit " + (page.getPageIndex() - 1) * page.getPageSize() + "," + page.getPageSize());

				results = jdbcTemplate.queryForList(sql.toString());

			}
		}
		if (results == null) {
			results = new ArrayList<Map<String, Object>>();
		}
		return results;

	}

	@Override
	public Long getReferDataTableListSize(Page page, SysUser sysUser, DataTableJson dataTableJson, String queryName,
			String queryStatement, String p) {
		Query query = getQuery(dataTableJson, queryName);
		if (query != null) {
			if (Strings.isNullOrEmpty(query.getNextSql())) {
				StringBuilder sql = new StringBuilder("select count(a.id) ");
				sql.append(query.getSql());
				if (!Strings.isNullOrEmpty(queryStatement)) {
					if (sql.indexOf(" where ") == -1) {
						sql.append(" where 1=1 ");
					}
					sql.append(" and (" + queryStatement + ")");
				}
				sql = replaceSqlParam(sysUser, sql.toString());
				Long count = 0L;
				if (!Strings.isNullOrEmpty(p)) {
					String[] ps = StringUtils.split(p, ",");
					count = jdbcTemplate.queryForObject(sql.toString(), Long.class, ps);
				} else {
					count = jdbcTemplate.queryForObject(sql.toString(), Long.class);
				}
				return count;
			} else {
				StringBuilder sql = new StringBuilder("select count(a.id) ");
				sql.append(query.getSql());
				if (!Strings.isNullOrEmpty(queryStatement)) {
					if (sql.indexOf(" where ") == -1) {
						sql.append(" where 1=1 ");
					}
					sql.append(" and (" + queryStatement + ")");
				}
				sql.append(" " + query.getNextSql());
				sql = replaceSqlParam(sysUser, sql.toString());

				sql.insert(0, "select count(*) from ( ");
				sql.insert(sql.length(), " ) __table__ ");
				Long count = 0L;
				if (!Strings.isNullOrEmpty(p)) {
					String[] ps = StringUtils.split(p, ",");
					count = jdbcTemplate.queryForObject(sql.toString(), Long.class, ps);
				} else {
					count = jdbcTemplate.queryForObject(sql.toString(), Long.class);
				}
				return count;
			}

		}
		return 0L;
	}

	@Override
	public List<Map<String, Object>> getReferDataTableList(Page page, SysUser sysUser, DataTableJson dataTableJson,
			String queryName, String queryStatement, String p) {
		Query query = getQuery(dataTableJson, queryName);
		List<Map<String, Object>> results = null;
		if (query != null) {
			StringBuilder columnSql = new StringBuilder();
			columnSql.append(",a.id as __a_id1");
			columnSql.append(",a.id as __a_id2");
			for (DataTableColumn column : dataTableJson.getColumns()) {
				if (column.getVisible()) {
					columnSql.append("," + column.getColumn());
				}
			}
			if (columnSql.length() > 0) {
				StringBuilder sql = new StringBuilder("select ");
				sql.append(columnSql.substring(1) + " ");
				sql.append(query.getSql());
				if (!Strings.isNullOrEmpty(queryStatement)) {
					if (sql.indexOf(" where ") == -1) {
						sql.append(" where 1=1 ");
					}
					sql.append(" and (" + queryStatement + ")");
				}

				if (!Strings.isNullOrEmpty(query.getNextSql())) {
					sql.append(" " + query.getNextSql());
				}

				if (!Strings.isNullOrEmpty(page.getOrderBy())) {
					sql.append(" order by " + page.getOrderBy());
				} else {
					if (!Strings.isNullOrEmpty(query.getOrderBy())) {
						sql.append(" order by " + query.getOrderBy());
					}
				}

				sql = replaceSqlParam(sysUser, sql.toString());
				sql.append(" limit " + (page.getPageIndex() - 1) * page.getPageSize() + "," + page.getPageSize());

				if (!Strings.isNullOrEmpty(p)) {
					String[] ps = StringUtils.split(p, ",");
					results = jdbcTemplate.queryForList(sql.toString(), ps);
				} else {
					results = jdbcTemplate.queryForList(sql.toString());
				}

			}
		}
		if (results == null) {
			results = new ArrayList<Map<String, Object>>();
		}
		return results;
	}

	private StringBuilder replaceSqlParam(SysUser sysUser, String sql) {
		if (sysUser != null && sysUser.getId() != null) {
			sql = sql.replaceAll(WebCommonConfig.SQLUSERID, sysUser.getId().toString());
		}
		if (!Strings.isNullOrEmpty(sysUser.getCity())) {
			sql = sql.replaceAll(Constant.SQLCITY, sysUser.getCity());
		}
		
		return new StringBuilder(sql);
	}

	private Query getQuery(DataTableJson dataTableJson, String queryName) {
		List<Query> queries = dataTableJson.getQuerys();
		Query query = null;
		if (Strings.isNullOrEmpty(queryName)) {
			if (queries != null && queries.size() > 0) {
				query = queries.get(0);
			}
		} else {
			for (Query q : queries) {
				if (!Strings.isNullOrEmpty(q.getName()) && q.getName().equals(queryName)) {
					query = q;
					break;
				}
			}
		}
		return query;
	}

	@Override
	public List<Map<String, Object>> getDataTableListByExport(Page page, SysUser sysUser, DataTableJson dataTableJson,
			String queryName, String sqlCondition, String queryStatement) {

		Query query = getQuery(dataTableJson, queryName);
		List<Map<String, Object>> results = null;
		if (query != null) {
			StringBuilder columnSql = new StringBuilder();
			for (DataTableColumn column : dataTableJson.getColumns()) {
				if (column.getVisible()) {
					columnSql.append("," + column.getColumn());
				}
			}
			if (columnSql.length() > 0) {
				StringBuilder sql = new StringBuilder("select ");
				sql.append(columnSql.substring(1) + " ");
				sql.append(query.getSql());
				if (!Strings.isNullOrEmpty(queryStatement)) {
					if (sql.indexOf(" where ") == -1) {
						sql.append(" where 1=1 ");
					}
					sql.append(" and (" + queryStatement + ")");
				} else {
					if (!Strings.isNullOrEmpty(sqlCondition)) {
						if (sql.indexOf(" where ") == -1) {
							sql.append(" where 1=1 ");
						}
						sql.append(" and (" + sqlCondition + ")");
					}
				}

				if (!Strings.isNullOrEmpty(query.getNextSql())) {
					sql.append(" " + query.getNextSql());
				}

				if (!Strings.isNullOrEmpty(page.getOrderBy())) {
					sql.append(" order by " + page.getOrderBy());
				} else {
					if (!Strings.isNullOrEmpty(query.getOrderBy())) {
						sql.append(" order by " + query.getOrderBy());
					}
				}

				sql = replaceSqlParam(sysUser, sql.toString());

				sql.append(" limit " + page.getExportStartNum() + "," + page.getExportEndNum());

				results = jdbcTemplate.queryForList(sql.toString());

			}
		}
		if (results == null) {
			results = new ArrayList<Map<String, Object>>();
		}
		return results;

	}

}
