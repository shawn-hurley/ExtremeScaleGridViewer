package com.shawnhurley;

import java.util.Date;
import java.util.GregorianCalendar;
import java.lang.Integer;
public class Foo {
	private int hello = 12;
	private String hw = "Hello World";
	private Bar bar = new Bar();
	private String Testing = "Testing thirteen";
	@SuppressWarnings("deprecation")
	private Date date = new Date(86, 12, 19);
	private Integer somewhittyname = 1234;
	public int getHello() {
		return hello;
	}
	public void setHello(int hello){
		this.hello = hello;
	}
	public String getHw(){
		return hw;
	}
	public void setHw(String hw){
		this.hw = hw;
	}
	public Bar getBar(){
		return bar;
	}
	public void setBar(Bar bar){
		this.bar = bar;
	}
	public String getTesting(){
		return Testing;
	}
	public void setTesting(String Testing){
		this.Testing = Testing;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getSomewhittyname() {
		return somewhittyname;
	}
	public void setSomewhittyname(Integer somewhittyname) {
		this.somewhittyname = somewhittyname;
	}

	
}
