package zzu.Mavis.Test;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import zzu.Mavis.UserDao.UserDao;
import zzu.Mavis.domain.User;

public class UserDaoTest {
	@Test
	public void findByUsernameTest() throws Exception {
		UserDao u=new UserDao();
		User  user=u.findByUsername("����");
		System.out.println(user);
	}	
	@Test
	public void addTest() {
		User user= new User();
		user.setPassword("123");
		user.setUsername("����");
		UserDao u=new UserDao();
		u.add(user);
		
	}
}
