package pojo;

/**
 * @author yanni
 * @time   2017年7月23日 下午10:38:50
 */
public class Page
{
	private Integer page;// 查看某一页
	private Integer count;// 每页记录数量

	private Integer start;
	private Integer end;

	public Integer getStart()
	{
		start=(page-1)*count;
		return start;
	}

	public Integer getEnd()
	{
		end=page*count-1;
		return end;
	}
	

	public Page()
	{
		super();
	}

	public Page(Integer page,Integer count)
	{
		super();
		this.page=page;
		this.count=count;
	}

	public void setStart(Integer start)
	{
		this.start=start;
	}

	public void setEnd(Integer end)
	{
		this.end=end;
	}

	public Integer getPage()
	{
		return page;
	}

	public void setPage(Integer page)
	{
		this.page=page;
	}

	public Integer getCount()
	{
		return count;
	}

	public void setCount(Integer count)
	{
		this.count=count;
	}
}
