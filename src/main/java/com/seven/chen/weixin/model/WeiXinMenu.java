package com.seven.chen.weixin.model;

import java.util.List;

public class WeiXinMenu {
	private int id;
	private String name;
	private String type;
	private String url;
	private String key;
	private Integer pid;
	private List<WeiXinMenu> sub_button;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String nanme) {
		this.name = nanme;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public List<WeiXinMenu> getSub_button() {
		return sub_button;
	}
	public void setSub_button(List<WeiXinMenu> sub_button) {
		this.sub_button = sub_button;
	}

}
