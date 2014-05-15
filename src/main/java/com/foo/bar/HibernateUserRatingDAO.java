package com.foo.bar;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateUserRatingDAO extends BaseDAO<UserRating, String> implements UserRatingDAO {

    public List<String> getAllUserIds() {
        List<String> result = new ArrayList<String>();
        Query q1 = getSession().createQuery("Select userId from UserRating");
        return q1.list();
    }

	public UserRating find(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserRating[] find(String... arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserRating getReference(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserRating[] getReferences(String... arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean removeById(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public void removeByIds(String... arg0) {
		// TODO Auto-generated method stub
		
	}

	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}
}
