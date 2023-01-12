package com.tth_module4.service;

import com.tth_module4.model.Branch;
import com.tth_module4.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchService {
    @Autowired
    BranchRepository branchRepository;

    public List<Branch> getAll() {
        return (List<Branch>) branchRepository.findAll();
    }

    public Branch findById(Long id) {
        Optional<Branch> optional = branchRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return new Branch();
    }
}
