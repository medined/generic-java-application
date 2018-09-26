package generic.wf;

import generic.wf.tasks.HelloWorldTask;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloWorldDriver {
    
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =  new AnnotationConfigApplicationContext("generic.wf");
        HelloWorldTask task = ctx.getBean(HelloWorldTask.class);
        task.process();
    }
    
}
