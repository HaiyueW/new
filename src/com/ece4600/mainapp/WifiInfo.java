package com.ece4600.mainapp;

import java.io.Serializable;

public class WifiInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String bssid;
	private int level;
	
	
	public WifiInfo() {
		super();
	}
	public WifiInfo(String bssid, int level) {
		super();
		this.bssid = bssid;
		this.level = level;
	}
	public String getBssid() {
		return bssid;
	}
	public void setBssid(String bssid) {
		this.bssid = bssid;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "WifiInfo [bssid=" + bssid + ", level=" + level + "]";
	}
	
	
}
