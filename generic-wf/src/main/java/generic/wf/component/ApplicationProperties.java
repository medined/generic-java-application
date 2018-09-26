package generic.wf.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wf")
@Data
@PropertySource("classpath:application.properties")
public class ApplicationProperties {

    private String targetEnvironment;

    private String bpmnDirectory;
    
}
