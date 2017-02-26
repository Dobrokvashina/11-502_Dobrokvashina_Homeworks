package ru.itis.inform.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

import javax.sql.DataSource;

@Configuration
@ComponentScan("ru.itis.inform")
@PropertySource("jdbc.properties")
public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
        builder.addResource("\\ru.itis\\hibernate\\User.hbm.xml");
        builder.addResource("\\ru.itis\\hibernate\\Auto.hbm.xml");
        builder.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQL82Dialect");
        return builder.buildSessionFactory();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("jdbc.driver"));
        dataSource.setUrl(environment.getProperty("jdbc.url"));
        dataSource.setUsername(environment.getProperty("jdbc.username"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));
        return dataSource;
    }

}