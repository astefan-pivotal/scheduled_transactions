package com.foo.bar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UserRating {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	String userId;
	String name;
	String job;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public UserRating() {}

	public UserRating(String name, String job) {
		this.name = name;
		this.job = job;
	}

}
