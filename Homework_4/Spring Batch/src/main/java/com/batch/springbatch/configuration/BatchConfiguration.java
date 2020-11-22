package com.batch.springbatch.configuration;

import com.batch.springbatch.listener.JobCompletionNotificationListener;
import com.batch.springbatch.processor.LowBalanceUserNotifierProcessor;
import com.batch.springbatch.writer.NoOpItemWriter;
import com.batch.springbatch.model.User;
import com.batch.springbatch.mapper.UserRowMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Optional;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private UserRowMapper userRowMapper;

    @Autowired
    @Qualifier("InMemoryDataSource")
    private DataSource dataSource;

    @Bean
    public JdbcCursorItemReader<User> reader() {
        JdbcCursorItemReader<User>  reader = new JdbcCursorItemReader<>();
        reader.setSql("SELECT * FROM users WHERE balance < 10");
        reader.setDataSource(dataSource);
        reader.setRowMapper(userRowMapper);
        return reader;
    }

    @Bean
    public LowBalanceUserNotifierProcessor processor() {
        return new LowBalanceUserNotifierProcessor();
    }

    @Bean
    public Job noticateUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("notificateUserJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public NoOpItemWriter writer() {
        return new NoOpItemWriter();
    }


    @Bean
    public Step step1(NoOpItemWriter writer) {
        return stepBuilderFactory.get("step1")
                .<User, Optional<Void>> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

}
