package org.zerock.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.zerock.domain.BoardVO;

public interface BoardRepository extends CrudRepository<BoardVO, Long>{ //int 같은 기본 자료형은 못 씀

	//조회
	//public List<BoardVO> findByBnoGreaterThan(Long bno, Pageable pageable); //springframwork.data.domain 으로 
	public Page<BoardVO> findByBnoGreaterThan(Long bno, Pageable pageable); //springframwork.data.domain 으로 

	
	//검색
	//5개씩, 7이 들어가있는 제목, 1페이지 가지고 오기
	public List<BoardVO> findByTitleContainingAndBnoGreaterThan(String keyword,Long bno, Pageable pageable);
	//public List<BoardVO> findByTitleContaining(String keyword);
	
}
