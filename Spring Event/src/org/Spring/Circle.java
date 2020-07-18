package org.Spring;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component//this annotation is used to declare this class as a bean instead of defining this class as bean in spring.xml
public class Circle implements Shape,ApplicationEventPublisherAware {
	private Point center;
	private MessageSource messageSource;
	//ApplicationEventPublisher is a interface implemented by application context written in main method but its a good practice to initialize it to a interface than a class
	private ApplicationEventPublisher publisher;


	public Point getCenter() {
		return center;
	}

	//if the name parameter not mentioned in the @Resource then 
	@Resource (name="pointA")
	public void setCenter(Point center) {
		this.center = center;
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}
	@Autowired
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Override
	public void draw() {
		System.out.println("Drawing Circle");
		System.out.println(this.messageSource.getMessage("points",new Object[]{center.getX(),center.getY()},"Default points",null));
		DrawEvent draw=new DrawEvent(this);
		publisher.publishEvent(draw);
	}
	
	@PostConstruct
	public void initialize() {
		System.out.println("Initialized circle");
	}
	@PreDestroy
	public void destroy() {
		System.out.println("Destroyed circle");
	}

	@Override
	//publisher what we receive is the application context itself
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher=publisher;
	}
	
}
