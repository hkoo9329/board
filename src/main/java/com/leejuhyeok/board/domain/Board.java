package com.leejuhyeok.board.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
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
	private Long idx;
	
	@Column
	@NotBlank
	private String title;
	
	@Lob
	@Column
	private String content; 
	
	@Column
	private String userName;
	
	
	@Column
	private LocalDateTime date;
	
	@Column
	private Long views;
	
	
	@Builder
	public Board(String title, String userName, String content, LocalDateTime date) {
		this.title = title;
		this.content = content;
		this.userName = userName;
		this.date = date;
		this.views = 0L;
	}
	
	public void setCreateDateTime() {
		this.date = LocalDateTime.now();
	}
	public void setViews(Long views) {
		this.views = views;
	}
	
	public void update(Board newBoard) {
		this.title = newBoard.title;
		this.content = newBoard.content;
		
	}
	
	
	
	
	
	
	
	
	

}
