package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import pojo.Goods;
import pojo.Page;
import service.GoodsService;
import service.UserService;
import utils.campus;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author yanni
 * @time 2017年7月21日 下午1:26:21
 */
@Controller
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserService userService;

    /**
     * 根据发布时间降序获取某个区间商品，默认最新9个
     *
     * @param count
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping("/getGoodsByNew")
    @ResponseBody
    public campus getGoodsByNew(
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "count", defaultValue = "9", required = false) Integer count)
            throws Exception {
        if (page == null || page <= 0)
            page = 1;
        if (count == null || count <= 0)
            count = 9;
        Page p = new Page(page, count);
        List<Goods> goods = goodsService.getGoodsByNew(p);
        return new campus(200, goods);
    }

    /**
     * 获取指定商品
     *
     * @param gid
     * @return
     * @throws Exception
     */
    @RequestMapping("/getGoodsByGid")
    @ResponseBody
    public campus getGoodsByGid(String gid) throws Exception {
        Goods goods = goodsService.getGoodsByGid(gid);
        return new campus(200, goods);
    }

    /**
     * 发布商品
     *
     * @param goods
     * @param files
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/publishGoods", method = RequestMethod.POST)
    @ResponseBody
    public campus publishGoods(Goods goods, @RequestParam MultipartFile[] files, @RequestParam String token)
            throws Exception {
        HashMap<String, Boolean> map = goodsService.validate(goods);
        if (map.size() > 0) {
            //参数有错误
            return new campus(400, map);
        }
        String userName=userService.isLogined(token);
        if(userName==null||userName.trim().equals(""))
        {//未登录
            return new campus(403,null);
        }
        goods.setUserName(userName);
        goods.setPublishTime(new Date());
        goods.setStatus(1);
        goods.setClickCount(0);
        goodsService.publishGoods(goods, files);
        return new campus(200, null);//发布成功
    }

    /**
     * 增加点击量
     *
     * @param gid
     * @return
     */
    @RequestMapping("/addClickCount")
    @ResponseBody
    public campus addClickCount(String gid) {
        if (gid.matches("[0-9a-zA-Z]{32}"))
            return new campus(400, null);
        goodsService.addClickCount(gid);
        return new campus(200, null);

    }

}
