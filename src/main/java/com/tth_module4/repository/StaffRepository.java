package com.tth_module4.repository;

import com.tth_module4.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    List<Staff> findByStaffNameContaining(String name);

    Optional<Staff> findByStaffName(String name);

    Optional<Staff> findByStaffCode(String name);


}
