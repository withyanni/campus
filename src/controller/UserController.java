package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pojo.User;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import service.UserService;
import utils.campus;
import utils.yanni;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

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
	public campus registUser(@RequestBody User user)
	{
		HashMap<String, String> map=userService.validate(user);
		if(map.size()>0)
			return new campus(400,map);
		user.setRegistTime(new Date());
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
			return new campus(400,null);
		boolean b=userService.usernameCanUse(userName);
		if(b==false)
			return new campus(400,null);
		return new campus(200,null);
	}

	/**登录
	 * @param user
	 * @return
	 */
	@RequestMapping (value="login",method=RequestMethod.POST)
	@ResponseBody
	public campus login(@RequestBody User user)
	{
		if(user.getUserName()==null||user.getUserPassword()==null)
		{
			return new campus(400,null);
		}
		User loginUser=userService.login(user);
		if(loginUser==null||loginUser.getUid()==null)
		{
			return new campus(403,null);
		}
		String token=yanni.UUID();
		jedis=jedisPool.getResource();
		jedis.set("token:"+token,user.getUserName());
		jedis.expire("token:"+token,1800);
		jedis.close();
		return new campus(200,token);
	}

	/**
	 * 退出登录
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping ("/quit")
	@ResponseBody
	public campus quit(
			@RequestParam (value="token",required=true) String token ,
			HttpServletRequest request)
	{

		jedis=jedisPool.getResource();
		jedis.del("token:"+token);
		request.getSession().invalidate();
		jedis.close();
		return new campus(200,null);
	}

	/**
	 * 判断是否登录
	 * @param token
	 * @return
	 */
	@RequestMapping ("/isLogined")
	public campus isLogined(String token)
	{
		String userName=userService.isLogined("token:"+token);
		if(userName==null||userName.trim().equals(""))
			return new campus(200,false);
		return new campus(200,userName);
	}
}