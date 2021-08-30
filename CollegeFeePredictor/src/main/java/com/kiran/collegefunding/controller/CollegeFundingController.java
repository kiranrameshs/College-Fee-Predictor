package com.kiran.collegefunding.controller;

import com.kiran.collegefunding.model.collegefunding.*;
import com.kiran.collegefunding.service.collegeFunding.CollegeFundingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CollegeFundingController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CollegeFundingService collegeFundingService;


    //-------College funding-----
    @PutMapping("/collegeData/getTuitionFee")
    private TuitionFeeResponse getCollegeNamesUsingCsv(@RequestBody @Valid TuitionFeeRequest tuitionFeeRequest) throws Exception {

        /**
         * Assumtions:
         * 1. Residence state
         * 2. Current Age of the child
         * 3. College start year is child at age 18
         * 4. If we fallback to statewise tuition fee, we only provide public (instate and outofstate depending on the
         * residence state as only public tuition fee is the standard) because the private avg for statewise will not be accurate
         */
        TuitionFeeResponse tuitionFeeResponse = new TuitionFeeResponse();
        String filename;
        String residenceState = "AK";
        Integer currentAge = 13;
        InstitutionType institutionType ;
        Integer columnNum = null;
        Long currentTuitionFee = null;
        Double inflation = tuitionFeeRequest.getInflationRate() * .01;
        try {
            //set a default institution type initially before setting specific type later
            if (tuitionFeeRequest.getState().equals(residenceState)) {
                institutionType = InstitutionType.PUBLIC_INSTATE;
            } else {
                institutionType = InstitutionType.PUBLIC_OUTOFSTATE;
            }
            //Search by college Name
            if (tuitionFeeRequest.getUid() != null) {
                filename = "CollegeWiseTuitionFee.csv";
                List<String[]> data = collegeFundingService.readAll(filename);
                for (String[] strArray : data) {
                    //if uid matches, college row is found
                    if (tuitionFeeRequest.getUid() == Long.parseLong(strArray[0])) {
                        //set specific institution type based on college row
                        if (Integer.parseInt(strArray[6]) == 1) {
                            if (tuitionFeeRequest.getState() == residenceState) {
                                institutionType = InstitutionType.PUBLIC_INSTATE;
                                columnNum = 9;
                            } else {
                                institutionType = InstitutionType.PUBLIC_OUTOFSTATE;
                                columnNum = 10;
                            }

                        } else {
                            institutionType = InstitutionType.PRIVATE;
                            //if private annual fee is not available, switch to private program wise
                            columnNum = Integer.parseInt(strArray[7]) >0 ? 7:8;
                        }
                    }
                    //set college specific data
                    tuitionFeeResponse.setUid(Long.parseLong(strArray[0]));
                    tuitionFeeResponse.setName(strArray[1]);
                    tuitionFeeResponse.setCity(strArray[2]);
                    //calculate the tuition fee for the first year of college
                    currentTuitionFee = Long.parseLong(strArray[columnNum]);
                    logger.info("currentTuitionFee is  "+currentTuitionFee);
                    break;
                }
            }

            //if the tuition fee is still not available, then the data for this college is missing (handle null values)
            //Fallback to state wise data, use the requested state as the input
            if (currentTuitionFee == null) {
                currentTuitionFee = collegeFundingService.getStateWiseTuitionFee(tuitionFeeRequest.getState(), residenceState);
                logger.info("currentTuitionFee from statewise is "+currentTuitionFee);
            }
            tuitionFeeResponse.setTuitionFee(collegeFundingService.getTuitionFeeWithInflation(currentAge, currentTuitionFee,inflation));
            tuitionFeeResponse.setState(StateCatalog.getInstance().getStateByAbbr(tuitionFeeRequest.getState()).get().getName());
            tuitionFeeResponse.setTotalTuitionFee(tuitionFeeResponse.calculateTotalTuitionFee());
            tuitionFeeResponse.setInstitutionType(institutionType.getStringValue());
            return tuitionFeeResponse;
        }catch(Exception e){
            throw new Exception("Error while getting tuition fee "+e.getMessage());
        }
    }

    @GetMapping("/collegeData/getInflationRate/csv")
    private  Map<String, InflationTypes> getInflationRateUsingCsv() throws Exception {
        String filename = "StateWiseInflationRate.csv";
        Map<String, InflationTypes> inflationRates = new HashMap<>();
        List<String[]> data = collegeFundingService.readAll(filename);
        for(String[] strArray: data){
            InflationTypes inflationTypes = new InflationTypes();
            inflationTypes.setPrivateInstitution(Float.parseFloat(strArray[1]));
            inflationTypes.setInStateInstitution(Float.parseFloat(strArray[3]));
            inflationTypes.setOutOfStateInstitution(Float.parseFloat(strArray[4]));
            inflationRates.put(strArray[0], inflationTypes);
        }
        return inflationRates;
    }

    @GetMapping("/collegeData/getCollegeNames/csv")
    private  List<CollegeDetails> getCollegeNamesUsingCsv() throws Exception {
//    private  Map<Long, String> getCollegeNamesUsingCsv() throws Exception {
        String filename = "CollegeWiseTuitionFee.csv";
        Map<Long, String> collegeNames = new HashMap<>();
        List<String[]> data = collegeFundingService.readAll(filename);
        List<CollegeDetails> collegeDetailsList = new ArrayList<>();
        for(String[] strArray: data){
            collegeNames.put(Long.parseLong(strArray[0]), strArray[1]);
            CollegeDetails collegeDetails = new CollegeDetails();
            collegeDetails.setUid(Long.parseLong(strArray[0]));
            collegeDetails.setInstitutionName(strArray[1]);
            collegeDetails.setCity(strArray[2]);
            collegeDetails.setStateAbbr(strArray[3]);
            collegeDetails.setZipCode(strArray[4]);
//            collegeDetails.setDegreeOffered(Integer.parseInt(strArray[5]));
            collegeDetails.setInstitutionType(InstitutionTypeFromData.getStringValueByIntegerValue(strArray[6]));
            collegeDetails.setCourseDuration(Integer.parseInt(strArray[12]));
            collegeDetailsList.add(collegeDetails);
        }
//        return collegeNames;
        return collegeDetailsList;
    }
}
