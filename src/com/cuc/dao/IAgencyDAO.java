package com.cuc.dao;

import java.util.ArrayList;

import com.cuc.model.Agency;

public interface IAgencyDAO {
	public boolean insert(Agency agen);//添加
	public ArrayList<Agency> searchByNo(String username);//按姓名查询
	public boolean update(Agency agen);//修改
	public boolean deleteById(int aid);//按id删除
	public boolean login(String username,String password);//登录验证
	public Agency getObjectById(int aid);
}
