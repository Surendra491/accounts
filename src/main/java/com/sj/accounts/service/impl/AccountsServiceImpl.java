package com.sj.accounts.service.impl;

import com.sj.accounts.constants.AccountConstants;
import com.sj.accounts.dto.AccountsDto;
import com.sj.accounts.dto.CustomerDto;
import com.sj.accounts.entity.Accounts;
import com.sj.accounts.entity.Customer;
import com.sj.accounts.exception.ResourceNotFoundException;
import com.sj.accounts.mapper.AccountMapper;
import com.sj.accounts.mapper.CustomerMapper;
import com.sj.accounts.repository.AccountsRepository;
import com.sj.accounts.repository.CustomerRepository;
import com.sj.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;


    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
       Optional<Customer> existingCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());
       if(existingCustomer.isPresent()){
           throw new ClassCastException("Customer already registered with given Mobile Number:"+customer.getMobileNumber());
       }
       customer.setCreatedAt(LocalDateTime.now());
       customer.setCreatedBy("Anonymous");
        Customer savedCustomer = customerRepository.save(customer);
        Accounts newAccount = createNewAccount(savedCustomer);
        accountsRepository.save(newAccount);
    }


    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(999999999);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");

        return newAccount;
    }
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
       Customer customer= customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () ->new ResourceNotFoundException("Customer","MobileNumber",mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () ->new ResourceNotFoundException("Account","customerId",customer.getCustomerId().toString())
        );

        CustomerDto customerDto =CustomerMapper.mapToCustomerDto(customer,new CustomerDto());
        customerDto.setAccountsDto(AccountMapper.mapToAccountsDto(accounts, new AccountsDto()));
        return  customerDto;
    }
}
