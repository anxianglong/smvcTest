package smvcTest.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionTimeOut extends HandlerInterceptorAdapter{
	
	private List<String> allowUrls; 
	/**
	 * Session超时，拦截访问
	 * 
	 */
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Object obj = request.getSession().getAttribute("userName");  
        if(obj==null){  
            request.getRequestDispatcher("login/login").forward(request, response);   
        return false;  
        }  
        else{  
            return super.preHandle(request, response, handler);  
        }
		
	}
	
	@RequestMapping("/sessiontimeout")  
    public void sessionTimeout(HttpServletRequest request,HttpServletResponse response) throws IOException {  
        String requestUrl = request.getRequestURI();   
        if (request.getHeader("x-requested-with") != null  
                && request.getHeader("x-requested-with").equalsIgnoreCase(  
                        "XMLHttpRequest")) { // ajax 超时处理  
            response.setHeader("sessionstatus", "timeout");  
            PrintWriter out = response.getWriter();  
            out.print("{timeout:true}");  
            out.flush();  
            out.close();  
        }else { // http 超时处理  
            response.sendRedirect(request.getContextPath() + "login/login");  
        }  
  
    }


	public List<String> getAllowUrls() {
		return allowUrls;
	}

	public void setAllowUrls(List<String> allowUrls) {
		this.allowUrls = allowUrls;
	}
	
	
}
