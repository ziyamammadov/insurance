package com.azericard.insurance.service;

import com.azericard.insurance.data.InsuranceRepository;
import com.azericard.insurance.util.EncodedRole;
import com.azericard.insurance.entity.Insurance;
import com.azericard.insurance.exception.AccessNotAllowedException;
import com.azericard.insurance.exception.GeneralException;
import com.azericard.insurance.exception.InsuranceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class InsuranceService {
    InsuranceRepository insuranceRepository;

    public InsuranceService(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    public Insurance save(Insurance insurance, String role) {
        if (role.equals(EncodedRole.ADMIN)) {
            insurance.setFromDate(insurance.getRegisterDate());
            insurance.setNumberOfDays(ChronoUnit.DAYS.between(insurance.getFromDate(), insurance.getToDate()));
            insurance.setPolicyNumber(insurance.getClient().getFIN());
            System.out.println("HERE IS FIN NUMBER"+insurance.getClient().getFIN());
            insurance.setInsuranceCost(insurance.buildInsuranceCost());
            insurance.setStatus(insurance.buildStatus());
            return insuranceRepository.save(insurance);
        }
        throw new AccessNotAllowedException();
    }

    public List<Insurance> getAll(String role) {
        if (role.equals(EncodedRole.ADMIN)) {
            List<Insurance> insurances = (List<Insurance>) insuranceRepository.findAll();
            if (insurances.isEmpty()) {
                throw new GeneralException("No Data Found");
            }
            return insurances;
        }
        throw new AccessNotAllowedException();
    }

    public Insurance getOne(long id, String role) {
        if (role.equals(EncodedRole.ADMIN)) {
            Optional<Insurance> optionalInsurance = insuranceRepository.findById(id);
            return optionalInsurance.orElseThrow(InsuranceNotFoundException::new);
        }
        throw new AccessNotAllowedException();

    }

    public void delete(Insurance insurance, String role) {
        if (role.equals(EncodedRole.ADMIN)) {
            insuranceRepository.delete(insurance);
        }
        throw new AccessNotAllowedException();
    }
}
