package com.foo.bar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultUserRatingManager implements UserRatingManager {

    @Autowired
    UserRatingDAO userRatingDAO;

    @Transactional("txName")
    public void updateAllUsers() {
        List<String> userIds = userRatingDAO.getAllUserIds();
        System.out.println(userIds);
        /*for (String userId : userIds) {
            userRatingDAO.
        }*/
    }
    
    @Transactional("txName")
    public void save(UserRating user) {
		userRatingDAO.save(user);
	}
}
