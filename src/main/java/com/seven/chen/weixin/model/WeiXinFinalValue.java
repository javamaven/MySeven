package com.seven.chen.weixin.model;

public class WeiXinFinalValue {

    public static String APPID = "wxe2faf2f96884af73";
    
    public static String APPSECRT = "24fe9c513f37c6b8ce46ada69b933ae6";
    
    public static String ACCESS_TOKEN_URL ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRT" ;
    
    public static String MENU_ADD = " https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    public static String MSG_TEXT_TYPE = "text";
    public static String MSG_IMAGE_TYPE = "image";
    public static String MSG_VOICE_TYPE = "voice";
    public static String MSG_VIDEO_TYPE = "video";
    public static String MSG_SHORTVIDEO = "shortvideo";
    public static String MSG_LOCATION_TYPE = "location";
    public static String MSG_EVENT_TYPE = "event";
    
    
}
