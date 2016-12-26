package com.seven.chen.weixin.quartz;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.seven.chen.weixin.model.AccessToken;
import com.seven.chen.weixin.model.ErrorEntity;
import com.seven.chen.weixin.model.WeiXinContentText;
import com.seven.chen.weixin.model.WeiXinFinalValue;


public class RefressAccessTokenTask {
	
	public static String AccessToken = "NnPytG883SsLNTLsHZFiGnmpHlM7UMHYdKmheATI7hHhy0X_X8mvr8yOGyrHA828B68qH2KhVnpkZ2GEnNIFG-iZImI_UzeL39EPUoEml4oDRWaABARFK";
	
    public void refressToken(){

    	CloseableHttpClient client = null;
        HttpGet get = null;
        CloseableHttpResponse resp = null;
        
        try {
        client = HttpClients.createDefault();
        String strurl = WeiXinFinalValue.ACCESS_TOKEN_URL;
        strurl = strurl.replaceAll("APPID", WeiXinFinalValue.APPID);
        strurl = strurl.replaceAll("APPSECRT", WeiXinFinalValue.APPSECRT);
        System.out.println(strurl);
        URL url = new URL(strurl);
        URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
        get = new HttpGet(uri);
        resp = client.execute(get);
        int statusCode = resp.getStatusLine().getStatusCode();
        if(statusCode>=200&&statusCode<300){
            HttpEntity entity = resp.getEntity();
            String content = EntityUtils.toString(entity);
            System.out.println(content);
            
            try {
                AccessToken at =JSON.parseObject(content,AccessToken.class );
                
                WeiXinContentText.accessToken = at.getAccess_token();
                
                AccessToken = at.getAccess_token();
                
                System.out.println(at.getAccess_token()+"..."+at.getExpires_in());
            }
            catch (Exception e) {
                e.printStackTrace();
                ErrorEntity errorentity = (ErrorEntity)JSON.parseObject(content,ErrorEntity.class );
                System.out.println("获取token异常 代码："+errorentity.getErrcode()+","+errorentity.getErrmsg());
                refressToken();
            }
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
        }finally{
            try {
            if(resp!=null){

                    resp.close();
                }
            }catch (IOException e) {
                    e.printStackTrace();
            }
            
            try {
            if(client!=null){
                client.close();
                }
            }catch (IOException e) {
                    e.printStackTrace();
            }  
        }
    }

}
