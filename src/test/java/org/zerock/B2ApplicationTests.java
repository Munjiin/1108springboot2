package org.zerock;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.Cleanup;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log //로그포제이는 없음. 문자열만 쓸 수 있음
public class B2ApplicationTests {
	
	@Setter(onMethod_=@Autowired)
	private DataSource ds;

	@Test
	public void contextLoads() {
	}
	
	@Test
	@SneakyThrows(Exception.class)//자동 익셉션
	public void testConnection() {
		
		@Cleanup Connection con = ds.getConnection(); //클린업이 오토클로즈
		log.info(""+con); //꼼수. 문자열 자동 변환
	}

}
