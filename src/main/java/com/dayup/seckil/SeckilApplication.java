package com.dayup.seckil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.audit.AuditAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@Configuration
//@Import({
//	AopAutoConfiguration.class,
//	AuditAutoConfiguration.class
//})
public class SeckilApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeckilApplication.class, args);
	}
}
