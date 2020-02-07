package com.leejuhyeok.board.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table
public class Comment {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Board board;

    @OneToOne
    private User user;

    @Column
    private LocalDateTime date;

    @Builder
    public Comment(String comment, User user, Board board, LocalDateTime date) {
        this.comment = comment;
        this.user = user;
        this.board = board;
        this.date = date;
    }

    public void setCreateDateTime() {
        this.date = LocalDateTime.now();
    }

}
