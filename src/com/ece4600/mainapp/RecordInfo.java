package com.ece4600.mainapp;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class RecordInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String roomName;//x,ªÚ’ﬂroom
	private String spotName;//y
	private List<WifiInfo> wifiInfos;

	
	public List<WifiInfo> getWifiInfos() {
		return wifiInfos;
	}

	public void setWifiInfos(List<WifiInfo> wifiInfos) {
		this.wifiInfos = wifiInfos;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getSpotName() {
		return spotName;
	}

	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}


	@Override
	public String toString() {
		return "RecordInfo [roomName=" + roomName + ", spotName=" + spotName
				+ ", wifiInfos=" + wifiInfos + "]";
	}


}
