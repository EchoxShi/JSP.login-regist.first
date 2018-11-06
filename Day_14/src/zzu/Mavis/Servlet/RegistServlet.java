package zzu.Mavis.Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itcast.commons.CommonUtils;
import zzu.Mavis.Service.UserException;
import zzu.Mavis.Service.UserService;
import zzu.Mavis.domain.User;

public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		UserService userservice=new UserService();
		
		
		User form = CommonUtils.toBean(request.getParameterMap(),User.class);
		Map <String,String > errors=new HashMap<>()	;	
		String username=form.getUsername();
		String password=form.getPassword();
		
		if(username==null||username.trim().equals("")) {
			errors.put("username", "为空");
		}else if(username.length()<3||username.length()>15) {
			errors.put("username", "长度不符合");
			
		}
		
		
		
		if(password==null||password.trim().equals("")) {
			errors.put("password", "为空");
		}else if(password.length()<3||password.length()>15) {
			errors.put("passwprd","长度");
		}
		
		// ����֤�����У��
		String sessionVerifyCode = (String) request.getSession().getAttribute("validationCode");
		String verifyCode = form.getVerifyCode();
		if(verifyCode == null || verifyCode.trim().isEmpty()) {
			errors.put("verifyCode", "验证码不能为空");
		} else if(!verifyCode.equalsIgnoreCase(sessionVerifyCode)) {
			errors.put("verifyCode", "验证码错误");
		}
		
		if(errors!=null&&errors.size()>0) {
			request.setAttribute("errors", errors);//request.setAttribute("errors", Object obj)
			request.setAttribute("form", form);
			request.getRequestDispatcher("/User/regist.jsp").forward(request, response);
			return;
		}
		
		try {
			userservice.regist(form);
			response.getWriter().print("<h1>注册成功<h1/>,<a href='"+request.getContextPath()+"/User/login.jsp'>登陆!</a>");
			
			
		}catch(UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("user", form);
			request.getRequestDispatcher("/User/regist.jsp").forward(request, response);
			
		}
		
	}

}
