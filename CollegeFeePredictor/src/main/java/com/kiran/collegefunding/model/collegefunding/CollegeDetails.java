package com.kiran.collegefunding.model.collegefunding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
    public class CollegeDetails {

        private Long uid;

        public String institutionName;

        public String city;

        public String stateAbbr;

        public String zipCode;

//        public Integer degreeOffered;

        public String institutionType;

        public Integer courseDuration;

    }

