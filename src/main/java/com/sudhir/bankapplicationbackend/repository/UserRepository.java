package com.sudhir.bankapplicationbackend.repository;

import com.sudhir.bankapplicationbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    @Modifying
    @Query(value = "update User set primary_account_balance=?2 where username=?1", nativeQuery = true)
    void updatePrimaryAccountBalance(String username, Double amount);
    @Modifying
    @Query(value = "update User set saving_account_balance=?2 where username=?1", nativeQuery = true )
    void updateSavingAccountBalance(String username, Double amount);

}
