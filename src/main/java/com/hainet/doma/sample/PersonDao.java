package com.hainet.doma.sample;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;

import java.util.List;

@Dao(config = DomaConfig.class)
public interface PersonDao {

    @Select
    List<Person> findAll();

    @Insert
    int insert(Person person);
}
