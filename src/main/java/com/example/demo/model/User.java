package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "liusong.user")
public class User {
	@Column(name="id")
    private Integer id;
	@Column(name="nick_name")
    private String nickName;
	@Column(name="password")
    private String password;
	@Column(name="register_data")
    private String registerData;
	@Column(name="username")
    private String username;

	public User() {
		super();
	}

	public User(String nickName, String password, String username) {
		super();
		this.nickName = nickName;
		this.password = password;
		this.username = username;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRegisterData() {
        return registerData;
    }

    public void setRegisterData(String registerData) {
        this.registerData = registerData == null ? null : registerData.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

}