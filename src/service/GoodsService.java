package service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dao.GoodsMapper;
import pojo.Goods;
import pojo.Page;
import utils.yanni;

/**
 * @author yanni
 * @time   2017年7月21日 下午1:03:14
 */
@Service
public class GoodsService
{
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private GoodsMapper goodsMapper;

	public List<Goods> getGoodsByNew(Page p)
	{
		List<Goods> goods=goodsMapper.getGoodsByNew(p);
		for(Goods good:goods)
		{
			addClickCount(good.getGid());
			good.setClickCount(good.getClickCount()+1);
		}
		return goods;
	}

	public Goods getGoodsByGid(String gid)
	{
		goodsMapper.addClickCount(gid);
		Goods goods=goodsMapper.getGoodsByGid(gid);
		return goods;
	}

	public void publishGoods(Goods goods ,MultipartFile[] files)
			throws IOException,IllegalAccessException,InvocationTargetException
	{
		String gid=yanni.UUID();
		String savePath=request.getSession().getServletContext()
				.getRealPath("/")+"goodsPicture/"+gid.charAt(0);
		File ff=new File(savePath);
		if(!ff.exists())
		{
			ff.mkdirs();
		}
		HashMap<String, String> map=new HashMap<String, String>();
		if(files!=null&&files.length>0)
		{
			// 循环获取file数组中得文件
			for(int i=0;i<files.length;i++)
			{
				MultipartFile file=files[i];
				// 判断文件是否为空
				if(!file.isEmpty())
				{
					try
					{
						String oldName=file.getOriginalFilename();
						String ends=oldName.substring(oldName.lastIndexOf("."));
						// 文件全路径名
						String filePath=savePath+"/"+gid+"-"+i+ends;
						map.put("goodPicture"+i,
								gid.charAt(0)+"/"+gid+"-"+i+ends);
						// 转存文件
						file.transferTo(new File(filePath));
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		Goods temp=yanni.toBean(map,Goods.class);
		goods.setGid(gid);
		goods.setGoodPicture0(temp.getGoodPicture0());
		goods.setGoodPicture1(temp.getGoodPicture1());
		goods.setGoodPicture2(temp.getGoodPicture2());
		goodsMapper.publishGoods(goods);
	}

	public HashMap<String, Boolean> validate(Goods goods)
	{
		HashMap<String, Boolean> map=new HashMap<String, Boolean>();
		// 商品名错误
		if(goods.getGoodName()==null||goods.getGoodName().trim().length()<2
				||goods.getGoodName().trim().length()>16)
		{
			map.put("goodNameError",true);
		}
		/*
		 * 如果分类多的时候用数据库查出一个分类集合然后去判断是否属于该集合
		 */
		if(goods.getGoodSort()==null||!goods.getGoodSort().equals("图书笔记")
				&&!goods.getGoodSort().equals("百货用品")
				&&!goods.getGoodSort().equals("服饰鞋帽")
				&&!goods.getGoodSort().equals("数码电子")
				&&!goods.getGoodSort().equals("游戏体育")
				&&!goods.getGoodSort().equals("美妆零食")
				&&!goods.getGoodSort().equals("其它"))
		{
			map.put("goodSortError",true);
		}
		// 商品价格错误
		if(goods.getGoodPrice()==null||goods.getGoodPrice()<0
				||goods.getGoodPrice()>10000)
		{
			map.put("goodPriceError",true);
		}
		/*
		 * 缺少联系方式的校验
		 */
		// 商品上传文件的后缀名校验
		if(goods.getGoodPicture0()!=null)
		{
			if(!goods.getGoodPicture0().endsWith(".jpg")
					&&!goods.getGoodPicture0().endsWith(".png")
					&&!goods.getGoodPicture0().endsWith(".jpeg")
					&&!goods.getGoodPicture1().endsWith("bmp"))
			{
				map.put("endsError0",true);
			}
		}
		if(goods.getGoodPicture1()!=null)
		{
			if(!goods.getGoodPicture1().endsWith(".jpg")
					&&!goods.getGoodPicture1().endsWith(".png")
					&&!goods.getGoodPicture1().endsWith(".jpeg")
					&&!goods.getGoodPicture1().endsWith("bmp"))
			{
				map.put("endsError1",true);
			}
		}
		if(goods.getGoodPicture2()!=null)
		{
			if(!goods.getGoodPicture0().endsWith(".jpg")
					&&!goods.getGoodPicture2().endsWith(".png")
					&&!goods.getGoodPicture2().endsWith(".jpeg")
					&&!goods.getGoodPicture1().endsWith("bmp"))
			{
				map.put("endsError2",true);
			}
		}
		return map;
	}

	public void addClickCount(String gid)
	{
		goodsMapper.addClickCount(gid);
	}
}