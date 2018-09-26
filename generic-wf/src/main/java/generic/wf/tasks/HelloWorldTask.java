package generic.wf.tasks;

import generic.wf.component.ApplicationProperties;
import org.activiti.engine.ProcessEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldTask implements AbstractTask {

    protected static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldTask.class);
    
    @Autowired
    private ApplicationProperties applicationProperties;
    
    @Autowired
    private ProcessEngine processEngine;
    
    public void process() {
        LOGGER.info("Target Environment: {}", applicationProperties.getTargetEnvironment());
        LOGGER.info("     ProcessEngine: {}", processEngine);
    }
}
