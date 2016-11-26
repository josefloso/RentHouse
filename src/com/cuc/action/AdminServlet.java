package com.cuc.action;

import java.io.File;
import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.cuc.dao.IAgencyDAO;

import com.cuc.dao.imp.AgencyDAO;

import com.cuc.model.Agency;
import com.cuc.util.FileUpload;

public class AdminServlet extends HttpServlet {

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
			Agency agency = new Agency();
			agency.setUsername(fu.getParameter("username"));
			agency.setPassword(fu.getParameter("password"));
			agency.setAname(fu.getParameter("name"));
			agency.setAsex(fu.getParameter("asex"));
			String str[] = fu.getParameters("tt");
			agency.setPhoto(fileArr[0]);
			IAgencyDAO agencyDAO = new AgencyDAO();
			if (agencyDAO.insert(agency)) {
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
		request.getRequestDispatcher("/admin/result.jsp").forward(request,
				response);
		}
	
	public void search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IAgencyDAO agencyDAO = new AgencyDAO();
		String username = request.getParameter("username");
		request.setAttribute("agencyList", agencyDAO.searchByNo(username));
		request.setAttribute("condition", username);// ��Ų�ѯ����
		request.getRequestDispatcher("/admin/displayaccount.jsp").forward(
				request, response);	
	}
	
	
	public void preupdate(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		int aid = Integer.parseInt(request.getParameter("aid"));
		IAgencyDAO agencyDAO = new AgencyDAO();
		request.setAttribute("agency", agencyDAO.getObjectById(aid));
		request.getRequestDispatcher("/admin/editagency.jsp").forward(request,
				response);
	}
	
	
	public void delete(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int aid = Integer.parseInt(request.getParameter("aid"));
		String username = request.getParameter("username");// ȡ�ò�ѯ����
		IAgencyDAO agencyDAO = new AgencyDAO();
		if (agencyDAO.deleteById(aid)) {
		// ɾ����Ƭ
			String realPath = this.getServletContext().getRealPath("/upload");
			String photo = agencyDAO.getObjectById(aid).getPhoto();
			File file = new File(realPath + "\\" + photo);
			file.delete();
		}
		request.setAttribute("username", username);// ��Ų�ѯ����
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
			Agency agency = new Agency();
			agency.setAid(Integer.parseInt(fu.getParameter("aid")));
			agency.setUsername(fu.getParameter("username"));
			agency.setPassword(fu.getParameter("password"));
			agency.setAname(fu.getParameter("aname"));
			agency.setAsex(fu.getParameter("asex"));
			String oldphoto = fu.getParameter("oldphoto");
			try {
				agency.setPhoto(fileArr[0]);
				// ɾ��ԭ��Ƭ
				File f = new File(realPath + "\\" + oldphoto);
				f.delete();
			} catch (ArrayIndexOutOfBoundsException e) {
				agency.setPhoto(oldphoto);
			}
			IAgencyDAO agencyDAO = new AgencyDAO();
			if (agencyDAO.update(agency)) {
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
	request.getRequestDispatcher("/admin/result.jsp").forward(request,
		response);
}

	

}

