package generic.wf;

import generic.wf.tasks.GenerateTravelPlanBpmnXmlFileTask;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class GenerateTravelPlanBpmnXmlFileDriver {
    
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =  new AnnotationConfigApplicationContext("generic.wf");
        GenerateTravelPlanBpmnXmlFileTask task = ctx.getBean(GenerateTravelPlanBpmnXmlFileTask.class);
        task.process();
    }
    
}
