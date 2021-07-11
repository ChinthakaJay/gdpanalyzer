package com.rootcodelabs.gdpanalyzer.model;

import lombok.*;

import javax.persistence.Entity;

/**
 * @author Chinthaka Jayarathne
 * @E-mail jgcjayarathne@gmail.com
 * @Telephone +94718861722
 * @created on 2021-07-11
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FinancialRecord extends BaseModel {

    private String countryCodeAlphaThree;
    private int year;
    private Double gdp;
}
