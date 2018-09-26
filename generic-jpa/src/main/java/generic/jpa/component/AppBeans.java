package generic.jpa.component;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:application.properties")
public class AppBeans {

    @Autowired
    private Environment env;

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = null;
        String driverClassName = env.getProperty("spring.datasource.driverClassName");
        if (driverClassName != null) {
            dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(env.getRequiredProperty("spring.datasource.driverClassName"));
            dataSource.setPassword(env.getRequiredProperty("spring.datasource.password"));
            dataSource.setUrl(env.getRequiredProperty("spring.datasource.url"));
            dataSource.setUsername(env.getRequiredProperty("spring.datasource.username"));
        }
        return dataSource;
    }

}
