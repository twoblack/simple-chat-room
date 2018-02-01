package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name="liusong.relation")
public class Relation {
	@Column(name="id")
	private Integer id;
	@Column(name="username")
	private String username;
	@Column(name="friend")
	private String friend;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFriend() {
		return friend;
	}
	public void setFriend(String friend) {
		this.friend = friend;
	}
}
