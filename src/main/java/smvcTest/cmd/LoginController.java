package smvcTest.cmd;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import smvcTest.util.BaseController;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

	@RequestMapping("/login")
	public String login(@RequestParam("userName") String strUserName, @RequestParam("passWord") String strPassword, Model model) {
		
		if(strUserName.equals("admin")&&strPassword.equals("123")){
			
			model.addAttribute("username", strUserName);
			model.addAttribute("password", strPassword);
			return "business";
		}
		return "login";
	}

	@RequestMapping("/exceptionTest")
	public String exceptionTest() throws SQLException {
		throw new SQLException("数据库异常。");
	}
}
