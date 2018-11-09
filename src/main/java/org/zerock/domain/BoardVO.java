package org.zerock.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "tbl_board")
@Data
public class BoardVO {
	
	//프라이머리는 오토인크리먼트
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_board") //아이디를 자동으로 만드는 것, 제너레이터는 시퀀스 네임
	@SequenceGenerator(name="seq_board", sequenceName="SEQ_BOARD") //시퀀스를 만드는 것
	private Long bno;
	
	@Column(length=300)
	private String title;
	
	@Lob
	private String content;
	private String writer;
	
	@CreationTimestamp
	private LocalDateTime regdate;
	@UpdateTimestamp
	private LocalDateTime updatedate;

}
