package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 自定义工具类
 * @author yanni
 * @time   2017年7月21日 下午16:59:53
 */
public class yanni
{
	// 定义jackson对象
	private static final ObjectMapper MAPPER=new ObjectMapper();

	/**
	 * 将对象转换成json字符串
	 * 
	 * @param data
	 * @return
	 */
	public static String objectToJson(Object data)
	{
		try
		{
			String string=MAPPER.writeValueAsString(data);
			return string;
		}
		catch(JsonProcessingException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json结果集转化为对象
	 * 
	 * @param jsonData
	 *            json数据
	 * @param *clazz
	 *            对象中的object类型
	 * @return
	 */
	public static <T> T jsonToPojo(String jsonData ,Class<T> beanType)
	{
		try
		{
			T t=MAPPER.readValue(jsonData,beanType);
			return t;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json数据转换成pojo对象list
	 * @param jsonData
	 * @param beanType
	 * @return
	 */
	public static <T> List<T> jsonToList(String jsonData ,Class<T> beanType)
	{
		JavaType javaType=MAPPER.getTypeFactory()
				.constructParametricType(List.class,beanType);
		try
		{
			List<T> list=MAPPER.readValue(jsonData,javaType);
			return list;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * map转Bean
	 * @param map
	 * @param clazz
	 * @return
	 */
	public static <T> T toBean(Map map ,Class<T> clazz)
	{
		try
		{
			/*
			 * 1. 通过参数clazz创建实例
			 * 2. 使用BeanUtils.populate把map的数据封闭到bean中
			 */
			T bean=clazz.newInstance();
			ConvertUtils.register(new DateConverter(),java.util.Date.class);
			BeanUtils.populate(bean,map);
			return bean;
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * 自动生成32位UUID字符串
	 * @return
	 */
	public static String UUID()
	{
		return UUID.randomUUID().toString().replaceAll("-","");
	}
}

class DateConverter implements Converter
{

	@SuppressWarnings ("rawtypes")
	@Override
	public Object convert(Class type ,Object value)
	{
		if(value==null)
			return null;// 如果要转换成值为null，那么直接返回null
		if(!(value instanceof String))
		{// 如果要转换的值不是String，那么就不转换了，直接返回
			return value;
		}
		String val=(String)value;// 把值转换成String

		// 使用SimpleDateFormat进行转换
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			return sdf.parse(val);
		}
		catch(ParseException e)
		{
			throw new RuntimeException(e);
		}
	}
}