package generic.wf.tasks;

import generic.wf.component.ApplicationProperties;
import java.io.File;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class TravelPlanProcessTask implements AbstractTask, ApplicationContextAware {

    protected static final Logger LOGGER = LoggerFactory.getLogger(TravelPlanProcessTask.class);

    private static ApplicationContext ctx;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Setter
    private String bpmnFile;

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        ctx = ac;
    }

    @Override
    public void process() {
        Assert.notNull(applicationProperties.getBpmnDirectory(), "BPMN directory mustn't be null.");

        LOGGER.info("bpmnDirectory: {}", applicationProperties.getBpmnDirectory());
        new File(applicationProperties.getBpmnDirectory()).mkdirs();

        String bpmnFilename = "travel-plan-review.bpmn20.xml";
        String bpmnFilepath = applicationProperties.getBpmnDirectory() + File.separator + bpmnFilename;

        GenerateTravelPlanBpmnXmlFileTask task1 = ctx.getBean(GenerateTravelPlanBpmnXmlFileTask.class);
        task1.setBpmnFile(bpmnFilepath);
        task1.process();

        DeployBpmnXmlFileTask task2 = ctx.getBean(DeployBpmnXmlFileTask.class);
        task2.setBpmnFile(bpmnFilepath);
        task2.process();
    }

}
