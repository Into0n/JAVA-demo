package sit.int202.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int202.demo.entities.Office;
import sit.int202.demo.repositories.OfficeRepository;

import java.util.List;

@Service

public class OfficeService {
    @Autowired
    private OfficeRepository officeRepository;

    private Office getOffice(String officeCode) {
        return officeRepository.findById(officeCode).orElse(null); //ถ้าหาเจอ return เป็น object ของ office ถ้าหาไม่เจอ return null
    }

    public List<Office> getAllOffices() {
        return officeRepository.findAll();
    }

    //create
    public Office createOffice(Office office) {
        Office existingOffice = getOffice(office.getOfficeCode());
        if (existingOffice != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Office id '%s' already exists", office.getOfficeCode()));
        }
        return officeRepository.save(office);
    }

    //update แสดงว่าต้องมีข้อมูลเก่าอยู่ก่อนหน้านี้
    public Office updateOffice(Office office) {
        Office existingOffice = getOffice(office.getOfficeCode());
        if (existingOffice == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Office id '%s' does not exists", office.getOfficeCode()));
        }
        return officeRepository.save(office);
    }

    //delete office จะ return object ของ office ที่ถูก delete
    public Office deleteOffice(String officeCode) {
        Office existingOffice = getOffice(officeCode);
        if (existingOffice == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Office id '%s' does not exists", officeCode));
        }
        officeRepository.delete(existingOffice);
        return existingOffice;
    }

    public Office getOfficeByCode(String officeCode) {
        return getOffice(officeCode);
    }
}
