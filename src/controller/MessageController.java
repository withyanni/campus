package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Message;
import pojo.Page;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import service.MessageService;
import utils.campus;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author yanni
 * @time 2017年7月23日 下午7:04:07
 */
@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private Jedis jedis;
    @Autowired
    private JedisPool jedisPool;

    /**
     * 发布留言
     *
     * @param message
     * @param request
     * @return
     */
    @RequestMapping(value = "/publishMessage", method = RequestMethod.POST)
    @ResponseBody
    public campus publishMessage(Message message,
                                 @RequestParam(value = "token", required = true) String token,
                                 HttpServletRequest request) {
        jedis = jedisPool.getResource();
        String userName = jedis.get(token);
        jedis.close();
        if (userName == null || userName.trim().equals(""))
            return new campus(403, null);
        if (message.getSecret() == null
                || (message.getSecret() != 0 && message.getSecret() != 1))
            message.setSecret(0);
        message.setUserName(userName);
        message.setCommentCount(0);
        message.setPublishTime(new Date());
        message.setZanCount(0);
        message.setClickCount(0);
        messageService.publishMessage(message);
        if (message.getSecret() == 1)
            message.setUserName(null);
        return new campus(200, null);
    }

    /**
     * 获取最新留言
     *
     * @param page
     * @param count
     * @return
     */
    @RequestMapping("/getMessageByNew")
    @ResponseBody
    public campus getMessageByNew(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "9") Integer count) {
        if (page == null || page <= 0)
            page = 1;
        if (count == null || count < 0)
            count = 9;
        Page p = new Page(page, count);
        List<Message> list = messageService.getMessageByNew(p);
        return new campus(200, list);
    }

    /**
     * 点赞,未完成
     *
     * @param mid
     * @return
     */
    @RequestMapping("/addZanCount")
    @ResponseBody
    public campus addZanCount(Integer mid, String token) {
        if (mid <= 0)
            return new campus(400, null);
        jedis = jedisPool.getResource();
        String userName = jedis.get("token:" + token);
        if (userName == null)
            return new campus(403, null);
        return new campus(200, null);
    }

    /**
     * 取消点赞，未完成
     * @param mid
     * @param token
     * @return
     */
    @RequestMapping("/cencelZanCount")
    @ResponseBody
    public campus cencelZanCount(Integer mid, String token) {
        return null;
    }
}
