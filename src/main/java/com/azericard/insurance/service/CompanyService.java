package com.azericard.insurance.service;

import com.azericard.insurance.data.CompanyRepository;
import com.azericard.insurance.entity.Company;
import com.azericard.insurance.entity.EncodedRole;
import com.azericard.insurance.exception.AccessNotAllowedException;
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

    public List<Company> getAll(String role) {
        if (role.equals(EncodedRole.SUPER_ADMIN)) {
            List<Company> companies = (List<Company>) companyRepo.findAll();
            if (companies.isEmpty()) {
                throw new GeneralException("No Data Found");
            }
            return companies;
        }
        throw new AccessNotAllowedException();
    }

    public Company getOne(long id,String role) {
        if (role.equals(EncodedRole.SUPER_ADMIN)) {
            Optional<Company> optionalCompany = companyRepo.findById(id);
            return optionalCompany.orElseThrow(CompanyNotFoundException::new);
        }
        throw new AccessNotAllowedException();

    }

    public Company save(Company company,String role) {
        if (role.equals(EncodedRole.SUPER_ADMIN)) {
            return companyRepo.save(company);
        }
        throw new AccessNotAllowedException();

    }

    public void delete(Company company,String role) {
        if (role.equals(EncodedRole.SUPER_ADMIN)) {
            companyRepo.delete(company);
        }
        throw new AccessNotAllowedException();
    }


}
