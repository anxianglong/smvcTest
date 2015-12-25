package smvcTest.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SessionTimeOut implements HandlerInterceptor{
	
	private List<String> allowUrls; 
	/**
	 * Session超时，拦截访问
	 * 
	 */
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String requestUrl = request.getRequestURI();    
        
        /** 
         * 对所有的请求，*.f进行拦截 
         */  
        if(requestUrl.indexOf(".f")!=-1){  
            /** 
             * 登录页login/login不进行拦截 
             */  
            for(String url : allowUrls) {    
                if(requestUrl.endsWith(url)) {    
                    return true;    
                }    
            }   
              
            Object obj = request.getSession().getServletContext().getAttribute("user");  
            if(obj != null) {    
                return true;    
            }else {    
                response.setHeader("sessionstatus", "timeout");  
                return false;  
            }    
        }else{  
            return true;  
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
            response.sendRedirect(request.getContextPath() + "/login.do");  
        }  
  
    }

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public List<String> getAllowUrls() {
		return allowUrls;
	}

	public void setAllowUrls(List<String> allowUrls) {
		this.allowUrls = allowUrls;
	}
	
	
}
