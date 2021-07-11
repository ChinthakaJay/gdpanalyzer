package com.rootcodelabs.gdpanalyzer.exception.pojo;

/**
 * @author Chinthaka Jayarathne
 * @E-mail jgcjayarathne@gmail.com
 * @Telephone +94718861722
 * @created on 2021-07-11
 */
public enum ErrorCode {

    PARA001("PARA001"), //Parameter validation failed
    FILE001("FILE001"), //Unsupported file format
    FILE002("FILE002"), //Fail to parse GDP data CSV file
    FILE003("FILE003"), //Fail to parse Country data CSV file
    FILE004("FILE003"), //File too large to upload
    CTRY001("CTRY001"), //Country not found
    FNRC001("FNRC001"), //Financial record not found
    GDPAXXX("GDPAXXX"); // !!!Unforeseen exception !!!


    private final String value;

    ErrorCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
