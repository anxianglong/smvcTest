package smvcTest.cmd;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import smvcTest.util.BaseController;

@Controller
@RequestMapping("/test")
public class SMVCTController extends BaseController {

	@RequestMapping("/login")
	public String login(@RequestParam("username") String strUserName, Model model) {
		model.addAttribute("userName", strUserName);
		return "business";
	}

	@RequestMapping("/exceptionTest")
	public String exceptionTest() throws SQLException {
		throw new SQLException("数据库异常。");
	}
}
