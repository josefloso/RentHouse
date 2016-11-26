package com.cuc.dao;

import java.util.ArrayList;

import com.cuc.model.Customer;
import com.cuc.model.House;

public interface IHouseDAO {
	public boolean insert(House house);//���
	public ArrayList<House> searchByNo(String hremark);//����ע��ѯ
	public boolean update(House house);//�޸�
	public boolean deleteById(int hid);//��idɾ��
	public House getObjectById(int hid);
}
