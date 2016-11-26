package com.cuc.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cuc.dao.ICustomerDAO;
import com.cuc.dao.IHouseDAO;
import com.cuc.dao.imp.CustomerDAO;
import com.cuc.dao.imp.HouseDAO;
import com.cuc.model.Customer;
import com.cuc.model.House;
import com.cuc.util.FileUpload;

public class HouseServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			doPost(request, response);
		}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		String para=request.getParameter("para");
		if(para==null){
			para="";
		}
		if (para.equals("logout")) {
			logout(request, response);
		} else if (para.equals("insert")) {
			insert(request, response);
		} 
		else if (para.equals("search")) {
			search(request, response);
		} else if (para.equals("preupdate")) {
			preupdate(request, response);
		}else if (para.equals("update")) {
			update(request, response);
		}else if (para.equals("delete")) {
			delete(request, response);
		}
	}
	public void logout(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	HttpSession session = request.getSession();
	session.invalidate();
	request.getRequestDispatcher("../login.jsp").forward(request,response);
	}

	public void insert(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	
		// �ļ��ϴ�
		FileUpload fu = new FileUpload();
		fu.setRequest(request);
		String realPath = this.getServletContext().getRealPath("/upload");
		fu.setUploadPath(realPath + "\\");
		
		
		int i = fu.process();
		// int ������� 0 �ļ������ɹ���1 request���󲻴��ڡ� 2 û���趨�ļ�����·�������ļ�����·������ȷ��3
		// û���趨��ȷ��enctype��4 �ļ������쳣��
		switch (i) {
			case 0:
				String[] fileArr = fu.getUpdFileNames();
				// ��Ϊ�ļ��ϴ����в��õ���enctype="multipart/form-data"�����Բ���ֱ�Ӷ�ȡ����
				House house = new House();
				house.setCid(Integer.parseInt(fu.getParameter("cid")));
				house.setHarea(Integer.parseInt(fu.getParameter("harea")));
				house.setHadd(fu.getParameter("hadd"));
				house.setHstate(fu.getParameter("hstate"));
				house.setHremark(fu.getParameter("hremark"));
				house.setHspend(Integer.parseInt(fu.getParameter("hspend")));
				String str[] = fu.getParameters("tt");
				house.setHphoto(fileArr[0]);
				IHouseDAO houseDAO = new HouseDAO();
				if (houseDAO.insert(house)) {
					request.setAttribute("message", "�ʻ���ӳɹ���");
				} else {
					// ɾ����Ƭ
					File f = new File(realPath + "\\" + fileArr[0]);
					f.delete();
					request.setAttribute("message", "����ʧ�ܣ�");
				}
				break;
			case 1:
				request.setAttribute("message", "request���󲻴��ڣ�");
				break;
			case 2:
				request.setAttribute("message", "û�����ñ���·����");
				break;
			case 3:
				request
						.setAttribute("message",
								"��û����enctype=multipart/form-data��");
				break;
			case 4:
				request.setAttribute("message", "�ϴ�����ʧ�ܣ�");
				break;
			}
			request.getRequestDispatcher("/agency/result.jsp").forward(request,
					response);
	}
	
	public void search(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	
		IHouseDAO houseDAO = new HouseDAO();
		String hremark = request.getParameter("hremark");
		request.setAttribute("houseList", houseDAO.searchByNo(hremark));
		request.setAttribute("condition", hremark);// ��Ų�ѯ����
		request.getRequestDispatcher("/agency/displayhouse.jsp").forward(
				request, response);	
	}
	
	
	public void preupdate(HttpServletRequest request,HttpServletResponse response)
		throws ServletException, IOException {
	
		int hid = Integer.parseInt(request.getParameter("hid"));
		IHouseDAO houseDAO = new HouseDAO();
		request.setAttribute("house", houseDAO.getObjectById(hid));
		request.getRequestDispatcher("/agency/edithouse.jsp").forward(request,
				response);
	}
	
	
	public void delete(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		int hid = Integer.parseInt(request.getParameter("hid"));
		String hremark = request.getParameter("hremark");// ȡ�ò�ѯ����
		IHouseDAO houseDAO = new HouseDAO();
		if (houseDAO.deleteById(hid)) {
		// ɾ����Ƭ
			String realPath = this.getServletContext().getRealPath("/upload");
			String hphoto = houseDAO.getObjectById(hid).getHphoto();
			File file = new File(realPath + "\\" + hphoto);
			file.delete();
		}
		request.setAttribute("hremark", hremark);// ��Ų�ѯ����
		search(request, response);
		
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	// �ļ��ϴ�
	FileUpload fu = new FileUpload();
	fu.setRequest(request);
	String realPath = this.getServletContext().getRealPath("/upload");
	fu.setUploadPath(realPath + "\\");
	int i = fu.process();
	// int ������� 0 �ļ������ɹ���1 request���󲻴��ڡ� 2 û���趨�ļ�����·�������ļ�����·������ȷ��3
	// û���趨��ȷ��enctype��4 �ļ������쳣��
	switch (i) {
	case 0:
		String[] fileArr = fu.getUpdFileNames();
		// ��Ϊ�ļ��ϴ����в��õ���enctype="multipart/form-data"�����Բ���ֱ�Ӷ�ȡ����
		House house = new House();
		house.setHid(Integer.parseInt(fu.getParameter("hid")));
		house.setCid(Integer.parseInt(fu.getParameter("cid")));
		house.setHarea(Integer.parseInt(fu.getParameter("harea")));
		house.setHadd(fu.getParameter("hadd"));
		house.setHspend(Integer.parseInt(fu.getParameter("hspend")));
		house.setHstate(fu.getParameter("hstate"));
		house.setHremark(fu.getParameter("hremark"));
		String oldphoto = fu.getParameter("oldphoto");
		try {
			house.setHphoto(fileArr[0]);
			// ɾ��ԭ��Ƭ
			File f = new File(realPath + "\\" + oldphoto);
			f.delete();
		} catch (ArrayIndexOutOfBoundsException e) {
			house.setHphoto(oldphoto);
		}
		IHouseDAO houseDAO = new HouseDAO();
		if (houseDAO.update(house)) {
			request.setAttribute("message", "�ʻ��޸ĳɹ���");
		} else {
			File f = new File(realPath + "\\" + fileArr[0]);
			f.delete();
			request.setAttribute("message", "����ʧ�ܣ�");
		}
		break;
	case 1:
	request.setAttribute("message", "request���󲻴��ڣ�");
	break;
	case 2:
	request.setAttribute("message", "û�����ñ���·����");
	break;
	case 3:
	request
			.setAttribute("message",
					"��û����enctype=multipart/form-data��");
	break;
	case 4:
	request.setAttribute("message", "�ϴ�����ʧ�ܣ�");
	break;
	}
	request.getRequestDispatcher("/agency/result.jsp").forward(request,
	response);
	}




}
