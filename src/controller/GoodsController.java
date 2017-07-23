package controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import pojo.Goods;
import pojo.User;
import service.GoodsService;
import utils.campus;

/**
 * @author yanni
 * @time   2017年7月21日 下午1:26:21
 */
@Controller
public class GoodsController
{
	@Autowired
	private GoodsService goodsService;

	/**
	 * 获取最新商品，默认9个
	 * @param count
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping ("/getGoodsByNew")
	@ResponseBody
	public campus getGoodsByNew(
			@RequestParam (value="count",defaultValue="9",required=false) Integer count)
			throws Exception
	{
		List<Goods> goods=goodsService.getGoodsByNew(count);
		return new campus(200,goods);
	}

	/**
	 * 获取指定商品
	 * @param gid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping ("/getGoodsByGid")
	@ResponseBody
	public campus getGoodsByGid(
			@RequestParam (value="gid",required=true) String gid)
			throws Exception
	{
		Goods goods=goodsService.getGoodsByGid(gid);
		return new campus(200,goods);
	}

	/**
	 * 发布商品
	 * @param goods
	 * @param files
	 * @return
	 * @throws Exception
	 */
	@RequestMapping (value="/publishGoods",method=RequestMethod.POST)
	@ResponseBody
	public campus publishGoods(Goods goods ,
			@RequestParam MultipartFile[] files ,HttpServletRequest request)
			throws Exception
	{
		HashMap<String, Boolean> map=goodsService.validate(goods);
		if(map.size()>0)
		{
			return new campus(400,map);
		}
		User user=(User)request.getSession().getAttribute("sessionUser");
		if(user==null||user.getUserName()==null)
			return new campus(403,"loginFirst");
		goods.setUserName(user.getUserName());
		goodsService.publishGoods(goods,files);
		return new campus(200,null);
	}

}
