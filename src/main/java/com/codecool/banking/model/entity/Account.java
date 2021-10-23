package com.codecool.banking.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "account")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "balance", nullable = false)
    private Long balance;
    @Column(name = "balance_decimal", nullable = false)
    private Long balanceDecimal;
}
