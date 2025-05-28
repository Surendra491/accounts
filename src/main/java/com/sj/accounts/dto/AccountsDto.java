package com.sj.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(
        name = "Accounts",
        description = "Schema holds the account information of the customer"
)
@Data
public class AccountsDto {
    @Schema(
            description = "Account number",
            example = "4830583040"
    )
    @NotEmpty(message = "Account number can not be a null or Empty")
    @Pattern(regexp = "(^$|[0-9]{})",message = "Account number must be 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Account Type",
            example = "Savings"
    )
    @NotEmpty(message = "Account Type can not be null or Empty")
    private String accountType;

    @Schema(
            description = "Branch Address"
    )
    @NotEmpty(message = "Branch Address can not be null or Empty")
    private String branchAddress;

}
