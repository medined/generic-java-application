package generic.jpa.service;

import generic.jpa.entity.VendorEntity;
import generic.jpa.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    private VendorRepository vendorRepository;
    
    @Override
    public long count() {
        return(vendorRepository.count());
    }
    
    @Override
    public void save(VendorEntity vendorEntity) {
        vendorRepository.saveAndFlush(vendorEntity);
    }
}
