package org.Spring;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SuppressWarnings("deprecation")
public class DrawingApp {

	public static void main(String[] args) {
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
		context.registerShutdownHook();
		Shape shape=(Shape) context.getBean("circle");
		shape.draw();
	// YOu can either print in main method or you can create messageSource interface to do the job refer circle class
	//	System.out.println(context.getMessage("name",null,"Default greeting",null));
	}

}
