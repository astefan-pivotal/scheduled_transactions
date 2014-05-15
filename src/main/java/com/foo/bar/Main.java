package com.foo.bar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				PersistenceConfig.class);
		UserRatingManager userRatingManager = (UserRatingManager) ctx.getBean("userRatingManager");
		
		UserRating[] users = new UserRating[] {
				new UserRating("Michael", "Minstrel"),
				new UserRating("Linda", "Lyricist"),
				new UserRating("Pete", "Piano Tuner"), };
		
		for (UserRating user : users) {
			userRatingManager.save(user);
		}
		
		
	}

}
