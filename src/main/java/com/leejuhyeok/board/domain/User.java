package com.leejuhyeok.board.domain;

import com.leejuhyeok.board.domain.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class User{
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	
	@Column(nullable = false)
	private String nickName;
	
	@Column
	private String email;
	
	@Column
	private String picture;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;
	
	@Builder
	public User(String nickName, String email, String picture, Role role) {
		this.nickName = nickName;
		this.email = email;
		this.picture = picture;
		this.role = role;
	}
	
	public User update(String nickName, String picture) {
		this.nickName = nickName;
		this.picture = picture;
		return this;
	}

	public String getRoleKey(){
		return this.role.getKey();
	}

	
}
