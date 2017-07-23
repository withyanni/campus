package controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pojo.User;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import service.UserService;
import utils.campus;
import utils.yanni;

/**
 * @author yanni
 * @time   2017年7月21日 下午7:47:38
 */
@Controller
public class UserController
{
	@Autowired
	private UserService userService;
	@Autowired
	private Jedis jedis;
	@Autowired
	private JedisPool jedisPool;

	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	@RequestMapping (value="/registUser",method=RequestMethod.POST)
	@ResponseBody
	public campus registUser(User user)
	{
		HashMap<String, Boolean> map=userService.validate(user);
		if(map.size()>0)
			return new campus(400,map);
		userService.registUser(user);
		return new campus(200,user.getUserName());
	}

	/**
	 * 校验用户名是否可以注册
	 * @param userName
	 * @return
	 */
	@RequestMapping ("/usernameCanUse")
	@ResponseBody
	public campus usernameCanUse(String userName)
	{
		if(userName==null||!userName.matches("^[0-9a-zA-Z]{6,16}$"))
			return new campus(400,false);
		boolean b=userService.usernameCanUse(userName);
		if(b==false)
			return new campus(400,false);
		return new campus(200,true);
	}

	/**登录
	 * @param user
	 * @return
	 */
	@RequestMapping ("/login")
	@ResponseBody
	public campus login(User user ,HttpServletRequest request ,
			HttpServletResponse response)
	{
		if(user.getUserName()==null||user.getUserPassword()==null)
		{
			return new campus(400,"用户名密码不能为空！");
		}
		User loginUser=userService.login(user);
		if(loginUser==null||loginUser.getUid()==null)
		{
			return new campus(400,"用户名密码错误！");
		}
		String token=yanni.UUID();
		jedis=jedisPool.getResource();
		jedis.set("token:"+token,user.getUserName());
		jedis.expire("token:"+user.getUserName()+":"+token,1800);
		jedis.close();
		request.getSession().setAttribute("sessionUser",token);
		return new campus(200,user.getUserName()+":"+token);
	}

	/**
	 * 退出登录
	 * @param userName
	 * @param request
	 * @return
	 */
	@RequestMapping ("/quit")
	@ResponseBody
	public campus quit(
			@RequestParam (value="userName",required=true) String userName ,
			HttpServletRequest request)
	{
		String token=(String)request.getSession().getAttribute("sessionUser");
		request.getSession().invalidate();
		jedis=jedisPool.getResource();
		jedis.del("token:"+userName+":"+token);
		jedis.close();
		return new campus(200,null);
	}

	@RequestMapping ("/isLogined")
	public campus isLogined(String token)
	{
		jedis=jedisPool.getResource();
		String userName=jedis.get("token:"+token);
		jedis.close();
		if(userName==null)
			return new campus(400,false);
		return new campus(200,true);
	}
}
