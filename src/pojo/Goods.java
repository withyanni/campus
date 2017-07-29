package pojo;

import java.util.Date;

public class Goods
{
	private String gid;

	private String userName;
	
	private Integer status;

	private String goodName;

	private String goodSort;

	private Integer goodPrice;

	private String sellerTel;

	private String goodDesc;

	private String goodPicture0;

	private String goodPicture1;

	private String goodPicture2;

	private Integer clickCount;

	private Date publishTime;

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status=status;
	}

	public String getGid()
	{
		return gid;
	}

	public void setGid(String gid)
	{
		this.gid=gid;
	}

	
	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName=userName;
	}

	public String getGoodName()
	{
		return goodName;
	}

	public void setGoodName(String goodName)
	{
		this.goodName=goodName;
	}

	public String getGoodSort()
	{
		return goodSort;
	}

	public void setGoodSort(String goodSort)
	{
		this.goodSort=goodSort;
	}

	public Integer getGoodPrice()
	{
		return goodPrice;
	}

	public void setGoodPrice(Integer goodPrice)
	{
		this.goodPrice=goodPrice;
	}

	public String getSellerTel()
	{
		return sellerTel;
	}

	public void setSellerTel(String sellerTel)
	{
		this.sellerTel=sellerTel;
	}

	public String getGoodDesc()
	{
		return goodDesc;
	}

	public void setGoodDesc(String goodDesc)
	{
		this.goodDesc=goodDesc;
	}

	public String getGoodPicture0()
	{
		return goodPicture0;
	}

	public void setGoodPicture0(String goodPicture0)
	{
		this.goodPicture0=goodPicture0;
	}

	public String getGoodPicture1()
	{
		return goodPicture1;
	}

	public void setGoodPicture1(String goodPicture1)
	{
		this.goodPicture1=goodPicture1;
	}

	public String getGoodPicture2()
	{
		return goodPicture2;
	}

	public void setGoodPicture2(String goodPicture2)
	{
		this.goodPicture2=goodPicture2;
	}

	public Integer getClickCount()
	{
		return clickCount;
	}

	public void setClickCount(Integer clickCount)
	{
		this.clickCount=clickCount;
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