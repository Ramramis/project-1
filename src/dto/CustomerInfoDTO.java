package com.spring.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CustomerInfoDTO {

	private int customerID;
    private String customerName;
    private String email;
    private String address;
    private String dateOfBirth;
    private String contact;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registrationDate;
    private String memo;
    
    
    



}
