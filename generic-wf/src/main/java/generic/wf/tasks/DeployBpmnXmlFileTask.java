package generic.wf.tasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import lombok.Setter;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class DeployBpmnXmlFileTask implements AbstractTask {

    protected static final Logger LOGGER = LoggerFactory.getLogger(DeployBpmnXmlFileTask.class);

    @Autowired
    private ProcessEngine processEngine;

    @Setter
    private String bpmnFile;

    @Override
    public void process() {
        Assert.notNull(processEngine, "BPMN process engine mustn't be null.");
        Assert.notNull(bpmnFile, "BPMN XML file mustn't be null.");

        RepositoryService repositoryService = processEngine.getRepositoryService();
        try {
            repositoryService.createDeployment().addInputStream("xxx",new FileInputStream(new File(bpmnFile))).deploy();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        
        Long count = repositoryService.createProcessDefinitionQuery().count();

        LOGGER.info("Process Definitions: " + count);
    }

}
