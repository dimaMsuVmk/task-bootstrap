package ru.ivanov.bootmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.ivanov.bootmvc.util.Init;

@SpringBootApplication
public class ProjectNameBootMvcApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ProjectNameBootMvcApplication.class, args);
		Init init = context.getBean(Init.class);
		init.initilize();
	}

}
