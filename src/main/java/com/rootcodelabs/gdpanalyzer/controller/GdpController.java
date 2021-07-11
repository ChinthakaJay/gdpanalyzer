package com.rootcodelabs.gdpanalyzer.controller;

import com.rootcodelabs.gdpanalyzer.dto.GdpDto;
import com.rootcodelabs.gdpanalyzer.exception.GdpAnalyzerException;
import com.rootcodelabs.gdpanalyzer.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;

/**
 * @author Chinthaka Jayarathne
 * @E-mail jgcjayarathne@gmail.com
 * @Telephone +94718861722
 * @created on 2021-07-11
 */

@RestController
@RequestMapping("/api/v1/gdp")
@Slf4j
@Validated
public class GdpController {

    @Autowired
    private DataService dataService;

    @GetMapping
    public ResponseEntity<GdpDto> getGdp(@RequestParam("country-code")
                                         @Pattern(regexp = "[A-Z]{2,3}", message = "Invalid Country Code")
                                                 String countryCode,
                                         @RequestParam("year")
//                                         @Min(value = 2007, message = "Minimum year is 2007")
//                                         @Max(value = 2016, message = "Maximum year is 2016")
                                                 int year) throws GdpAnalyzerException {
//        It is possible to validate if year is between a given range.
//        However if data is already available in db no point in failing the request
        log.info("GDP data query api called");
        return new ResponseEntity<>(dataService.getGdp(countryCode, year), HttpStatus.OK);
    }
}
