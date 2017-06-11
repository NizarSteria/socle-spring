package tn.demo.component;

import javax.sql.DataSource;


import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tn.demo.model.Person;

@Component
public class PersonItemWriter extends JdbcBatchItemWriter<Person> implements ItemWriter<Person> {

    @Autowired
    public PersonItemWriter(DataSource dataSource) {
        this.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        this.setSql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)");
        this.setDataSource(dataSource);
    }
}