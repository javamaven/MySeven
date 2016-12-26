package com.seven.chen.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.seven.chen.dao.IUserDao;
import com.seven.chen.entity.Resource;
import com.seven.chen.entity.Role;
import com.seven.chen.entity.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao{
	
	@Override
	public List<User> listUser() {
		// TODO Auto-generated method stub
		return this.find("select * from tb_user",new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setNickname(rs.getString("nickname"));
				user.setPassword(rs.getString("password"));
				user.setStatus(rs.getInt("status"));
				user.setUsername(rs.getString("username"));
				return user;
			}
			
		} );
	}

	@Override
	public User loadByUsername(String username) {

			String sql = "select * from tb_user where username = ?";
			List<Object> params =new ArrayList<Object>();
			params.add(username);
			return this.get(sql, params, new RowMapper<User>(){
				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setNickname(rs.getString("nickname"));
					user.setPassword(rs.getString("password"));
					user.setStatus(rs.getInt("status"));
					user.setUsername(rs.getString("username"));
					return user;
				}});
		}

	@Override
	public List<User> listByRole(int id) {
		String sql = "select u from tb_user u,tb_role r,tb_userrole ur where u.id=ur.userId and r.id=ur.roleId and r.id=?";
		List<Object> params =new ArrayList<Object>();
		params.add(id);
		return this.find(sql, params,new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setNickname(rs.getString("nickname"));
				user.setPassword(rs.getString("password"));
				user.setStatus(rs.getInt("status"));
				user.setUsername(rs.getString("username"));
				return user;
			}
			
		} );
	}


	@Override
	public void adduser(User user) {
	
	String sql = "insert into tb_user " +
	"(username,password,status,nickname) values(?,?,?,?)";
	/*	Timestamp create = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getCreateDate()));*/
		List<Object> params = new ArrayList<Object>();
		params.add(user.getUsername());
		params.add(user.getPassword());
		params.add(user.getStatus());
		params.add(user.getNickname());
		this.update(sql, params);	
		
	}

	@Override
	public User load(int id) {
		String sql = "select * from tb_user where id=?";
		List<Object> params =new ArrayList<Object>();
		params.add(id);
		return this.get(sql, params,new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setNickname(rs.getString("nickname"));
				user.setPassword(rs.getString("password"));
				user.setStatus(rs.getInt("status"));
				user.setUsername(rs.getString("username"));
				return user;
			}
			
		} );
	}

	@Override
	public void delete(int id) {
		String sql = "delete from tb_user where id = ? ";
		this.jdbcTemplate.update(sql, new Object[]{id});
	}

}
