package com.seven.chen.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.seven.chen.dao.IResourceDao;
import com.seven.chen.entity.Resource;

@Repository("resourceDao")
public class ResourceDaoImpl extends BaseDaoImpl<Resource> implements IResourceDao{

	@Override
	public List<Resource> listResource() {
		return this.find("select * from tb_resource ", new RowMapper<Resource>(){

			@Override
			public Resource mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Resource re = new Resource();
				re.setId(rs.getInt("id"));
				re.setName(rs.getString("name"));
				re.setPermission(rs.getString("permission"));
				re.setUrl(rs.getString("url"));
				return re;
			}});
	}
	
	@Override
	public List<Resource> listAllResource(int uid) {
		String sql = "select res from tb_user u,tb_resource res,tb_userrole ur,tb_roleresource rr where " +
				"u.id=ur.userId and ur.roleId=rr.roleId  and rr.resId=res.id and u.id=?";
		List<Object> params =new ArrayList<Object>();
		params.add(uid);
		return this.find(sql,params, new RowMapper<Resource>(){

			@Override
			public Resource mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Resource re = new Resource();
				re.setId(rs.getInt("id"));
				re.setName(rs.getString("name"));
				re.setPermission(rs.getString("permission"));
				re.setUrl(rs.getString("url"));
				return re;
			}});
	}

	@Override
	public List<Resource> listRoleResource(int roleId) {
		String hql = "select res from tb_role role,tb_resource res,tb_roleresource rr where " +
				"role.id=rr.roleId and res.id=rr.resId and role.id=?";
		List<Object> params =new ArrayList<Object>();
		params.add(roleId);
		return this.find(hql,params, new RowMapper<Resource>(){

			@Override
			public Resource mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Resource re = new Resource();
				re.setId(rs.getInt("id"));
				re.setName(rs.getString("name"));
				re.setPermission(rs.getString("permission"));
				re.setUrl(rs.getString("url"));
				return re;
			}});
	}

	@Override
	public void add(Resource res) {
		
		String sql = "insert into tb_resource " +
				"(name,url,permission) values(?,?,?)";
				/*	Timestamp create = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getCreateDate()));*/
					List<Object> params = new ArrayList<Object>();
					params.add(res.getName());
					params.add(res.getUrl());
					params.add(res.getPermission());
					this.update(sql, params);	
		
	}

	@Override
	public Resource load(int id) {
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		return this.get("select * from tb_resource where id=? ",params, new RowMapper<Resource>(){

			@Override
			public Resource mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Resource re = new Resource();
				re.setId(rs.getInt("id"));
				re.setName(rs.getString("name"));
				re.setPermission(rs.getString("permission"));
				re.setUrl(rs.getString("url"));
				return re;
			}});
	}
	

}
