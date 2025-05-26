package com.sj.accounts.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    @Size(min = 5, max = 30, message = "The Length of the customer name should be between 5 and 30 characters")
    @NotEmpty(message = "Name can not be null or Empty")
    private String name;

    @NotEmpty(message = "Email address can not be null or Empty")
    @Email
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
