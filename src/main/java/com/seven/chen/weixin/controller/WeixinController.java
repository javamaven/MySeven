package com.seven.chen.weixin.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.seven.chen.util.MessageKit;
import com.seven.chen.util.SecurityKit;
import com.seven.chen.weixin.model.WeiXinContentText;
import com.seven.chen.weixin.model.WeiXinFinalValue;
import com.seven.chen.weixin.model.WeiXinMenu;
import com.seven.chen.weixin.quartz.RefressAccessTokenTask;

@RequestMapping("/")
@Controller
public class WeixinController {

   public static String tokern = "qazxsw";
    
    @RequestMapping(value="/wget",method=RequestMethod.GET)
    public void init(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        
        System.out.println(".........................................................................");
        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");
        System.out.println("signature="+signature);
        System.out.println("echostr="+echostr);
        String arrs[] = {WeixinController.tokern,nonce,timestamp};
        Arrays.sort(arrs);
        System.out.println("arrs="+arrs.length);       
        StringBuffer sb = new StringBuffer();
        for(String a: arrs){
            sb.append(a);
        }
        System.out.println("sb="+sb.toString()); 
        String sha1 = SecurityKit.sha1(sb.toString());
        System.out.println("sha1="+sha1);        
        System.out.println(sha1.equals(signature));
        if(sha1.equals(signature)){
            resp.getWriter().println(echostr);
        }
    } 
    
    @RequestMapping(value="/wget",method=RequestMethod.POST)
    public void getInfo(HttpServletRequest req,HttpServletResponse resp) throws IOException{
    	Map<String,String> maps = MessageKit.reqMsg2Map(req);
    
    	System.out.println(maps);

    	//根据传过来的信息  处理后回复信息
    	String content = MessageKit.handlerMsg(maps);
    	
    	//设置req消息头
    	resp.setContentType("application/xml;charset=UTF-8");
    	resp.setCharacterEncoding("UTF-8");
    	resp.getWriter().write(content);
    
    }
	
    @RequestMapping("/access")
    public void testAccessToekn(HttpServletRequest req,HttpServletResponse resp) throws IOException{
        
        resp.getWriter().println(WeiXinContentText.accessToken);

    }
    
    @RequestMapping("/create")
    public void createMenu(){
    	
    	try {
			List<WeiXinMenu> wms = new ArrayList<WeiXinMenu>();
			WeiXinMenu wmone = new WeiXinMenu();
			wmone.setId(1);
			wmone.setName("点击右边");
			wmone.setType("view");
			wmone.setKey("0001");
			wmone.setUrl("http://www.ejbang.com/");
			wms.add(wmone);
			WeiXinMenu wm2 = new WeiXinMenu();
			List<WeiXinMenu> wxsub = new ArrayList<WeiXinMenu>();
			wm2.setId(2);
			wm2.setName("有惊喜");
			wmone = new WeiXinMenu();
			wmone.setId(3);
			wmone.setPid(2);
			wmone.setName("我爱黄斌 ");
			wmone.setKey("0002");
			wmone.setType("click");
			wxsub.add(wmone);
			wmone = new WeiXinMenu();
			wmone.setId(4);
			wmone.setPid(2);
			wmone.setName("很爱");
			wmone.setKey("0003");
			wmone.setType("pic_weixin");
			wxsub.add(wmone);
			wm2.setSub_button(wxsub);
			wmone = new WeiXinMenu();
			wmone.setId(5);
			wmone.setPid(2);
			wmone.setName("非常爱");
			wmone.setKey("0003");
			wmone.setType("pic_weixin");
			wxsub.add(wmone);
			wm2.setSub_button(wxsub);
			wms.add(wm2);
			Map<String,List<WeiXinMenu>>maps = new HashMap<String,List<WeiXinMenu>>();
			maps.put("button",wms);
			System.out.println(maps);
			String json = JSON.toJSONString(maps);
			System.out.println(json);
			CloseableHttpClient client= HttpClients.createDefault();
			String url = WeiXinFinalValue.MENU_ADD;
			System.out.println(WeiXinContentText.accessToken);
			url = url.replace("ACCESS_TOKEN",RefressAccessTokenTask.AccessToken);

	        URL strurl = new URL(url);
	        URI uri = new URI(strurl.getProtocol(), strurl.getHost(), strurl.getPath(), strurl.getQuery(), null);			
			System.out.println(url);
			HttpPost post = new HttpPost(uri);
			System.out.println(url);
			post.addHeader("Content-Type","application/json");
			StringEntity entity = new StringEntity(json,ContentType.create("application/json", "utf-8"));
			post .setEntity(entity);
			CloseableHttpResponse resp = client.execute(post);
			
			int sc = resp.getStatusLine().getStatusCode();
			
			if(sc>=200&&sc<=300){
				System.out.println(EntityUtils.toString(resp.getEntity()));
			}
		} catch (UnsupportedCharsetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
    }
}
