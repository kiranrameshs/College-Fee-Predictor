package com.kiran.collegefunding.service.collegeFunding;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CollegeFundingServiceImpl implements CollegeFundingService{

    private static Logger logger = LoggerFactory.getLogger(CollegeFundingServiceImpl.class);

    public Long getStateWiseTuitionFee(String state, String residenceState) throws Exception {
        String filename = "StateWiseTuitionFee.csv";
        Integer columnNum = 4;
        try {

            if (state.equals(residenceState)) {
                columnNum = 3;
            }
            List<String[]> data = readAll(filename);
            for (String[] strArray : data) {
                //search by state
                if (state.equals(strArray[0])) {
                    return Long.parseLong(strArray[columnNum]);
                }
            }
            throw new Exception("Could not find the requested State, Invalid State input");
        }catch(Exception e){
            throw new Exception("Error while reading statewise tuition fee "+e.getMessage());
        }
    }

    public Map<Integer, Long> getTuitionFeeWithInflation(Integer currentAge, Long currentTuitionFee, Double inflation){
        Map<Integer, Long> tuitionFee = new HashMap<>();
        int yearsToFirstYear = 18-currentAge;
        logger.info("yearsToFirstYear is  "+yearsToFirstYear);
        Double firstYearTuitionFeeWithInflation = currentTuitionFee.doubleValue();
        //the current tuition fee is from previous year, hence the calculation should be for yearsToFirstYear
        for(int j = 0; j<yearsToFirstYear; j++){
            firstYearTuitionFeeWithInflation = firstYearTuitionFeeWithInflation + (firstYearTuitionFeeWithInflation*inflation);
        }
        Long firstYearTuitionFee = Math.round(firstYearTuitionFeeWithInflation);
        logger.info("firstYearTuitionFee is  "+firstYearTuitionFee);
        //set 4 years of college tuition fee after adding inflation rate
        Double newTuitionCost = firstYearTuitionFeeWithInflation;
        for(int year = 0; year <4; year++){
            newTuitionCost = newTuitionCost + (newTuitionCost*inflation);
            tuitionFee.put(org.joda.time.DateTime.now().getYear()+yearsToFirstYear+year, Math.round(newTuitionCost));
//            tuitionFee.put();
        }
        return tuitionFee;
    }


    public List<String[]> readAll(String filename) throws Exception {
        try(Reader reader = Files.newBufferedReader(Paths.get(
                ClassLoader.getSystemResource(filename).toURI()))){

            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(',')
                    .withIgnoreQuotations(true)
                    .build();

            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(1)
                    .withCSVParser(parser)
                    .build();
            List<String[]> list = csvReader.readAll();
            return list;
        } catch (FileNotFoundException e){
            throw new Exception("invalid input "+e.getMessage());
        }
    }
}
