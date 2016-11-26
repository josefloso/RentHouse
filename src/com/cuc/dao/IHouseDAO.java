package com.cuc.dao;

import java.util.ArrayList;

import com.cuc.model.Customer;
import com.cuc.model.House;

public interface IHouseDAO {
	public boolean insert(House house);//添加
	public ArrayList<House> searchByNo(String hremark);//按备注查询
	public boolean update(House house);//修改
	public boolean deleteById(int hid);//按id删除
	public House getObjectById(int hid);
}
