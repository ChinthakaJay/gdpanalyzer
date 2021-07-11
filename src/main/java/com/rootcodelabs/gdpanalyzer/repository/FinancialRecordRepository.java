package com.rootcodelabs.gdpanalyzer.repository;

import com.rootcodelabs.gdpanalyzer.model.FinancialRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Chinthaka Jayarathne
 * @E-mail jgcjayarathne@gmail.com
 * @Telephone +94718861722
 * @created on 2021-07-11
 */

@Repository
public interface FinancialRecordRepository extends CrudRepository<FinancialRecord, String> {
    FinancialRecord findOneByCountryCodeAlphaThreeAndYear(String countryCode, int year);
}
