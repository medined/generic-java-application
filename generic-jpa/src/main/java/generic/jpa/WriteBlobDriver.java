package generic.jpa;

import generic.jpa.component.BlobStorageInitializer;
import generic.jpa.tasks.WriteBlobTask;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Blobs should never be directly stored. They should be stored by the
 * service class of the owning entity. However, below is an example of
 * the bad behavior.
 */
public class WriteBlobDriver {
    public static void main(String[] args) {
        try {
            AnnotationConfigApplicationContext ctx =  new AnnotationConfigApplicationContext("generic.jpa");
            WriteBlobTask task = ctx.getBean(WriteBlobTask.class);
            task.process();
        } finally {
            BlobStorageInitializer.shutdown();
        }
    }
}
