package com.rootcodelabs.gdpanalyzer.controller;

import com.rootcodelabs.gdpanalyzer.exception.GdpAnalyzerException;
import com.rootcodelabs.gdpanalyzer.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Chinthaka Jayarathne
 * @E-mail jgcjayarathne@gmail.com
 * @Telephone +94718861722
 * @created on 2021-07-11
 */

@RestController
@RequestMapping("/api/v1/uploads")
@Slf4j
public class DataUploadController {

    @Autowired
    private DataService dataService;

    @PostMapping(value = "/gdp-data", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadGdpData(@RequestParam("file") MultipartFile file) throws GdpAnalyzerException {
        log.info("GDP data upload api called");
        dataService.parseGdpData(file);
        log.info("GDP data parsed successfully");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/country-data", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadCountryData(@RequestParam("file") MultipartFile file) throws GdpAnalyzerException {
        log.info("Country data upload api called");
        dataService.parseCountryData(file);
        log.info("Country data parsed successfully");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
