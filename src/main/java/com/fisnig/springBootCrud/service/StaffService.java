package com.fisnig.springBootCrud.service;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


import com.fisnig.springBootCrud.model.Staff;

import com.fisnig.springBootCrud.repository.StaffRepository;


@Service
public class StaffService {

    private StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<Staff> getAllSatffs() {
        return (List<Staff>) staffRepository.findAll();
    }

    public Optional<Staff> findById(Long id) {
        return staffRepository.findById(id);
    }
    public Staff save(Staff staff) {
        return staffRepository.save(staff);
    }

    public void deleteById(Long id) {
        staffRepository.deleteById(id);
    }

	
}
