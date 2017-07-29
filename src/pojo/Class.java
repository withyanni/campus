package pojo;

import java.util.Date;

/**
 * @author yanni
 * @time   2017年7月24日 下午6:09:29
 */
public class Class
{
	private Integer cid;
	private String userName;
	private Date startTime;// 开始时间
	private Date endTime;// 结束时间
	private String place;// 地点
	private String academy;// 学院
	private String major;// 专业
	private String grade;// 年级
	private String className;// 课程名字
	private String price;// 价格
	private Integer sex;// 需要性别
	private String other;// 其它详细说明
	private String qq;// 联系方式
	private Date publishTime;

	public String getQq()
	{
		return qq;
	}

	public void setQq(String qq)
	{
		this.qq=qq;
	}

	public Date getPublishTime()
	{
		return publishTime;
	}

	public void setPublishTime(Date publishTime)
	{
		this.publishTime=publishTime;
	}

	public Integer getCid()
	{
		return cid;
	}

	public void setCid(Integer cid)
	{
		this.cid=cid;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName=userName;
	}

	public Date getStartTime()
	{
		return startTime;
	}

	public void setStartTime(Date startTime)
	{
		this.startTime=startTime;
	}

	public Date getEndTime()
	{
		return endTime;
	}

	public void setEndTime(Date endTime)
	{
		this.endTime=endTime;
	}

	public String getPlace()
	{
		return place;
	}

	public void setPlace(String place)
	{
		this.place=place;
	}

	public String getAcademy()
	{
		return academy;
	}

	public void setAcademy(String academy)
	{
		this.academy=academy;
	}

	public String getMajor()
	{
		return major;
	}

	public void setMajor(String major)
	{
		this.major=major;
	}

	public String getGrade()
	{
		return grade;
	}

	public void setGrade(String grade)
	{
		this.grade=grade;
	}

	public String getClassName()
	{
		return className;
	}

	public void setClassName(String className)
	{
		this.className=className;
	}

	public String getPrice()
	{
		return price;
	}

	public void setPrice(String price)
	{
		this.price=price;
	}

	public Integer getSex()
	{
		return sex;
	}

	public void setSex(Integer sex)
	{
		this.sex=sex;
	}

	public String getOther()
	{
		return other;
	}

	public void setOther(String other)
	{
		this.other=other;
	}

}
