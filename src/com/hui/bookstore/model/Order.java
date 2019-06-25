package com.hui.bookstore.model;

import java.util.Date;
import java.util.List;

public class Order {
	private String id;
	private double money;
	private String receiverAddress;
	private String receiverName;
	private String receiverPhone;
	private int paystate;//支付状态，未支付为0，已支付为1
	private Date ordertime;
	private List<OrderItem> items;//用来存放订单下的所有
/*
 * 如果表时一个外键关系，一般设置成对象
 * 这样就可以直接拿到它的id或者其他我们想要的
 */
	private User user;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public int getPaystate() {
		return paystate;
	}

	public void setPaystate(int paystate) {
		this.paystate = paystate;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", money=" + money + ", receiverAddress=" + receiverAddress + ", receiverName="
				+ receiverName + ", receiverPhone=" + receiverPhone + ", paystate=" + paystate + ", ordertime="
				+ ordertime + ", user=" + user + "]";
	}
	
}
