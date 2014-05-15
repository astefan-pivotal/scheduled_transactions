package com.foo.bar;

import java.io.Serializable;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;

@Repository
public class BaseDAO<T, ID extends Serializable> extends GenericDAOImpl<T, ID> {

    @Autowired
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    public void setTopAndForUpdate(int top, Query query){
        query.setLockOptions(LockOptions.UPGRADE);
        query.setFirstResult(0);
        query.setMaxResults(top);
    }
}
