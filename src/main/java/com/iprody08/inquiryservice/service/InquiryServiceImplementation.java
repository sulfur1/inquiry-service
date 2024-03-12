package com.iprody08.inquiryservice.service;

import com.iprody08.inquiryservice.dao.InquiryRepository;
import com.iprody08.inquiryservice.entity.Inquiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InquiryServiceImplementation implements InquiryService {
    @Autowired
    @Qualifier("inquiryRepository")
    private InquiryRepository inquiryDAO;


    @Override
    public List<Inquiry> getAll() {
        return inquiryDAO.findAll();
    }

    @Override
    public void set(Inquiry entity) {
        inquiryDAO.save(entity);
    }

    @Override
    public Optional<Inquiry> get(long id) {
        return inquiryDAO.findById(id);
    }

    @Override
    public void delete(long id) {
        inquiryDAO.deleteById(id);
    }
}
