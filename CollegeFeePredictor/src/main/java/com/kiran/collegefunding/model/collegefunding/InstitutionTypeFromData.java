package com.kiran.collegefunding.model.collegefunding;

public enum InstitutionTypeFromData {

    PUBLIC_INSTITUTE("Public Institute", "1"),
    PRIVATE_NONPROFIT_INSTITUTE("Private Institute","2"),
    PRIVATE_FORPROFIT_INSTITUTE("Private Institute","3");


    private String stringValue;
    private String integerValue;

    InstitutionTypeFromData(String stringValue, String integerValue) {

        this.stringValue = stringValue;
        this.integerValue = integerValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public String getIntegerValue() {
        return integerValue;
    }

    public static String getStringValueByIntegerValue(String value){
        for(InstitutionTypeFromData e : InstitutionTypeFromData.values()){
            if(e.getIntegerValue().equals(value)) return e.getStringValue();
        }
        return null;
    }
}
