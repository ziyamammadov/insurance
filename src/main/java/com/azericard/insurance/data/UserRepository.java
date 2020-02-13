package com.azericard.insurance.data;

import com.azericard.insurance.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "select u from User u where u.company.id=?1")
    List<User> getUsersByCompanyId(long id);
}
