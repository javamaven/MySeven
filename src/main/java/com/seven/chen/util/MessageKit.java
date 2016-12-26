package com.seven.chen.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.xml.sax.DocumentHandler;

import com.seven.chen.weixin.model.WeiXinFinalValue;

public class MessageKit {
	
	//定义一个静态消息回复的map
	private static Map<String,String> replyMsgMaps = new HashMap<String,String>();
	static {
		replyMsgMaps.put("你最爱谁", "黄斌");
		replyMsgMaps.put("你好", "你也好");
		replyMsgMaps.put("肚子饿了", "吃饭去");
		
	}
	
	public static Map<String,String> reqMsg2Map(HttpServletRequest req){
		try {
			String xml = req2Xml(req);
			Map<String,String> maps = new HashMap<String,String>();
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			List<Element> eles = root.elements();
			for(Element e: eles){
				
				maps.put(e.getName(), e.getTextTrim());
				
			}
			return maps;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String req2Xml(HttpServletRequest req) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
    	String str = null;
    	StringBuffer sb = new StringBuffer();
    	while((str = br.readLine())!=null){
    		
    		System.out.println(str);
    		sb.append(str);
    		
    	}
    	return sb.toString();
	}

	public static String handlerMsg(Map<String, String> maps) throws IOException {
		// TODO Auto-generated method stub
		String msgType = maps.get("MsgType");
		//对消息类型进行判断
		if(WeiXinFinalValue.MSG_EVENT_TYPE.equals(msgType)){
			
		}else if(WeiXinFinalValue.MSG_TEXT_TYPE.equals(msgType)){
			
			//对文本消息进行处理
			return textTypeHandler(maps);
			
		}else if(WeiXinFinalValue.MSG_IMAGE_TYPE.equals(msgType)){
			
		}else if(WeiXinFinalValue.MSG_VOICE_TYPE.equals(msgType)){
			
		}else if(WeiXinFinalValue.MSG_VIDEO_TYPE.equals(msgType)){
			
		}else if(WeiXinFinalValue.MSG_SHORTVIDEO.equals(msgType)){
			
		}else if(WeiXinFinalValue.MSG_LOCATION_TYPE.equals(msgType)){
			
		}
		return null;
	}

	private static String textTypeHandler(Map<String, String> maps) throws IOException {
		// TODO Auto-generated mehod stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("ToUserName",maps.get("FromUserName"));
		map.put("FromUserName", maps.get("ToUserName"));
		map.put("CreateTime",new Date().toString() );
		map.put("MsgType", WeiXinFinalValue.MSG_TEXT_TYPE);
		String replycontent = "乖,别调皮哈！重新输入";
		String content = maps.get("Content");
		
		
		System.out.println("........................"+content);
		
		if(replyMsgMaps.containsKey(content)){
			replycontent = replyMsgMaps.get(content);
		}
		map.put("Content",replycontent);
		
		return map2Xml(map);
	}

	private static String map2Xml(Map<String, String> map) throws IOException {
		Document document = DocumentHelper.createDocument();
		Element element = document.addElement("xml");
		Set<String> keys = map.keySet();
		for(String key:keys){
			element.addElement(key).addText(map.get(key));
		}
		StringWriter sw = new StringWriter();
		document.write(sw);
		return sw.toString();
	}
}
