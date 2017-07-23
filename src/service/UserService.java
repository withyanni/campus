package service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserMapper;
import pojo.User;

/**
 * @author yanni
 * @time   2017年7月21日 下午8:03:31
 */
@Service
public class UserService
{
	@Autowired
	private UserMapper userMapper;

	public HashMap<String, Boolean> validate(User user)
	{
		HashMap<String, Boolean> map=new HashMap<String, Boolean>();
		if(userMapper.usernameCanUse(user.getUserName())>0)
		{
			map.put("usernameExistError",true);
		}
		if(user.getUserName()==null
				||!user.getUserName().matches("^[0-9a-zA-Z]{6,16}$"))
		{
			map.put("usernameError",true);
		}
		if(user.getUserPassword()==null
				||!user.getUserPassword().matches("^[0-9a-zA-Z]{6,16}$"))
		{
			map.put("userpasswordError",true);
		}
		if(user.getReUserPassword()==null
				||!user.getReUserPassword().equals(user.getUserPassword()))
		{
			map.put("reUserPasswordError",true);
		}
		if(user.getEmail()==null||!user.getEmail().matches(
				"^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$"))
		{
			map.put("emailError",true);
		}
		if(user.getQq()==null
				||!user.getQq().toString().matches("^[1-9][0-9]{4,9}$"))
		{
			map.put("qqError",true);
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
}
