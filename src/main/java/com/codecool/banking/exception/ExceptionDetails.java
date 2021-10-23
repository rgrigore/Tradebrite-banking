package com.codecool.banking.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class ExceptionDetails {
    private int status;
    private Date timestamp;
    private String message;
    private String details;
}
