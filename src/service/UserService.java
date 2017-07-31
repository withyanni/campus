package service;

import dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.User;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;

/**
 * @author yanni
 * @time   2017年7月21日 下午8:03:31
 */
@Service
public class UserService
{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private Jedis jedis;
	@Autowired
	private JedisPool jedisPool;

	public HashMap<String, String> validate(User user)
	{
		HashMap<String, String> map=new HashMap<String, String>();
		if(userMapper.usernameCanUse(user.getUserName())>0)
		{
			map.put("usernameExistError",user.getUserName());
		}
		if(user.getUserName()==null
				||!user.getUserName().matches("^[0-9a-zA-Z]{6,16}$"))
		{
			map.put("usernameError",user.getUserName());
		}
		if(user.getUserPassword()==null
				||!user.getUserPassword().matches("^[0-9a-zA-Z]{6,16}$"))
		{
			map.put("userpasswordError",user.getUserPassword());
		}
		if(user.getReUserPassword()==null
				||!user.getReUserPassword().equals(user.getUserPassword()))
		{
			map.put("reUserPasswordError",user.getReUserPassword());
		}
		if(user.getEmail()==null||!user.getEmail().matches(
				"^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$"))
		{
			map.put("emailError",user.getEmail());
		}
		if(user.getQq()==null
				||!user.getQq().toString().matches("^[1-9][0-9]{4,9}$"))
		{
			map.put("qqError",user.getQq()==null?null:user.getQq().toString());
		}
		return map;

	}

	public void registUser(User user)
	{
		userMapper.registUser(user);
	}

	public User login(User user)
	{
		return userMapper.login(user);
	}

	public boolean usernameCanUse(String userName)
	{
		return userMapper.usernameCanUse(userName)==0;
	}

	public String isLogined(String token) {
		jedis=jedisPool.getResource();
		String userName=jedis.get("token:"+token);
		return userName;
	}
}
