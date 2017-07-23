package dao;

import java.util.List;

import pojo.Goods;
/**
 * 
 * @author yanni
 * @time   2017年7月21日 下午1:55:10
 */
public interface GoodsMapper {
	public List<Goods> getGoodsByNew(Integer count);//获取最新商品
	public Goods getGoodsByGid(String id);//获取指定商品
	public void publishGoods(Goods goods);//发布商品
	public void addClickCount(String gid);
}