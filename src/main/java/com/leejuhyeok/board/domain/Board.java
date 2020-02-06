package com.leejuhyeok.board.domain;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

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
	private LocalDateTime date;
	
	@Column
	private Long views;
	
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	
	
	@Builder
	public Board(String title, String userName, String content, LocalDateTime date, Long views,
			User user) {
		this.title = title;
		this.content = content;
		this.date = date;
		this.user = user;
		this.views = views;
	}
	
	public void setCreateDateTime() {
		this.date = LocalDateTime.now();
	}
	public void setViews(Long views) {
		this.views = views;
	}
	
	public Board update(Board newBoard) {
		this.title = newBoard.title;
		this.content = newBoard.content;
		
		return this;
		
	}
	
	
	
	
	
	
	
	
	

}
