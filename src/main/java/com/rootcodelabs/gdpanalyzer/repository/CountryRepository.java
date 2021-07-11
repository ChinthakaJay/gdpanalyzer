package com.rootcodelabs.gdpanalyzer.repository;

import com.rootcodelabs.gdpanalyzer.model.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Chinthaka Jayarathne
 * @E-mail jgcjayarathne@gmail.com
 * @Telephone +94718861722
 * @created on 2021-07-11
 */

@Repository
public interface CountryRepository extends CrudRepository<Country, String> {
    Country findOneByCountryCodeAlphaThree(String countryCode);

    Country findOneByCountryCodeAlphaTwo(String countryCode);
}
