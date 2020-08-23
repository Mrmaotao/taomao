package com.mt.taomao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.mt.taomao"})
@MapperScan("com.mt.taomao.dao")
public class TaomaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaomaoApplication.class, args);
	}

}
