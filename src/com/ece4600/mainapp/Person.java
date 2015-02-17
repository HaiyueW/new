package com.ece4600.mainapp;

import java.io.Serializable;

public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	String times;

	public Person(String time) {
		super();
		this.times = time;
	}

	@Override
	public String toString() {
		return "Time [times=" + times + "]";
	}

}
