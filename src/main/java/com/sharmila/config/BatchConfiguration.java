package com.sharmila.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.sharmila.config.JobCompletionNotificationListener;
import com.sharmila.domain.MsiSdn;
import com.sharmila.processor.MsiSdnItemProcessor;

//@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	@Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    /**
     * <p>Spring batch implementation,still in progress</p>
     * @return
     */
 // tag::readerwriterprocessor[]
    @Bean
    public FlatFileItemReader<MsiSdn> reader() {
    	System.out.println("******* batch read file ");
        return new FlatFileItemReaderBuilder<MsiSdn>()
            .name("personItemReader")
            .resource(new ClassPathResource("50M_msisdns992.csv"))
            .delimited()
            .names(new String[]{ "msisdn"})
            .fieldSetMapper(new BeanWrapperFieldSetMapper<MsiSdn>() {{
                setTargetType(MsiSdn.class);
            }})
            .build();
    }

   

    @Bean
    public JdbcBatchItemWriter<MsiSdn> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<MsiSdn>()
            .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
            .sql("INSERT INTO tableMSISDNV1 (msisdn) VALUES (:msisdn)")
            .dataSource(dataSource)
            .build();
    }
    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .flow(step1)
            .end()
            .build();
    }
    @Bean
    public MsiSdnItemProcessor processor() {
        return new MsiSdnItemProcessor();
    }
    @Bean
    public Step step1(JdbcBatchItemWriter<MsiSdn> writer) {
        return stepBuilderFactory.get("step1")
            .<MsiSdn, MsiSdn> chunk(10)
            .reader(reader())
            .processor(this.processor())
            .writer(writer)
            .build();
    }
    // end::jobstep[]

}
