package com.sj.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Accounts extends BaseEntity {


    private long customerId;

    @Id
    private long accountNumber;
    private String accountType;
    private String branchAddress;

}
