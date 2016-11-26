package com.cuc.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cuc.dao.IAdminDAO;
import com.cuc.util.DBUtil;

public class AdminDAO implements IAdminDAO {

	public boolean login(String username, String password) {
		Connection con=DBUtil.getInstance().getConnection();//���ݿ⹤�ߣ����Ӵ�
		PreparedStatement pstmt=null;//�����ѯ����ȫ�֣�
		ResultSet rs=null;//����������ȫ�֣�
		String sql="select * from t_admin where username=? and password=?";//�������ݿ����
		try {
			pstmt=con.prepareStatement(sql);//pstmtʵ����
			pstmt.setString(1, username);//�滻sql�����"?"
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();//ִ�в�ѯ
			if(rs.next()){
			//����һ����¼	
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			return false;//������false
		}
	}
}
