package com.cuc.dao;

import java.util.ArrayList;

import com.cuc.model.Agency;

public interface IAgencyDAO {
	public boolean insert(Agency agen);//���
	public ArrayList<Agency> searchByNo(String username);//��������ѯ
	public boolean update(Agency agen);//�޸�
	public boolean deleteById(int aid);//��idɾ��
	public boolean login(String username,String password);//��¼��֤
	public Agency getObjectById(int aid);
}
