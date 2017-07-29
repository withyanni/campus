package pojo;

import java.util.Date;
import java.util.List;

public class Message
{
	private Integer mid;

	private String userName;

	private String message;

	private Integer zanCount;

	private Integer secret;

	private Date publishTime;

	private Integer clickCount;

	private Integer commentCount;

	private List<Comment> comment;

	public Integer getMid()
	{
		return mid;
	}

	public void setMid(Integer mid)
	{
		this.mid=mid;
	}

	public Integer getClickCount()
	{
		return clickCount;
	}

	public void setClickCount(Integer clickCount)
	{
		this.clickCount=clickCount;
	}

	public List<Comment> getComment()
	{
		return comment;
	}

	public void setComment(List<Comment> comment)
	{
		this.comment=comment;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName=userName;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message=message;
	}

	public Integer getZanCount()
	{
		return zanCount;
	}

	public void setZanCount(Integer zanCount)
	{
		this.zanCount=zanCount;
	}

	public Integer getSecret()
	{
		return secret;
	}

	public void setSecret(Integer secret)
	{
		this.secret=secret;
	}

	public Date getPublishTime()
	{
		return publishTime;
	}

	public void setPublishTime(Date publishTime)
	{
		this.publishTime=publishTime;
	}

	public Integer getCommentCount()
	{
		return commentCount;
	}

	public void setCommentCount(Integer commentCount)
	{
		this.commentCount=commentCount;
	}

}