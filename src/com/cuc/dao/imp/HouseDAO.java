package com.cuc.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cuc.dao.IHouseDAO;
import com.cuc.model.Customer;
import com.cuc.model.House;
import com.cuc.util.DBUtil;

public class HouseDAO implements IHouseDAO {

	public boolean deleteById(int hid) {
		Connection con=DBUtil.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String sql="delete from t_house where hid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, hid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			//e.printStackTrace();
			return false;
		}finally{
			DBUtil.getInstance().close(con, pstmt, null);
		}
		return true;
	}

	public House getObjectById(int hid) {
		House house = new House();
		Connection con=DBUtil.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql="select * from t_house where hid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, hid);
			rs=pstmt.executeQuery();
			if(rs.next()){
				house.setHid(rs.getInt("hid"));
				house.setCid(rs.getInt("cid"));
				house.setHarea(rs.getInt("harea"));
				house.setHadd(rs.getString("hadd"));
				house.setHspend(rs.getInt("hspend"));
				house.setHstate(rs.getString("hstate"));
				house.setHphoto(rs.getString("hphoto"));
				house.setHremark(rs.getString("hremark"));
			}
		} catch (SQLException e) {
			return null;
		}finally{
			DBUtil.getInstance().close(con, pstmt, rs);
		}
		return house;
	}

	public boolean insert(House house) {
		Connection con=DBUtil.getInstance().getConnection();//数据库工具，连接打开
		PreparedStatement pstmt=null;//定义查询对象（全局）
		String sql="insert into t_house(cid,harea,hadd,hspend,hstate,hphoto,hremark) values(?,?,?,?,?,?,?)";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, house.getCid());
				pstmt.setInt(2, house.getHarea());
				pstmt.setString(3, house.getHadd());
				pstmt.setInt(4, house.getHspend());
				pstmt.setString(5, house.getHstate());
				pstmt.setString(6, house.getHphoto());
				pstmt.setString(7, house.getHremark());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				return false;
			}finally{
				DBUtil.getInstance().close(con, pstmt, null);
			}
			return true;
	}

	public ArrayList<House> searchByNo(String hremark) {
		ArrayList<House> result=new ArrayList<House>();
		Connection con=DBUtil.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql="select * from t_house where hremark like ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+hremark+"%");
			rs=pstmt.executeQuery();
			while(rs.next()){
				House house = new House();
				house.setHid(rs.getInt("hid"));
				house.setCid(rs.getInt("cid"));
				house.setHarea(rs.getInt("harea"));
				house.setHadd(rs.getString("hadd"));
				house.setHspend(rs.getInt("hspend"));
				house.setHstate(rs.getString("hstate"));
				house.setHphoto(rs.getString("hphoto"));
				house.setHremark(rs.getString("hremark"));
				result.add(house);
			}
		} catch (SQLException e) {
			return null;
		}finally{
			DBUtil.getInstance().close(con, pstmt, rs);
		}
		return result;
	}

	public boolean update(House house) {
		Connection con=DBUtil.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String sql="update t_house set cid=?,harea=?,hadd=?,hspend=?,hstate=?,hphoto=?,hremark=? where hid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, house.getCid());
			pstmt.setInt(2, house.getHarea());
			pstmt.setString(3, house.getHadd());
			pstmt.setInt(4, house.getHspend());			
			pstmt.setString(5, house.getHstate());
			pstmt.setString(6, house.getHphoto());
			pstmt.setString(7, house.getHremark());			
			pstmt.setInt(8, house.getHid());
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
