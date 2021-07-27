package org.formation;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Tp1Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Tp1Application.class, args);

	//	Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

	}

}
