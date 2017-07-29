package dao;

import pojo.User;

public interface UserMapper {
	public Integer usernameCanUse(String username);

	public void registUser(User user);

	public User login(User user);
}