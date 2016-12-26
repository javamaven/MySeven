package test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.seven.chen.weixin.model.AccessToken;
import com.seven.chen.weixin.model.WeiXinContentText;
import com.seven.chen.weixin.model.WeiXinFinalValue;
import com.seven.chen.weixin.model.WeiXinMenu;
import com.seven.chen.weixin.quartz.RefressAccessTokenTask;

public class WeiXinTest {

    private static final Feature AccessToekn = null;

    @Test
    public void testHttpClient(){
        
        try {
        CloseableHttpClient client = HttpClients.createDefault();
        String strurl = WeiXinFinalValue.ACCESS_TOKEN_URL;
        strurl = strurl.replaceAll("APPID", WeiXinFinalValue.APPID);
        strurl = strurl.replaceAll("APPSECRT", WeiXinFinalValue.APPSECRT);
        System.out.println(strurl);
        URL url = new URL(strurl);
        URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
        HttpGet get = new HttpGet(uri);
        CloseableHttpResponse resp;
        resp = client.execute(get);
        int statusCode = resp.getStatusLine().getStatusCode();
        if(statusCode>=200&&statusCode<300){
            HttpEntity entity = resp.getEntity();
            String content = EntityUtils.toString(entity);
            System.out.println(content);
            AccessToken at =JSON.parseObject(content,AccessToken.class );
            System.out.println(at.getAccess_token()+"..."+at.getExpires_in());
            
            WeiXinContentText.accessToken = at.getAccess_token();
            
        }
        }
        catch (ClientProtocolException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    

    public void testMenu(){
 /*   	
    	try {
			List<WeiXinMenu> wms = new ArrayList<WeiXinMenu>();
			WeiXinMenu wmone = new WeiXinMenu();
			wmone.setId(1);
			wmone.setNanme("学习网站");
			wmone.setKey("view");
			wmone.setUrl("www.baidu.com");
			wms.add(wmone);
			WeiXinMenu wm2 = new WeiXinMenu();
			List<WeiXinMenu> wxsub = new ArrayList<WeiXinMenu>();
			wm2.setNanme("测试子菜单");
			wmone = new WeiXinMenu();
			wmone.setId(2);
			wmone.setNanme("事件测试");
			wmone.setKey("0001");
			wmone.setType("click");
			wxsub.add(wmone); 
			wmone = new WeiXinMenu();
			wmone.setId(3);
			wmone.setNanme("事件saomiao");
			wmone.setKey("0002");
			wmone.setType("click");
			wxsub.add(wmone);  
			Map<String,List<WeiXinMenu>>maps = new HashMap<String,List<WeiXinMenu>>();
			maps.put("button",wxsub);
			String json = JSON.toJSONString(maps);
			CloseableHttpClient client= HttpClients.createDefault();
			String url = WeiXinFinalValue.MENU_ADD;
			System.out.println(WeiXinContentText.accessToken);
			url = url.replace("ACCESS_TOKEN","");

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
     	
     	*/
    	
    }
    
}
