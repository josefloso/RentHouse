package com.cuc.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.cuc.dao.ICustomerDAO;
import com.cuc.dao.imp.CustomerDAO;
import com.cuc.model.Customer;
import com.cuc.util.FileUpload;

public class CustomerServlet extends HttpServlet {

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
		
		// 文件上传
		FileUpload fu = new FileUpload();
		fu.setRequest(request);
		String realPath = this.getServletContext().getRealPath("/upload");
		fu.setUploadPath(realPath + "\\");
		
		
		int i = fu.process();
		// int 操作结果 0 文件操作成功；1 request对象不存在。 2 没有设定文件保存路径或者文件保存路径不正确；3
		// 没有设定正确的enctype；4 文件操作异常。
		switch (i) {
			case 0:
				String[] fileArr = fu.getUpdFileNames();
				// 因为文件上传表单中采用的是enctype="multipart/form-data"，所以不能直接读取数据
				Customer customer = new Customer();
				customer.setCname(fu.getParameter("cname"));
				customer.setCsex(fu.getParameter("csex"));
				customer.setCtel(fu.getParameter("ctel"));
				customer.setCage(Integer.parseInt(fu.getParameter("cage")));
				String str[] = fu.getParameters("tt");
				customer.setPhoto(fileArr[0]);
				ICustomerDAO customerDAO = new CustomerDAO();
				if (customerDAO.insert(customer)) {
					request.setAttribute("message", "帐户添加成功！");
				} else {
					// 删除照片
					File f = new File(realPath + "\\" + fileArr[0]);
					f.delete();
					request.setAttribute("message", "操作失败！");
				}
				break;
			case 1:
				request.setAttribute("message", "request对象不存在！");
				break;
			case 2:
				request.setAttribute("message", "没有设置保存路径！");
				break;
			case 3:
				request
						.setAttribute("message",
								"表单没设置enctype=multipart/form-data！");
				break;
			case 4:
				request.setAttribute("message", "上传操作失败！");
				break;
			}
			request.getRequestDispatcher("/agency/result.jsp").forward(request,
					response);
		}
	
	public void search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ICustomerDAO customerDAO = new CustomerDAO();
		String cname = request.getParameter("cname");
		request.setAttribute("customerList", customerDAO.searchByNo(cname));
		request.setAttribute("condition", cname);// 存放查询条件
		request.getRequestDispatcher("/agency/displayaccount.jsp").forward(
				request, response);	
	}
	
	
	public void preupdate(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		
		int cid = Integer.parseInt(request.getParameter("cid"));
		ICustomerDAO customerDAO = new CustomerDAO();
		request.setAttribute("customer", customerDAO.getObjectById(cid));
		request.getRequestDispatcher("/agency/editcustomer.jsp").forward(request,
				response);
	}
	
	
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int cid = Integer.parseInt(request.getParameter("cid"));
		String cname = request.getParameter("cname");// 取得查询条件
		ICustomerDAO customerDAO = new CustomerDAO();
		if (customerDAO.deleteById(cid)) {
		// 删除照片
			String realPath = this.getServletContext().getRealPath("/upload");
			String photo = customerDAO.getObjectById(cid).getPhoto();
			File file = new File(realPath + "\\" + photo);
			file.delete();
		}
		request.setAttribute("cname", cname);// 存放查询条件
		search(request, response);
	
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 文件上传
		FileUpload fu = new FileUpload();
		fu.setRequest(request);
		String realPath = this.getServletContext().getRealPath("/upload");
		fu.setUploadPath(realPath + "\\");
		int i = fu.process();
		// int 操作结果 0 文件操作成功；1 request对象不存在。 2 没有设定文件保存路径或者文件保存路径不正确；3
		// 没有设定正确的enctype；4 文件操作异常。
		switch (i) {
		case 0:
			String[] fileArr = fu.getUpdFileNames();
			// 因为文件上传表单中采用的是enctype="multipart/form-data"，所以不能直接读取数据
			Customer customer = new Customer();
			customer.setCid(Integer.parseInt(fu.getParameter("cid")));
			customer.setCage(Integer.parseInt(fu.getParameter("cage")));
			customer.setCtel(fu.getParameter("ctel"));
			customer.setCname(fu.getParameter("cname"));
			customer.setCsex(fu.getParameter("csex"));
			String oldphoto = fu.getParameter("oldphoto");
			try {
				customer.setPhoto(fileArr[0]);
				// 删除原照片
				File f = new File(realPath + "\\" + oldphoto);
				f.delete();
			} catch (ArrayIndexOutOfBoundsException e) {
				customer.setPhoto(oldphoto);
			}
			ICustomerDAO customerDAO = new CustomerDAO();
			if (customerDAO.update(customer)) {
				request.setAttribute("message", "帐户修改成功！");
			} else {
				File f = new File(realPath + "\\" + fileArr[0]);
				f.delete();
				request.setAttribute("message", "操作失败！");
			}
			break;
	case 1:
		request.setAttribute("message", "request对象不存在！");
		break;
	case 2:
		request.setAttribute("message", "没有设置保存路径！");
		break;
	case 3:
		request
				.setAttribute("message",
						"表单没设置enctype=multipart/form-data！");
		break;
	case 4:
		request.setAttribute("message", "上传操作失败！");
		break;
	}
	request.getRequestDispatcher("/agency/result.jsp").forward(request,
		response);
}

	

}

