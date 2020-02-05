package com.leejuhyeok.board.domain;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class SessionUser  implements Serializable{
	private String nickName;
	private String email;
	private String picture;
	
	public SessionUser(User user) {
		this.nickName = user.getNickName();
		this.email = user.getEmail();
		this.picture = user.getPicture();
	}
}
