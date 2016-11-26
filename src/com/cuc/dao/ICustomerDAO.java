package com.cuc.dao;

import java.util.ArrayList;
import com.cuc.model.Customer;

public interface ICustomerDAO {
	public boolean insert(Customer customer);//添加
	public ArrayList<Customer> searchByNo(String cname);//按姓名查询
	public boolean update(Customer customer);//修改
	public boolean deleteById(int cid);//按id删除
	public Customer getObjectById(int cid);

}
