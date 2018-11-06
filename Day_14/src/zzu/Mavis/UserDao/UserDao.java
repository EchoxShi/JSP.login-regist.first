package zzu.Mavis.UserDao;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;

import org.dom4j.io.OutputFormat;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import zzu.Mavis.domain.User;

public class UserDao {
	private String path;

	public UserDao() {// ʲô��˼
		/*path = this.getClass().getClassLoader().getResource("Users.xml").getPath();*/
		//path = this.getClass().getResource("/Users.xml").getPath();
		path = this.getClass().getClassLoader().getResource("Users.xml").getPath();
		System.out.println(path);
	}

	@Test
	public void add(User user) {

		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(path);
			Element root = doc.getRootElement();

			Element userEle = root.addElement("user");
			userEle.addAttribute("username", user.getUsername());
			userEle.addAttribute("password", user.getPassword());

			OutputFormat format = new OutputFormat("\t", true);
			format.setTrimText(true);
			XMLWriter writer = new XMLWriter(new FileOutputStream(path),format);
			writer.write(doc);
			writer.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public User findByUsername(String username) {
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(path);
			Element ele = (Element) doc.selectSingleNode("//user[@username='" + username + "']");

			if (ele == null) {
				return null;
			}
			User user = new User();
			user.setUsername(ele.attributeValue("username"));
			user.setPassword(ele.attributeValue("password"));
			return user;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
