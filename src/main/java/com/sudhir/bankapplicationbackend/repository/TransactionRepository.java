package com.sudhir.bankapplicationbackend.repository;

import com.sudhir.bankapplicationbackend.entity.Transaction;
import com.sudhir.bankapplicationbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    List<Transaction> getTransactionByUsername(String username);

}
