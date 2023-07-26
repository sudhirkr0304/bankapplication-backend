package com.sudhir.bankapplicationbackend.controller;

import com.sudhir.bankapplicationbackend.dto.DepositAmountDto;
import com.sudhir.bankapplicationbackend.dto.SuccessResponse;
import com.sudhir.bankapplicationbackend.dto.TransferAmountDto;
import com.sudhir.bankapplicationbackend.entity.Transaction;
import com.sudhir.bankapplicationbackend.entity.User;
import com.sudhir.bankapplicationbackend.repository.TransactionRepository;
import com.sudhir.bankapplicationbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @PostMapping("/account/deposit")
    @Transactional
    public ResponseEntity<?> depositAmount(@RequestBody DepositAmountDto depositAmountDto) {

        User user = userRepository.findByUsername(depositAmountDto.getUsername());
        if (user == null) {
            return new ResponseEntity<>(new SuccessResponse("username does not exist", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }

        if(depositAmountDto.getAccountType().equals("savings")) {
            userRepository.updateSavingAccountBalance(depositAmountDto.getUsername(), depositAmountDto.getAmount()+ user.getSavingAccountBalance());
            Transaction transaction = new Transaction(depositAmountDto.getUsername(),"deposit","savings",depositAmountDto.getAmount());
            transactionRepository.save(transaction);
            return new ResponseEntity<>(new SuccessResponse("balance added to saving account", HttpStatus.OK), HttpStatus.OK);
        }
        else {
            userRepository.updatePrimaryAccountBalance(depositAmountDto.getUsername(), depositAmountDto.getAmount() + user.getPrimaryAccountBalance());
            Transaction transaction = new Transaction(depositAmountDto.getUsername(),"deposit","primary",depositAmountDto.getAmount());
            transactionRepository.save(transaction);
            return new ResponseEntity<>(new SuccessResponse("balance added to primary account", HttpStatus.OK), HttpStatus.OK);
        }


    }

    @PostMapping("/account/withdraw")
    @Transactional
    public ResponseEntity<?> withdrawAmount(@RequestBody DepositAmountDto depositAmountDto) {

        User user = userRepository.findByUsername(depositAmountDto.getUsername());
        if (user == null) {
            return new ResponseEntity<>(new SuccessResponse("username does not exist", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }


        if(depositAmountDto.getAccountType().equals("savings")) {

            if(user.getSavingAccountBalance() < depositAmountDto.getAmount()) {
                return new ResponseEntity<>(new SuccessResponse("you are trying to withdraw more amount that what present in your account", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
            }
            userRepository.updateSavingAccountBalance(depositAmountDto.getUsername(), user.getSavingAccountBalance() - depositAmountDto.getAmount());
            Transaction transaction = new Transaction(depositAmountDto.getUsername(),"withdraw","savings",depositAmountDto.getAmount());
            transactionRepository.save(transaction);
            return new ResponseEntity<>(new SuccessResponse("balance withdrawn from saving account", HttpStatus.OK), HttpStatus.OK);
        }
        else {
            if(user.getPrimaryAccountBalance() < depositAmountDto.getAmount()) {
                return new ResponseEntity<>(new SuccessResponse("you are trying to withdraw more amount that what present in your account", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
            }
            userRepository.updatePrimaryAccountBalance(depositAmountDto.getUsername(), user.getPrimaryAccountBalance() - depositAmountDto.getAmount() );
            Transaction transaction = new Transaction(depositAmountDto.getUsername(),"withdraw","primary",depositAmountDto.getAmount());
            transactionRepository.save(transaction);
            return new ResponseEntity<>(new SuccessResponse("balance added to primary account", HttpStatus.OK), HttpStatus.OK);
        }


    }


    @GetMapping("/account/transaction")
    public List<Transaction> getAllTransaction(@RequestParam String username) {
        List<Transaction> transactionList = transactionRepository.getTransactionByUsername(username);
        System.out.println(transactionList);
        return transactionList;
    }

    @PostMapping("/account/transfer")
    @Transactional
    public ResponseEntity<?> transferAmount(@RequestBody TransferAmountDto depositAmountDto, @RequestParam String username) {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(new SuccessResponse("username does not exist", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }


        if(depositAmountDto.getTo().equals("savings")) {


            userRepository.updatePrimaryAccountBalance(user.getUsername(), user.getPrimaryAccountBalance() - depositAmountDto.getAmount());
            userRepository.updateSavingAccountBalance(user.getUsername(),user.getSavingAccountBalance()+ depositAmountDto.getAmount());
            Transaction transaction = new Transaction(user.getUsername(),"transfer","savings",depositAmountDto.getAmount());
            transactionRepository.save(transaction);
            return new ResponseEntity<>(new SuccessResponse("balance transferred to saving account", HttpStatus.OK), HttpStatus.OK);
        }
        else {
            if(user.getPrimaryAccountBalance() < depositAmountDto.getAmount()) {
                return new ResponseEntity<>(new SuccessResponse("you are trying to withdraw more amount that what present in your account", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
            }
            userRepository.updateSavingAccountBalance(user.getUsername(), user.getSavingAccountBalance() - depositAmountDto.getAmount());
            userRepository.updateSavingAccountBalance(user.getUsername(),user.getPrimaryAccountBalance() + depositAmountDto.getAmount());
            Transaction transaction = new Transaction(user.getUsername(),"transfer","primary",depositAmountDto.getAmount());
            //transactionRepository.save(transaction);
            return new ResponseEntity<>(new SuccessResponse("balance transferred to primary account", HttpStatus.OK), HttpStatus.OK);
        }


    }


}
