package com.kiran.collegefunding.model.collegefunding;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@NoArgsConstructor
@Data
@ToString
public class TuitionFeeRequest {

    @JsonProperty("uid")
    private Long uid;

    @JsonProperty("state")
    @NotNull
    @Pattern(regexp = "[A-Z]{2}")
    private String state;

    @JsonProperty("inflationRate")
    private Float inflationRate;
}
