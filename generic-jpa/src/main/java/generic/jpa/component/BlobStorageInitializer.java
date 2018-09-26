package generic.jpa.component;

import java.io.File;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import io.findify.s3mock.S3Mock;
import lombok.Getter;

@Component
@PropertySource(value = "classpath:application.properties")
public class BlobStorageInitializer {

	protected static final Logger LOGGER = LoggerFactory.getLogger(BlobStorageInitializer.class);

	@Autowired
	private Environment env;

	private static S3Mock api;

	@Getter
	private static AmazonS3 client;

	@Getter
	private static String bucketName;

	@PostConstruct
	public void setup() {
		boolean usesS3Mock = env.getRequiredProperty("generic.blob.use.s3.mode", Boolean.class);
		bucketName = env.getRequiredProperty("generic.blob.bucket.name");
		String region = env.getRequiredProperty("generic.blob.s3.region");

		if (usesS3Mock) {
			String serverName = env.getRequiredProperty("generic.blob.server.name");
			int serverPort = env.getRequiredProperty("generic.blob.server.port", Integer.class);
			String directory = env.getRequiredProperty("generic.blob.directory");

			File dir = new File(directory);

			dir.mkdirs();

			System.out.println("directory: " + directory);

			api = new S3Mock.Builder().withPort(serverPort).withFileBackend(directory).build();
			api.start();
			String serviceEndpoint = String.format("http://%s:%d", serverName, serverPort);

			AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(
					new AnonymousAWSCredentials());

			AwsClientBuilder.EndpointConfiguration endpoint = new AwsClientBuilder.EndpointConfiguration(
					serviceEndpoint, region);

			client = AmazonS3ClientBuilder.standard().withPathStyleAccessEnabled(true)
					.withEndpointConfiguration(endpoint).withCredentials(credentialsProvider).build();
		} else {
			String accessKey = env.getRequiredProperty("generic.blob.access.key");
			String secretAccessKey = env.getRequiredProperty("generic.blob.secret.access.key");
			BasicAWSCredentials creds = new BasicAWSCredentials(accessKey, secretAccessKey);
			client = AmazonS3ClientBuilder.standard().withPathStyleAccessEnabled(true)
					.withCredentials(new AWSStaticCredentialsProvider(creds)).build();
		}

		client.createBucket(bucketName);
		LOGGER.info("BlobStorage: initialized. S3 Mock Flag: {} Bucket: {}", usesS3Mock, bucketName);
	}

	/**
	 * The S3Mock thread should be cleanly shutdown. I have not seen this down using
	 * PreDestroy or using a shutdown hook. If possible, call this shutdown method
	 * before the process dies.
	 */
	public static void shutdown() {
		api.stop();
		api.shutdown();
		LOGGER.info("BlogStorage: shutdown completed.");
	}

}
