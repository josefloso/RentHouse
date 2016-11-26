package com.cuc.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.cuc.dao.ICustomerDAO;
import com.cuc.model.Customer;
import com.cuc.util.DBUtil;

public class CustomerDAO implements ICustomerDAO {

	public boolean deleteById(int cid) {
		Connection con=DBUtil.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String sql="delete from t_customer where cid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			//e.printStackTrace();
			return false;
		}finally{
			DBUtil.getInstance().close(con, pstmt, null);
		}
		return true;
	}

	public Customer getObjectById(int cid) {
		Customer customer = new Customer();
		Connection con=DBUtil.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql="select * from t_customer where cid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cid);
			rs=pstmt.executeQuery();
			if(rs.next()){
				customer.setCid(rs.getInt("cid"));
				customer.setCtel(rs.getString("ctel"));
				customer.setCage(rs.getInt("cage"));
				customer.setCname(rs.getString("cname"));
				customer.setCsex(rs.getString("csex"));
				customer.setPhoto(rs.getString("photo"));
			}
		} catch (SQLException e) {
			return null;
		}finally{
			DBUtil.getInstance().close(con, pstmt, rs);
		}
		return customer;
	}

	public boolean insert(Customer customer) {
		Connection con=DBUtil.getInstance().getConnection();//数据库工具，连接打开
		PreparedStatement pstmt=null;//定义查询对象（全局）
		String sql="insert into t_customer(ctel,csex,cname,cage,photo) values(?,?,?,?,?)";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, customer.getCtel());
				pstmt.setString(2, customer.getCsex());
				pstmt.setString(3, customer.getCname());
				pstmt.setInt(4, customer.getCage());
				pstmt.setString(5, customer.getPhoto());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				return false;
			}finally{
				DBUtil.getInstance().close(con, pstmt, null);
			}
			return true;
	}

	public ArrayList<Customer> searchByNo(String cname) {
		ArrayList<Customer> result=new ArrayList<Customer>();
		Connection con=DBUtil.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql="select * from t_customer where cname like ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+cname+"%");
			rs=pstmt.executeQuery();
			while(rs.next()){
				Customer customer = new Customer();
				customer.setCid(rs.getInt("cid"));
				customer.setCtel(rs.getString("ctel"));
				customer.setCsex(rs.getString("csex"));
				customer.setCname(rs.getString("cname"));
				customer.setCage(rs.getInt("cage"));
				customer.setPhoto(rs.getString("photo"));
				result.add(customer);
			}
		} catch (SQLException e) {
			return null;
		}finally{
			DBUtil.getInstance().close(con, pstmt, rs);
		}
		return result;
	}

	public boolean update(Customer customer) {
		Connection con=DBUtil.getInstance().getConnection();
		PreparedStatement pstmt = null;
		String sql="update t_customer set ctel=?,cage=?,cname=?,csex=?,photo=? where cid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, customer.getCtel());
			pstmt.setInt(2, customer.getCage());
			pstmt.setString(3, customer.getCname());
			pstmt.setString(4, customer.getCsex());
			pstmt.setString(5, customer.getPhoto());
			pstmt.setInt(6, customer.getCid());
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
