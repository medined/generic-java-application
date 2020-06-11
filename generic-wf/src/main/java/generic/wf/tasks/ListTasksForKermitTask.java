package generic.wf.tasks;

import generic.wf.component.ApplicationProperties;
import java.util.List;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ListTasksForKermitTask implements AbstractTask {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ListTasksForKermitTask.class);

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Override
    public void process() {
        Assert.notNull(processEngine, "BPMN process engine mustn't be null.");

        TaskService taskService = processEngine.getTaskService();
        List<org.activiti.engine.task.Task> tasks = taskService.createTaskQuery().taskCandidateGroup("kermit").list();
        LOGGER.info("tasks: " + tasks);
    }

}
