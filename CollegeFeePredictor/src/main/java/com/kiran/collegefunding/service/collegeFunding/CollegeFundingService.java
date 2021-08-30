package com.kiran.collegefunding.service.collegeFunding;

import java.util.List;
import java.util.Map;

public interface CollegeFundingService {

    Long getStateWiseTuitionFee(String state, String residenceState) throws Exception;

    Map<Integer, Long> getTuitionFeeWithInflation(Integer currentAge, Long currentTuitionFee, Double inflation);

    List<String[]> readAll(String filename) throws Exception;


}
