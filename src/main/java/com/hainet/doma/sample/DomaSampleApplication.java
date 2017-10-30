package com.hainet.doma.sample;

import org.seasar.doma.jdbc.tx.TransactionManager;

public class DomaSampleApplication {

    public static void main(String[] args) {
        final PersonDao dao = new PersonDaoImpl();

        TransactionManager tm = DomaConfig.singleton().getTransactionManager();

        tm.required(() -> {
            Person person = new Person();
            person.id = null;
            person.name = "hainet";

            dao.insert(person);
            dao.findAll().forEach(it -> {
                System.out.println(it.id);
                System.out.println(it.name);
            });
        });
    }
}
