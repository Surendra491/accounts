package com.sj.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {

    @Schema(
            description = "Name of the customer",
            example = "Sj"
    )
    @Size(min = 5, max = 30, message = "The Length of the customer name should be between 5 and 30 characters")
    @NotEmpty(message = "Name can not be null or Empty")
    private String name;

    @Schema(
            description = "Email id of the customer",
            example = "Sj@email.com"
    )
    @NotEmpty(message = "Email address can not be null or Empty")
    @Email
    private String email;

    @Schema(
            description = "Mobile number of the customer",
            example = "6360119033"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account details of the customer"
    )
    private AccountsDto accountsDto;
}
