package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MessageMapper;
import pojo.Message;
import pojo.Page;

/**
 * @author yanni
 * @time   2017年7月23日 下午7:18:15
 */
@Service
public class MessageService
{
	@Autowired
	private MessageMapper messageMapper;

	public void publishMessage(Message message)
	{
		messageMapper.publishMessage(message);
	}

	public List<Message> getMessageByNew(Page p)
	{
		List<Message> list=messageMapper.getMessageByNew(p);
		for(Message message:list)
		{
			addClickCount(message.getMid());
			message.setClickCount(message.getClickCount()+1);
			if(message.getSecret()==1)
				message.setUserName(null);
		}
		return list;
	}

	public void addClickCount(Integer mid)
	{
		messageMapper.addClickCount(mid);
	}

	public void addZanCount(Integer mid)
	{
		messageMapper.addZanCount(mid);
	}
}
