package com.azericard.insurance.data;

import com.azericard.insurance.entity.Insurance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends CrudRepository<Insurance, Long> {
}
