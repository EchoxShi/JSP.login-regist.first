package zzu.Mavis.Service;

import zzu.Mavis.UserDao.UserDao;
import zzu.Mavis.domain.User;

public class UserService {
	private UserDao userdao=new UserDao();
	
	//ע��
	public void regist(User user) throws UserException {
		User _user=userdao.findByUsername(user.getUsername());
		System.out.println(_user);
		if(_user!=null) throw new UserException("用户名"+user.getUsername()+"已存在!");
		userdao.add(user);
	}
	
	public User login(String username ,String password) throws UserException {
		User user= userdao.findByUsername(username);
		if(user==null) throw new UserException("用户名不存在，请前往注册");
		if(!password.equals(user.getPassword())) throw new UserException("密码输入错误");
		return user;	
		
	}

	
	
	
	
}
