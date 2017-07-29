package utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import pojo.User;

/**
 * @author yanni
 * @time   2017年7月23日 下午5:04:14
 */
public class Interceptors implements HandlerInterceptor
{

	@Override
	public void afterCompletion(HttpServletRequest request ,
			HttpServletResponse response ,Object arg2 ,Exception e)
			throws Exception
	{
	}

	@Override
	public void postHandle(HttpServletRequest request ,
			HttpServletResponse response ,Object arg2 ,ModelAndView mv)
			throws Exception
	{
	}

	@Override
	public boolean preHandle(HttpServletRequest request ,
			HttpServletResponse response ,Object arg2) throws Exception
	{
		User user=(User)request.getSession().getAttribute("sessionUser");
		if(user==null||user.getUid()==null)
			return false;
		else 
			return true;
	}
}
