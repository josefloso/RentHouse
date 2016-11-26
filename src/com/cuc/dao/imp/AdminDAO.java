package com.cuc.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cuc.dao.IAdminDAO;
import com.cuc.util.DBUtil;

public class AdminDAO implements IAdminDAO {

	public boolean login(String username, String password) {
		Connection con=DBUtil.getInstance().getConnection();//数据库工具，连接打开
		PreparedStatement pstmt=null;//定义查询对象（全局）
		ResultSet rs=null;//定义结果集（全局）
		String sql="select * from t_admin where username=? and password=?";//定义数据库语句
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
}
