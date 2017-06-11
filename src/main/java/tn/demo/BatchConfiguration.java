package tn.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tn.demo.model.Person;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    @Autowired
    private ItemReader<Person> reader;

    @Autowired
    private ItemProcessor<Person, Person> processor;

    @Autowired
    private JobExecutionListener listener;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private ItemWriter<Person> writer;

    @Bean
    public Job importUserJob(Step s1) {
        return jobs.get("importUserJob").incrementer(new RunIdIncrementer()).listener(listener).flow(s1).end().build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1").<Person, Person>chunk(10).reader(reader).processor(processor).writer(writer).build();
    }


}