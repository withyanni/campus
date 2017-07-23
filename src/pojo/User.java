package pojo;

import java.util.Date;
/**
 * 
 * @author yanni
 * @time   2017年7月21日 下午7:58:54
 */
public class User
{
	private Integer uid;
	private String userName;
	private String userPassword;
	private String reUserPassword;//注册时的确认密码
	private String newUserPassword;//新密码
	private String reNewUserPassword;//修改密码时候的确认新密码
	private String oldPassword;
	private String email;
	private Date registTime;
	private Integer qq;
	
	public User()
	{
		super();
	}
	public User(String userName)
	{
		super();
		this.userName=userName;
	}
	public Integer getUid()
	{
		return uid;
	}
	public void setUid(Integer uid)
	{
		this.uid=uid;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName=userName;
	}
	public String getUserPassword()
	{
		return userPassword;
	}
	public void setUserPassword(String userPassword)
	{
		this.userPassword=userPassword;
	}
	public String getReUserPassword()
	{
		return reUserPassword;
	}
	public void setReUserPassword(String reUserPassword)
	{
		this.reUserPassword=reUserPassword;
	}
	public String getNewUserPassword()
	{
		return newUserPassword;
	}
	public void setNewUserPassword(String newUserPassword)
	{
		this.newUserPassword=newUserPassword;
	}
	public String getReNewUserPassword()
	{
		return reNewUserPassword;
	}
	public void setReNewUserPassword(String reNewUserPassword)
	{
		this.reNewUserPassword=reNewUserPassword;
	}
	public String getOldPassword()
	{
		return oldPassword;
	}
	public void setOldPassword(String oldPassword)
	{
		this.oldPassword=oldPassword;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email=email;
	}
	public Date getRegistTime()
	{
		return registTime;
	}
	public void setRegistTime(Date registTime)
	{
		this.registTime=registTime;
	}
	public Integer getQq()
	{
		return qq;
	}
	public void setQq(Integer qq)
	{
		this.qq=qq;
	}
	


}