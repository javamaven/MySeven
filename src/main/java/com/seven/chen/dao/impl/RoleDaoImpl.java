package com.seven.chen.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.seven.chen.dao.IRoleDao;
import com.seven.chen.entity.Role;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements IRoleDao {

	@Override
	public void addRole(Role role){
		
		String sql = "insert into tb_role " +
				"(name,sn) values(?,?)";
				/*	Timestamp create = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getCreateDate()));*/
					List<Object> params = new ArrayList<Object>();
					params.add(role.getName());
					params.add(role.getSn());
					this.update(sql, params);	
	
	}
	
	@Override
	public List<Role> listRole() {
		// TODO Auto-generated method stub
		return this.find("select * from tb_role ", new RowMapper<Role>(){

			@Override
			public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Role role = new Role();
				
				role.setId(rs.getInt("id"));
				role.setName(rs.getString("name"));
				
				return role;
			}
			
		});
	}

	@Override
	public List<Role> listUserRole(int uid) {
		List<Object> params = new ArrayList<Object>();
		params.add(uid);
	// TODO Auto-generated method stub
	return this.find("select r.* from tb_role r,tb_userrole ur where r.id = ur.roleId and ur.userId = ?   ",params, new RowMapper<Role>(){
	
		@Override
		public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Role role = new Role();
			
			role.setId(rs.getInt("id"));
			role.setName(rs.getString("name"));
			role.setSn("sn");
			
			return role;
		}
		
	});
	}

	@Override
	public List<Role> listRoleSnByUser(int uid) {
		List<Object> params = new ArrayList<Object>();
		params.add(uid);
	// TODO Auto-generated method stub
		return this.find("select r.sn from tb_userrole ur,tb_role r,tb_user u where u.id=ur.userId and r.id=ur.roleId and u.id=?",params, new RowMapper<Role>(){
		
			@Override
			public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Role role = new Role();

				role.setSn("sn");
				
				return role;
			}
			
		});
		}

	@Override
	public void delete(int id) {
		String sql = "delete from tb_role where id =?";
		this.jdbcTemplate.update(sql, new Object[]{id});
	}

	@Override
	public Role load(int id) {
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		return this.get("select r.* from tb_role r where r.id = ?   ",params, new RowMapper<Role>(){
		
			@Override
			public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Role role = new Role();
				
				role.setId(rs.getInt("id"));
				role.setName(rs.getString("name"));
				role.setSn("sn");
				
				return role;
			}
			
		});
		}

}
