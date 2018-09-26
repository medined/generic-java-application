package generic.jpa.repository;

public interface BlobRepository {
    public String save(final String parentType, final String parentId, final String buffer);
    public String save(final String parentType, final String parentId, final byte[] buffer);
    public String read(final String key);
}
