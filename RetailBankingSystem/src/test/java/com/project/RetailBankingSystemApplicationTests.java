package com.project;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.account.entities.Account;
import com.project.account.entities.Customer;
import com.project.account.entities.Employee;
import com.project.account.entities.Statement;
import com.project.account.entities.repositories.AccountRepository;
import com.project.account.entities.repositories.CustomerRepository;
import com.project.account.service.AccountService;
import com.project.account.service.EmployeeService;
import com.project.account.service.TransactionService;

@SpringBootTest
class RetailBankingSystemApplicationTests {

    @Autowired
    CustomerRepository repository;

    @Autowired
    AccountRepository aRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService tService;

    @Autowired
    EmployeeService eService;

    @Test
    void createCustomer() {
        Date date = Date.valueOf("2020-05-25");
//		Customer customer = new Customer("Mr.","Gaurav","Mohan","Wadibhasme", "Male", date,"abcd1234","123456789012","address line 1","nagpur", "maharashtra" , "India" , "pincode");
        Customer customer = new Customer();
//		customer.setCustomerId(1);
        customer.setFirstName("AAA");
        customer.setTitle("Mr");
        customer.setDateOfBirth(date);
        repository.save(customer);
    }

    @Test
    void getCustomer() {
        int customerId = 1;
        Optional<Customer> optional = repository.findById(customerId);
        Assertions.assertNotNull(optional.get());

    }

    @Test
    void findAccounts() {
        int customerId = 2;
        List<Account> accounts = accountService.findAccountsByCustomerId(customerId);
        for (Account account : accounts) {
            System.out.println(account.toString());
        }
    }

    @Test
    void createStatement() {

        int accountId = 3;
        Account account = aRepository.findById(accountId).get();
        double balance = account.getInitialBalance();
        System.out.println(balance);
        Date date = Date.valueOf("2020-05-25");
        double amount = 200;
        String narration = "Deposited by cash";
        boolean deposited = false;
        boolean withdrawn = true;
        balance = (deposited) ? balance + amount : balance - amount;
        System.out.println(balance);


        Statement statement = new Statement();
        statement.setAccountId(accountId);
        statement.setDate(date);
        statement.setAmount(amount);
        statement.setNarration(narration);

        statement.setClosingBalance(balance);

        tService.createStatement(statement);
        account.setInitialBalance(balance);
        aRepository.save(account);

    }

    @Test
    public void createEmployee() {
        Employee employee = new Employee();
        employee.setUsername("Employee");
        employee.setPassword("qwerty");
        eService.createEmployee(employee);

    }

}
