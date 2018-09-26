package generic.wf.component;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AppBeans {

    @Bean
    public ProcessEngine getProcessEngine() {
        return ProcessEngines.getDefaultProcessEngine();
    }

}
