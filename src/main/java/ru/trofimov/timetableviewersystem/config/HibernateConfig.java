package ru.trofimov.timetableviewersystem.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.trofimov.timetableviewersystem.model.*;

import javax.sql.DataSource;
import java.util.Properties;

@org.springframework.context.annotation.Configuration
@ComponentScan("ru.trofimov.timetableviewersystem")
@PropertySource("classpath:application.properties")
public class HibernateConfig {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver}")
    private String driver;
    @Value("${hibernate.dialect}")
    private String hibernateDialect;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateAuto;
    @Value("${hibernate.show.sql}")
    private String hibernateShowSqlStatus;

    @Bean
    public SessionFactory sessionFactory() {
        Configuration configuration = configuration();
        addAnnotatedClasses(configuration);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                applySetting(Environment.DATASOURCE, dataSource()).
                applySettings(hibernateProperties()).
                build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private void addAnnotatedClasses(Configuration configuration) {
        configuration.addAnnotatedClass(Classroom.class);
        configuration.addAnnotatedClass(Course.class);
        configuration.addAnnotatedClass(Group.class);
        configuration.addAnnotatedClass(Lesson.class);
        configuration.addAnnotatedClass(LessonSlot.class);
        configuration.addAnnotatedClass(User.class);
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.hbm2ddl.auto", hibernateAuto);
        properties.put("hibernate.show_sql", hibernateShowSqlStatus);
        return properties;
    }

    @Bean
    public Configuration configuration() {
        return new Configuration();
    }
}
