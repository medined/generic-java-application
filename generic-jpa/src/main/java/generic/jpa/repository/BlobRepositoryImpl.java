package generic.jpa.repository;

import generic.jpa.component.BlobStorageInitializer;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class BlobRepositoryImpl implements BlobRepository {

    @Override
    public String read(final String key) {
        String bucketName = BlobStorageInitializer.getBucketName();
        return BlobStorageInitializer.getClient().getObjectAsString(bucketName, key);
    }
    
    @Override
    public String save(final String parentType, final String parentId, final String buffer) {
        String sha256 = DigestUtils.sha256Hex(buffer);
        String key = String.format("s-%s/t-%s/p-%s", sha256, parentType, parentId);
        String bucketName = BlobStorageInitializer.getBucketName();
        BlobStorageInitializer.getClient().putObject(bucketName, key, buffer);
        return key;            
    }

    @Override
    public String save(final String parentType, final String parentId, final byte[] buffer) {
        String sha256 = DigestUtils.sha256Hex(buffer);
        String key = String.format("s-%s/t-%s/p-%s", sha256, parentType, parentId);
        String bucketName = BlobStorageInitializer.getBucketName();
        BlobStorageInitializer.getClient().putObject(bucketName, key, new String(buffer));
        return key;            
    }
}
