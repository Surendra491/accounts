package com.sj.accounts.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

    @NotEmpty(message = "Account number can not be a null or Empty")
    @Pattern(regexp = "(^$|[0-9]{})",message = "Account number must be 10 digits")
    private Long accountNumber;

    @NotEmpty(message = "Account Type can not be null or Empty")
    private String accountType;

    @NotEmpty(message = "Branch Address can not be null or Empty")
    private String branchAddress;

}
