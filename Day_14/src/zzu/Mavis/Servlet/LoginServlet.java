package zzu.Mavis.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zzu.Mavis.Service.UserException;
import zzu.Mavis.Service.UserService;
import zzu.Mavis.domain.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
		
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		/*String loginCode = request.getParameter("loginCode");
		*/
		if(username == null || username.trim().isEmpty()) {
			request.setAttribute("msg", "用户名不能空!");
			request.getRequestDispatcher("/User/ogin.jsp").forward(request, response);
			return;
		}
		if(password == null || password.trim().isEmpty()) {
			request.setAttribute("msg", "密码不能空!");
			request.getRequestDispatcher("/User/login.jsp").forward(request, response);
			return;			
		}
		/*if(loginCode == null || loginCode.trim().isEmpty()) {
			request.setAttribute("msg", "��֤�벻��Ϊ�գ�");
			request.setAttribute("username", username);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;			
		}
		*/
		/*String vCode = (String)request.getSession().getAttribute("loginCode");
		request.getSession().removeAttribute("loginCode");
		if(!vCode.equalsIgnoreCase(loginCode)) {
			request.setAttribute("msg", "��֤�����");
			request.setAttribute("username", username);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;				
		}*/
		
		UserService userService = new UserService();
		User user;
		try {
			user = (User) userService.login(username, password);
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("username",username);
			request.getRequestDispatcher("/User/login.jsp").forward(request, response);
			return;	
		}
		
		request.getSession().setAttribute("user", user);
		request.getRequestDispatcher("/User/welcom.jsp").forward(request, response);
	}
		
	

}
