package com.codecool.banking.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "balance", nullable = false)
    private Long balance;
    @Column(name = "balance_decimal", nullable = false)
    private Long balanceDecimal;
}
