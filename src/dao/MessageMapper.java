package dao;

import java.util.List;

import pojo.Message;
import pojo.Page;

public interface MessageMapper
{

	public void publishMessage(Message message);

	public List<Message> getMessageByNew(Page p);

	public void addClickCount(Integer mid);

	public void addZanCount(Integer mid);
}