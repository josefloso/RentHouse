package com.cuc.dao;

import java.util.ArrayList;
import com.cuc.model.Customer;

public interface ICustomerDAO {
	public boolean insert(Customer customer);//���
	public ArrayList<Customer> searchByNo(String cname);//��������ѯ
	public boolean update(Customer customer);//�޸�
	public boolean deleteById(int cid);//��idɾ��
	public Customer getObjectById(int cid);

}
