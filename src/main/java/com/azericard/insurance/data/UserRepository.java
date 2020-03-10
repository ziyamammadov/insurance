package com.azericard.insurance.data;

import com.azericard.insurance.entity.Company;
import com.azericard.insurance.util.Role;
import com.azericard.insurance.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> getUsersByCompany_Id(long id);

    @Override
    Optional<User> findById(Long aLong);

    @Query(value = "select u from User u,Company c where u.role='OPERATOR' and u.company=?1")
    List<User> getListOfOperatorsByCompany(Company company);

    List<User> getUsersByRole(Role role);

    Optional<User> getUserByUsernameAndPassword(String username, String password);
}
