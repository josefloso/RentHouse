package com.cuc.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cuc.dao.IAdminDAO;
import com.cuc.dao.IAgencyDAO;
import com.cuc.dao.imp.AdminDAO;
import com.cuc.dao.imp.AgencyDAO;
import com.cuc.model.Admin;
import com.cuc.model.Agency;

public class UserValid extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String code = request.getParameter("code");
		int user = Integer.parseInt(request.getParameter("user"));
		String rand = (String) session.getAttribute("rand");
		if (!code.equals(rand)) {
			request.setAttribute("message", "��֤�����,���������룡");
		}else {
			switch (user) {
				case 0:
		            IAdminDAO adminDao=new AdminDAO();
		            if (adminDao.login(username, password)){
			            Admin admin=new Admin();
			            admin.setUsername(username);
			            admin.setPassword(password);
			            session.setAttribute("admin", admin);
			            response.sendRedirect("../admin/welcome.jsp");
			            return;
				   } else {
					    request.setAttribute("message","�˺Ż�������󣡻����㲻�ǹ���Ա�����������롣");  
				   }
		           break;
				case 1:
		            IAgencyDAO agencyDao=new AgencyDAO();
				    if (agencyDao.login(username, password)) {
				    		Agency agency=new Agency();
				            agency.setUsername(username);
				            agency.setPassword(password);
				            session.setAttribute("agency", agency);
				            response.sendRedirect("../agency/welcome.jsp");
		                    return;
				    } else {
					    request.setAttribute("message","�˺Ż�������󣡻����㲻���н飬���������롣");  
				    }
		                    break;
			}
		}
		request.getRequestDispatcher("../login.jsp").forward(request,
				response);
	}

}

	  

	
