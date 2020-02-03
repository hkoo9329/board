package com.leejuhyeok.board.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table
public class User {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long idx;
	
	@Column
	@NotNull
	public String nickName;
	
	@Column
	@NotNull
	public String password;
	
}
