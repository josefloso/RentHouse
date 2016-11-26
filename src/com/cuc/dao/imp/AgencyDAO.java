package com.cuc.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cuc.dao.IAgencyDAO;
import com.cuc.model.Agency;
import com.cuc.util.DBUtil;

public class AgencyDAO implements IAgencyDAO {

	public boolean deleteById(int aid) {
		Connection con=DBUtil.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String sql="delete from t_agency where aid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, aid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			//e.printStackTrace();
			return false;
		}finally{
			DBUtil.getInstance().close(con, pstmt, null);
		}
		return true;
	}

	public Agency getObjectById(int aid) {
		Agency agency=new Agency();
		Connection con=DBUtil.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql="select * from t_agency where aid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, aid);
			rs=pstmt.executeQuery();
			if(rs.next()){
				agency.setAid(rs.getInt("aid"));
				agency.setUsername(rs.getString("username"));
				agency.setPassword(rs.getString("password"));
				agency.setAname(rs.getString("aname"));
				agency.setAsex(rs.getString("asex"));
				agency.setPhoto(rs.getString("photo"));
			}
		} catch (SQLException e) {
			return null;
		}finally{
			DBUtil.getInstance().close(con, pstmt, rs);
		}
		return agency;
		
	}
	public boolean insert(Agency agen) {
		Connection con=DBUtil.getInstance().getConnection();//数据库工具，连接打开
		PreparedStatement pstmt=null;//定义查询对象（全局）
		String sql="insert into t_agency(username,password,aname,asex,photo) values(?,?,?,?,?)";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, agen.getUsername());
				pstmt.setString(2, agen.getPassword());
				pstmt.setString(3, agen.getAname());
				pstmt.setString(4, agen.getAsex());
				pstmt.setString(5, agen.getPhoto());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				return false;
			}finally{
				DBUtil.getInstance().close(con, pstmt, null);
			}
			return true;

	}

	public boolean login(String username, String password) {
		Connection con=DBUtil.getInstance().getConnection();//数据库工具，连接打开
		PreparedStatement pstmt=null;//定义查询对象（全局）
		ResultSet rs=null;//定义结果集（全局）
		String sql="select * from t_agency where username=? and password=?";//定义数据库语句
		try {
			pstmt=con.prepareStatement(sql);//pstmt实例化
			pstmt.setString(1, username);//替换sql语句中"?"
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();//执行查询
			if(rs.next()){
			//有下一条记录	
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			return false;//出错返回false
		}
	}
	

	public ArrayList<Agency> searchByNo(String username) {
		ArrayList<Agency> result=new ArrayList<Agency>();
		Connection con=DBUtil.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql="select * from t_agency where username like ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+username+"%");
			rs=pstmt.executeQuery();
			while(rs.next()){
				Agency agency = new Agency();
				agency.setAid(rs.getInt("aid"));
				agency.setUsername(rs.getString("username"));
				agency.setPassword(rs.getString("password"));
				agency.setAname(rs.getString("aname"));
				agency.setAsex(rs.getString("asex"));
				agency.setPhoto(rs.getString("photo"));
				result.add(agency);
			}
		} catch (SQLException e) {
			return null;
		}finally{
			DBUtil.getInstance().close(con, pstmt, rs);
		}
		return result;
	}

	public boolean update(Agency agency) {
		Connection con=DBUtil.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String sql="update t_agency set username=?,password=?,aname=?,asex=?,photo=? where aid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, agency.getUsername());
			pstmt.setString(2, agency.getPassword());
			pstmt.setString(3, agency.getAname());
			pstmt.setString(4, agency.getAsex());
			pstmt.setString(5, agency.getPhoto());
			pstmt.setInt(6, agency.getAid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			//e.printStackTrace();
			return false;
		}finally{
			DBUtil.getInstance().close(con, pstmt, null);
		}
		return true;
	}

}
