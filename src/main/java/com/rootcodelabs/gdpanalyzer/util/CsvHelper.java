package com.rootcodelabs.gdpanalyzer.util;

import com.rootcodelabs.gdpanalyzer.exception.GdpAnalyzerException;
import com.rootcodelabs.gdpanalyzer.exception.pojo.ErrorCode;
import com.rootcodelabs.gdpanalyzer.model.Country;
import com.rootcodelabs.gdpanalyzer.model.FinancialRecord;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chinthaka Jayarathne
 * @E-mail jgcjayarathne@gmail.com
 * @Telephone +94718861722
 * @created on 2021-07-11
 */
public final class CsvHelper {

    private static final String TYPE = "text/csv";
    private static final String[] GDP_DATA_HEADERS = {"Country Name", "Country Code", "Year", "Value"};
    private static final String[] COUNTRY_DATA_HEADERS = {"Country", "Alpha-2 code", "Alpha-3 code", "Numeric"};

    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<FinancialRecord> csvToFinancialRecords(MultipartFile file) throws GdpAnalyzerException {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(),
                StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withHeader(GDP_DATA_HEADERS).withIgnoreHeaderCase
                             ().withTrim())) {

            List<FinancialRecord> financialRecords = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                FinancialRecord financialRecord = new FinancialRecord(csvRecord.get(GDP_DATA_HEADERS[1]),
                        Integer.parseInt(csvRecord.get(GDP_DATA_HEADERS[2])),
                        Double.parseDouble(csvRecord.get(GDP_DATA_HEADERS[3])));
                financialRecords.add(financialRecord);
            }
            return financialRecords;
        } catch (IOException e) {
            throw new GdpAnalyzerException(HttpStatus.BAD_REQUEST,
                    ErrorCode.FILE002,
                    "failed to parse GDP Data CSV file",
                    "failed to parse GDP data file : " + e.getMessage());
        }
    }

    public static List<Country> csvToCountry(MultipartFile file) throws GdpAnalyzerException {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(),
                StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withHeader(COUNTRY_DATA_HEADERS).withIgnoreHeaderCase().withTrim())) {

            List<Country> countries = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Country country = new Country(csvRecord.get(COUNTRY_DATA_HEADERS[2]),
                        csvRecord.get(COUNTRY_DATA_HEADERS[1]),
                        Integer.parseInt(csvRecord.get(COUNTRY_DATA_HEADERS[3])),
                        csvRecord.get(COUNTRY_DATA_HEADERS[0]));
                countries.add(country);
            }
            return countries;
        } catch (IOException e) {
            throw new GdpAnalyzerException(HttpStatus.BAD_REQUEST,
                    ErrorCode.FILE003,
                    "failed to parse Country Data CSV file",
                    "failed to parse Country data file : " + e.getMessage());
        }
    }
}
