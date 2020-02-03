package com.leejuhyeok.board.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table
public class Board {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long idx;
	
	@Column
	@NotNull
	public String title;
	
	@Column
	@NotNull
	public String userName;
	
	@Column
	@NotNull
	public String date;
	
	@Column
	@NotNull
	public Long views;
	
	
	@Builder
	public Board(String title, String userName, String date) {
		this.title = title;
		this.userName = userName;
		this.date = date;
		this.views = 0L;
	}
	
	
	
	
	
	
	

}
