package com.kiran.collegefunding.model.collegefunding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
public class TuitionFeeResponse {

    private Long uid;

    private String name;

    private String city;

    private String State;

    private String institutionType;

    private Map<Integer, Long> tuitionFee;

    private Long totalTuitionFee;

    public Long calculateTotalTuitionFee(){
        Long totalTuitionFee = 0L;
        for(Integer year: this.getTuitionFee().keySet()){
            totalTuitionFee+=this.getTuitionFee().get(year);
        }
        return totalTuitionFee;
    }


}
