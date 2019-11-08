package cn.jiweiqing.base;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.jiweiqing.base.mapper")
public class JjJavaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JjJavaServerApplication.class, args);
	}

}
