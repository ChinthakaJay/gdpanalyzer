package com.rootcodelabs.gdpanalyzer.service;

import com.rootcodelabs.gdpanalyzer.dto.GdpDto;
import com.rootcodelabs.gdpanalyzer.exception.GdpAnalyzerException;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Chinthaka Jayarathne
 * @E-mail jgcjayarathne@gmail.com
 * @Telephone +94718861722
 * @created on 2021-07-11
 */
public interface DataService {
    void parseGdpData(MultipartFile file) throws GdpAnalyzerException;

    void parseCountryData(MultipartFile file) throws GdpAnalyzerException;

    GdpDto getGdp(String countryCode, int year) throws GdpAnalyzerException;
}
