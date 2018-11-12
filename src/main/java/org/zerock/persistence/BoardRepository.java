package org.zerock.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.zerock.domain.BoardVO;

public interface BoardRepository extends CrudRepository<BoardVO, Long>, QuerydslPredicateExecutor<BoardVO>{ //int 같은 기본 자료형은 못 씀
	
	@Query("select b from BoardVO b where bno >0 order by b.bno desc")
	public Page<BoardVO> getList(Pageable pageable);
	
	//제목 검색
	@Query("select b from BoardVO b where b.title like %:title% and bno >0 order by b.bno desc")
	public Page<BoardVO> getListByTitle(@Param("title") String title, Pageable pageable);
	
	//내용 검색
	@Query("select b from BoardVO b where b.content like %:content% and bno >0 order by b.bno desc")
	public Page<BoardVO> getListByContent(@Param("content") String title, Pageable pageable);
	
	//작성자 검색
		@Query("select b from BoardVO b where b.writer like %:writer% and bno >0 order by b.bno desc")
		public Page<BoardVO> getListByWriter(@Param("writer") String title, Pageable pageable);
	
	
	
	
	
	
	
	
	
	
	
	
	
	////////////////////////////////////////////////////////////////////
	/*
	//JPQL
	@Query("select b from BoardVO b where b.bno > 0") //엔티티 타입 쓰기
	public Page<BoardVO> getList(Pageable pageable);

	//조회
	//public List<BoardVO> findByBnoGreaterThan(Long bno, Pageable pageable); //springframwork.data.domain 으로 
	public Page<BoardVO> findByBnoGreaterThan(Long bno, Pageable pageable); //springframwork.data.domain 으로 

	
	//검색
	//5개씩, 7이 들어가있는 제목, 1페이지 가지고 오기
	public List<BoardVO> findByTitleContainingAndBnoGreaterThan(String keyword,Long bno, Pageable pageable);
	//public List<BoardVO> findByTitleContaining(String keyword);
	*/
}
