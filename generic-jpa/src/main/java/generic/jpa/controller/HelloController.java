package generic.jpa.controller;

import generic.jpa.service.VendorService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class HelloController {

    @Autowired
    private VendorService vendorService;
    
    @RequestMapping("/")
    public String home() {
        long vCount = vendorService.count();
        StringBuilder sb = new StringBuilder();
        sb.append("Vendors: ").append(vCount);
        return sb.toString();
    }
}
