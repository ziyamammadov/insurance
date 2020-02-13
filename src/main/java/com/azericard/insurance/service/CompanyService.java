package com.azericard.insurance.service;

import com.azericard.insurance.data.CompanyRepository;
import com.azericard.insurance.entity.Company;
import com.azericard.insurance.exception.CompanyNotFoundException;
import com.azericard.insurance.exception.GeneralException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    private final CompanyRepository companyRepo;

    public CompanyService(CompanyRepository companyRepo) {
        this.companyRepo = companyRepo;
    }

    public List<Company> getAll() {
        List<Company> companies = (List<Company>) companyRepo.findAll();
        if (companies.isEmpty()) {
            throw new GeneralException("No Data Found");
        }
        return companies;
    }

    public Company getOne(long id) {
        Optional<Company> optionalCompany = companyRepo.findById(id);
        return optionalCompany.orElseThrow(CompanyNotFoundException::new);
    }

    public Company save(Company company) {
        return companyRepo.save(company);
    }

    public void delete(Company company) {
        companyRepo.delete(company);
    }


}
