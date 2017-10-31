package com.hainet.doma.sample.config;

import org.seasar.doma.SingletonConfig;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.Naming;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.H2Dialect;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;
import org.seasar.doma.jdbc.tx.TransactionManager;

import javax.sql.DataSource;

@SingletonConfig
public class DomaConfig implements Config {

    private static final DomaConfig CONFIG = new DomaConfig();

    private final Dialect dialect;

    private final LocalTransactionDataSource dataSource;

    private final Naming namingType;

    private final TransactionManager transactionManager;

    private DomaConfig() {
        dialect = new H2Dialect();
        dataSource = new LocalTransactionDataSource(
                new StringBuilder()
                        .append("jdbc:h2:mem:doma-sample;")
                        .append("INIT=CREATE TABLE person (id INT AUTO_INCREMENT, name CHAR, PRIMARY KEY (id))")
                        .toString(),
                "sa",
                null);
        namingType = Naming.SNAKE_UPPER_CASE;
        transactionManager = new LocalTransactionManager(dataSource.getLocalTransaction(getJdbcLogger()));
    }

    @Override
    public Dialect getDialect() {
        return dialect;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public Naming getNaming() {
        return namingType;
    }

    @Override
    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    public static DomaConfig singleton() {
        return CONFIG;
    }
}
