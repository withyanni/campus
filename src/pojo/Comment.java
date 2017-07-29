package pojo;

import java.util.Date;

/**
 * @author yanni
 * @time   2017年7月24日 上午8:19:51
 */
public class Comment
{
	private Integer cid;
	private Integer mid;
	private String userName;
	private Integer secret;
	private String comment;
	private Date publishTime;

	public Integer getSecret()
	{
		return secret;
	}

	public void setSecret(Integer secret)
	{
		this.secret=secret;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName=userName;
	}

	public Integer getCid()
	{
		return cid;
	}

	public void setCid(Integer cid)
	{
		this.cid=cid;
	}

	public Integer getMid()
	{
		return mid;
	}

	public void setMid(Integer mid)
	{
		this.mid=mid;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment=comment;
	}

	public Date getPublishTime()
	{
		return publishTime;
	}

	public void setPublishTime(Date publishTime)
	{
		this.publishTime=publishTime;
	}

}
