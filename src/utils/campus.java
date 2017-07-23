package utils;

/**
 * 自定义简单相应结构
 * @author yanni
 * @time   2017年7月21日 下午9:04:12
 */
public class campus
{
	// HTTP状态码
	private Integer status;
	// 响应数据
	private Object data;

	public campus(Integer status,Object data)
	{
		this.status=status;
		this.data=data;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status=status;
	}

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data=data;
	}

	
	
}
