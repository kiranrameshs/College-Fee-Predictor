package com.kiran.collegefunding.model.collegefunding;

public enum InstitutionType {

    PUBLIC_INSTATE("Public In State", "1"),
    PUBLIC_OUTOFSTATE("Public Out of State", "2"),
    PRIVATE("Private","3");


    private String stringValue;
    private String integerValue;

    InstitutionType(String stringValue, String integerValue) {

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
        for(InstitutionType e : InstitutionType.values()){
            if(e.getIntegerValue().equals(value)) return e.getStringValue();
        }
        return null;
    }
}
