package smvcTest.cmd;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import smvcTest.util.BaseController;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

	@RequestMapping("/login")
	public String login(@RequestParam("userName") String strUserName, @RequestParam("passWord") String strPassword, Model model, HttpServletRequest request) {
		
		if(strUserName.equals("admin")&&strPassword.equals("123")){
			
			request.getSession().setAttribute("userName", strUserName);
			request.getSession().setAttribute("passWord", strPassword);
			
			model.addAttribute("userName", strUserName);
			model.addAttribute("passWord", strPassword);
			return "business";
		}
		return "index";
	}

	@RequestMapping("/exceptionTest")
	public String exceptionTest() throws SQLException {
		throw new SQLException("数据库异常。");
	}
}
