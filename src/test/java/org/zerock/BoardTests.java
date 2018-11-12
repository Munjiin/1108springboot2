package org.zerock;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.QBoardVO;
import org.zerock.persistence.BoardRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Setter;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class BoardTests {
	
	@Setter(onMethod_=@Autowired)
	private BoardRepository boardRepository;
	
	//querydsl
	@Test
	public void testDynamic() {
		String[] types= {"t","c"};
		String keyword="10";
		
		BooleanBuilder builder = new BooleanBuilder();
		
		QBoardVO board = QBoardVO.boardVO;
		
		/*if(type.equals("t")) {
			builder.and(
				board.title.contains(keyword)
				)
			.and(board.bno.gt(0));
		}*/	
		builder.and(board.bno.gt(0));
		
		BooleanExpression[] arr = new BooleanExpression[types.length];
		for(int i=0; i<types.length; i++) {
			String type = types[i];
			BooleanExpression cond = null;
			
			if(type.equals("t")) {
				cond = board.title.contains(keyword);
			}else if(type.equals("c")) {
				cond = board.content.contains(keyword);
			}
			
			arr[i] = cond;
		}
		
		builder.andAnyOf(arr);
		
		
		Page<BoardVO> result = boardRepository.findAll(builder, PageRequest.of(0,10, Sort.Direction.DESC,"bno"));
		
		log.info(""+result);
	}
	
	//작성자 검색
			@Test
			public void testWriter() {
				
				Page<BoardVO> result = boardRepository.getListByWriter("3", PageRequest.of(0,10));
				log.info("" +result);
				
				result.getContent().forEach(vo->log.info(""+vo));
				
			}
	
	//내용검색
		@Test
		public void testContent() {
			
			Page<BoardVO> result = boardRepository.getListByContent("10", PageRequest.of(0,10));
			log.info("" +result);
			
			result.getContent().forEach(vo->log.info(""+vo));
			
		}
	
	
	//제목검색
	@Test
	public void testTitle() {
		
		Page<BoardVO> result = boardRepository.getListByTitle("10", PageRequest.of(0,10));
		log.info("" +result);
		
		result.getContent().forEach(vo->log.info(""+vo));
		
	}
	
	@Test
	public void testList() {
		Page<BoardVO> result = boardRepository.getList(PageRequest.of(0,10));
		log.info("" +result);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	///////////////////////////////////////////////////////////////////////////////
	
	/*
	@Test
	public void testQ1() {
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC,"bno");
		Page<BoardVO> result = boardRepository.getList(pageable);
		
		log.info(""+result);
		log.info("=========================================");
		log.info("TOTAL PAGES: " + result.getTotalPages());
		log.info("PAGE: " + result.getNumber());
		log.info("NEXT: " + result.hasNext());
		log.info("P NEXT: " +result.nextPageable());
		log.info("PREV: " + result.hasPrevious());
		log.info("P PREV: " +result.previousPageable());
		
		result.getContent().forEach(vo->log.info(""+vo));
	}
	
	@Test
	public void testFind2() {
		
		//boardRepository.findByTitleContaining("2").forEach(vo->log.info(""+vo));
		//5개씩, 7이 들어가있는 제목, 1페이지 가지고 오기
		Pageable pageable = PageRequest.of(0, 5,Sort.Direction.DESC,"bno");

		boardRepository.findByTitleContainingAndBnoGreaterThan("7",0L,pageable).forEach(vo->log.info(""+vo));
	}
	
	@Test
	public void testFind1() {
		Pageable pageable = PageRequest.of(0, 10,Sort.Direction.DESC,"bno"); //페이지 0부터 10개씩
		Page<BoardVO> result = boardRepository.findByBnoGreaterThan(0L,pageable);
		log.info(""+result);
		log.info("=========================================");
		log.info("TOTAL PAGES: " + result.getTotalPages());
		log.info("PAGE: " + result.getNumber());
		log.info("NEXT: " + result.hasNext());
		log.info("P NEXT: " +result.nextPageable());
		log.info("PREV: " + result.hasPrevious());
		log.info("P PREV: " +result.previousPageable());
		
		result.getContent().forEach(vo->log.info(""+vo));
		
		
		//boardRepository.findByBnoGreaterThan(0L,pageable).forEach(vo->log.info(""+vo));
	}
	*/
	
	
	//삭제
	@Test
	public void testDelete() {
		boardRepository.deleteById(10L);
	}
	
	//수정
	/*@Test
	public void testUpdate() {
		boardRepository.findById(10L).ifPresent(vo->{
			vo.setContent("수정된 제목 입니다");
			boardRepository.save(vo);
			});
	}*/
	
	//파인드바이하지 않는 수정
	@Test
	public void testUpdate() {
		BoardVO vo = new BoardVO();
		vo.setBno(10L);
		vo.setTitle("제목 10 수정");
		vo.setContent("내용 10 수정");
		vo.setWriter("user10");
		
		boardRepository.save(vo);
	}
	
	
	@Test
	public void testRead() {
		//Optional<BoardVO> result = boardRepository.findById(10L); //옵셔널,,널포인트익셉션을 피하는 방법
		
		//result.ifPresent(vo->log.info(""+vo));//여기 안걸리면 널
		
		boardRepository.findById(10L).ifPresent(vo->log.info(""+vo)); //w줄여서
	}
	
	@Test
	public void testInsert() {
		//IntStream.range(0,100).forEach(i -> {
		IntStream.range(100,1000).forEach(i -> {
			BoardVO vo = new BoardVO();
			vo.setTitle("jiin" + i);
			vo.setContent("jiin" + i);
			vo.setWriter("user" + (i%10));
			
			boardRepository.save(vo);
					
		});
	}

}
