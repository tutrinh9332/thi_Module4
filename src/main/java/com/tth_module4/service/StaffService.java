package com.tth_module4.service;

import com.tth_module4.model.Staff;
import com.tth_module4.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
    @Autowired
    StaffRepository staffRepository;


    public List<Staff> getAll() {
        return (List<Staff>) staffRepository.findAll();
    }

    public void save(Staff staff) {
        staffRepository.save(staff);
    }

    public void delete(Long id) {
        staffRepository.deleteById(id);
    }

    public Staff findById(Long id) {
        Optional<Staff> optional = staffRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return new Staff();
    }

    public List<Staff> findByStaffNameContaining(String name) {
        return staffRepository.findByStaffNameContaining(name);
    }

    public Optional<Staff> findByNameStaff(String name) {
        return staffRepository.findByStaffName(name);
    }

    public Optional<Staff> findByStaffCode(String name) {
        return staffRepository.findByStaffCode(name);
    }


}
