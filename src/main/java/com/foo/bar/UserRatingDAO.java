package com.foo.bar;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface UserRatingDAO extends GenericDAO<UserRating, String> {
    public void deleteAll();
    public List<String> getAllUserIds();
}
