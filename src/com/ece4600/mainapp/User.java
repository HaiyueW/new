package com.ece4600.mainapp;

import org.kymjs.aframe.database.annotate.Id;

public class User {

    @Id()
    private int id;
    private int num;
    private String x;
    private String y;
    private int router1;
    private int router2;
    private int router3;
    private int router4;
    private int router5;
    private int router6;
    private String room;
    
    
    public String getRoom() {
		return room;
	}




	public void setRoom(String room) {
		this.room = room;
	}




	public User() {
		super();
	}




	@Override
	public String toString() {
		return "User [id=" + id + ", num=" + num + ", x=" + x + ", y=" + y + ", router1=" + router1 + ", router2=" + router2 + ", router3=" + router3 + ", router4=" + router4 + ", router5=" + router5
				+ ", router6=" + router6 + ", room=" + room + "]";
	}




	public User(int id, int num, String x, String y, int router1, int router2, int router3, int router4, int router5, int router6, String room) {
		super();
		this.id = id;
		this.num = num;
		this.x = x;
		this.y = y;
		this.router1 = router1;
		this.router2 = router2;
		this.router3 = router3;
		this.router4 = router4;
		this.router5 = router5;
		this.router6 = router6;
		this.room = room;
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public String getX() {
		return x;
	}


	public void setX(String x) {
		this.x = x;
	}


	public String getY() {
		return y;
	}


	public void setY(String y) {
		this.y = y;
	}


	public int getRouter1() {
		return router1;
	}


	public void setRouter1(int router1) {
		this.router1 = router1;
	}


	public int getRouter2() {
		return router2;
	}


	public void setRouter2(int router2) {
		this.router2 = router2;
	}


	public int getRouter3() {
		return router3;
	}


	public void setRouter3(int router3) {
		this.router3 = router3;
	}


	public int getRouter4() {
		return router4;
	}


	public void setRouter4(int router4) {
		this.router4 = router4;
	}


	public int getRouter5() {
		return router5;
	}


	public void setRouter5(int router5) {
		this.router5 = router5;
	}


	public int getRouter6() {
		return router6;
	}


	public void setRouter6(int router6) {
		this.router6 = router6;
	}
    

//    AP1: e8:de:27:7b:97:1c
//    AP2: e8:de:27:36:52:ee
//    AP3: e8:de:27:7b:97:42
//    AP4: e8:de:27:36:54:2e
//    AP5: e8:de:27:7b:97:52
//    AP6: e8:de:27:36:54:40



}
