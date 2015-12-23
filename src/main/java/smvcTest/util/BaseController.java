package smvcTest.util;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {

	/**
	 * 异常处理
	 * 
	 * @param request
	 * @param e
	 * @return 错误页面
	 */
	@ExceptionHandler
	public String exception(HttpServletRequest request, Exception e) {
		// 添加自己的异常处理逻辑，如日志记录
		request.setAttribute("exceptionMessage", e.getMessage());

		// 根据不同的异常类型进行不同处理
		if (e instanceof SQLException)
			return "errors/error";
		else
			return "errors/error";
	}

}
