package com.andersenlab.adamovichjr.config;

import com.andersenlab.adamovichjr.dao.CrudDao;
import com.andersenlab.adamovichjr.dao.impl.SportCarDao;
import com.andersenlab.adamovichjr.dao.repository.SportCarRepository;
import com.andersenlab.adamovichjr.model.SportCarEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import(HibernateConfig.class)
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.andersenlab.adamovichjr.dao.repository")
@ComponentScan(basePackages = "com.andersenlab.adamovichjr")
public class DaoConfig {
}
