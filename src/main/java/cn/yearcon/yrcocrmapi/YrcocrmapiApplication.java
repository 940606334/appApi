package cn.yearcon.yrcocrmapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class
})
public class YrcocrmapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(YrcocrmapiApplication.class, args);
	}
}
