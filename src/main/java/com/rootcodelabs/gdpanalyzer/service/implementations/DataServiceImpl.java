package com.rootcodelabs.gdpanalyzer.service.implementations;

import com.rootcodelabs.gdpanalyzer.dto.GdpDto;
import com.rootcodelabs.gdpanalyzer.exception.GdpAnalyzerException;
import com.rootcodelabs.gdpanalyzer.exception.pojo.ErrorCode;
import com.rootcodelabs.gdpanalyzer.model.Country;
import com.rootcodelabs.gdpanalyzer.model.FinancialRecord;
import com.rootcodelabs.gdpanalyzer.repository.CountryRepository;
import com.rootcodelabs.gdpanalyzer.repository.FinancialRecordRepository;
import com.rootcodelabs.gdpanalyzer.service.DataService;
import com.rootcodelabs.gdpanalyzer.util.CsvHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Chinthaka Jayarathne
 * @E-mail jgcjayarathne@gmail.com
 * @Telephone +94718861722
 * @created on 2021-07-11
 */

@Service
@Slf4j
public class DataServiceImpl implements DataService {

    @Autowired
    private FinancialRecordRepository financialRecordRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public void parseGdpData(MultipartFile file) throws GdpAnalyzerException {
        if (CsvHelper.hasCSVFormat(file)) {
            List<FinancialRecord> financialRecords = CsvHelper.csvToFinancialRecords(file);
            financialRecordRepository.deleteAll();
            financialRecordRepository.saveAll(financialRecords);
            log.debug("No of records: {}", financialRecords.size());
            log.info("CSV saved successfully");
        } else {
            throw new GdpAnalyzerException(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                    ErrorCode.FILE001, "unsupported media type",
                    "unsupported media type");
        }
    }

    @Override
    public void parseCountryData(MultipartFile file) throws GdpAnalyzerException {
        if (CsvHelper.hasCSVFormat(file)) {
            List<Country> countries = CsvHelper.csvToCountry(file);
            countryRepository.deleteAll();
            countryRepository.saveAll(countries);
            log.debug("No of records: {}", countries.size());
            log.info("CSV saved successfully");
        } else {
            throw new GdpAnalyzerException(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                    ErrorCode.FILE001, "unsupported media type",
                    "unsupported media type");
        }
    }

    @Override
    @Cacheable("gdps")
    public GdpDto getGdp(String countryCode, int year) throws GdpAnalyzerException {

        log.debug("country code: {}, year: {}", countryCode, year);
        Country country;
        if (countryCode.length() == 3) {
            country = countryRepository.findOneByCountryCodeAlphaThree(countryCode);
            log.debug("Country found: {}", country);
        } else {
            country = countryRepository.findOneByCountryCodeAlphaTwo(countryCode);
        }

        if (ObjectUtils.isEmpty(country)) {
            throw new GdpAnalyzerException(HttpStatus.NOT_FOUND,
                    ErrorCode.CTRY001,
                    "Country not found in database", "Country not found: " + countryCode);
        }

        FinancialRecord financialRecord =
                financialRecordRepository.findOneByCountryCodeAlphaThreeAndYear(country.getCountryCodeAlphaThree(),
                        year);
        if (ObjectUtils.isEmpty(financialRecord)) {
            throw new GdpAnalyzerException(HttpStatus.NOT_FOUND,
                    ErrorCode.FNRC001,
                    "Financial record not found in database",
                    "Financial record not found: " + countryCode + "   year: " + year);
        }

        log.debug("Record found: {}", financialRecord);
        log.info("Record found successfully");
        return new GdpDto(country.getCountryName(), String.format ("%.2f", financialRecord.getGdp()));
    }
}
