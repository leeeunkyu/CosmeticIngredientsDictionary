package com.work.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.work.dto.User;

@Repository
public class UserDaoImpl implements UserDao {

	private Factory factory;
	@Autowired
	public void setFactoryDao(Factory factory) {
		this.factory = factory;
	}
	/**
	 * �α���
	 */
	public String login(String userId,String userPw) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql= "select * from users where user_id=? and user_pw =?";		
		
		try {
			con=factory.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,userId);
			pstmt.setString(2,userPw);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getString("user_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					factory.close(con, pstmt, rs);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return null;
	}
	/**
	 * ���̵� üũ
	 * @param userId
	 * @return
	 */
	public boolean isId(String userId) {
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String sql="select * from users where user_id=?";
		
		try {
			con=factory.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				factory.close(con, pstmt, rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * ȸ������
	 * @param user
	 * @return
	 */
	public int join(User user) {
		Connection con=null;
		PreparedStatement pstmt = null;
		String sql ="insert into users values(?,?,?)";
		try {
			con = factory.getConnection();
			pstmt = con.prepareStatement(sql);
			System.out.println(user.getUserId() + user.getUserPw() + user.getUserName());
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPw());
			pstmt.setString(3, user.getUserName());
//			int rows = pstmt.executeUpdate(); 
//			System.out.println(rows);
//			return rows;
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("동일안 아이디가 존재합니다.");
		}finally {
			try {
				factory.close(con, pstmt, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
}
