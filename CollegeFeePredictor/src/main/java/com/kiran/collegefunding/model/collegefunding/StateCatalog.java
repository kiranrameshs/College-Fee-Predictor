package com.kiran.collegefunding.model.collegefunding;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StateCatalog {
    private static StateCatalog stateCatalog;
    private final List<State> stateList = new ArrayList<>();

    private StateCatalog() {
        init();
    }

    public static StateCatalog getInstance() {
        if (stateCatalog == null) {
            stateCatalog = new StateCatalog();
        }

        return stateCatalog;
    }

    public List<State> getStateList() {
        return new ArrayList<>(stateList);
    }

    private void init() {
        stateList.add(new State(1, "AL", "ALABAMA"));
        stateList.add(new State(2, "AK", "ALASKA"));
        stateList.add(new State(4, "AZ", "ARIZONA"));
        stateList.add(new State(5, "AR", "ARKANSAS"));
        stateList.add(new State(6, "CA", "CALIFORNIA"));
        stateList.add(new State(7, "CO", "COLORADO"));
        stateList.add(new State(8, "CT", "CONNECTICUT"));
        stateList.add(new State(9, "DE", "DELAWARE"));
        stateList.add(new State(10, "DC", "DISTRICT OF COLUMBIA"));
        stateList.add(new State(12, "FL", "FLORIDA"));
        stateList.add(new State(13, "GA", "GEORGIA"));
        stateList.add(new State(15, "HI", "HAWAII"));
        stateList.add(new State(16, "ID", "IDAHO"));
        stateList.add(new State(17, "IL", "ILLINOIS"));
        stateList.add(new State(18, "IN", "INDIANA"));
        stateList.add(new State(19, "IA", "IOWA"));
        stateList.add(new State(20, "KS", "KANSAS"));
        stateList.add(new State(21, "KY", "KENTUCKY"));
        stateList.add(new State(22, "LA", "LOUISIANA"));
        stateList.add(new State(23, "ME", "MAINE"));
        stateList.add(new State(25, "MD", "MARYLAND"));
        stateList.add(new State(26, "MA", "MASSACHUSETTS"));
        stateList.add(new State(27, "MI", "MICHIGAN"));
        stateList.add(new State(28, "MN", "MINNESOTA"));
        stateList.add(new State(29, "MS", "MISSISSIPPI"));
        stateList.add(new State(30, "MO", "MISSOURI"));
        stateList.add(new State(31, "MT", "MONTANA"));
        stateList.add(new State(32, "NE", "NEBRASKA"));
        stateList.add(new State(33, "NV", "NEVADA"));
        stateList.add(new State(34, "NH", "NEW HAMPSHIRE"));
        stateList.add(new State(35, "NJ", "NEW JERSEY"));
        stateList.add(new State(36, "NM", "NEW MEXICO"));
        stateList.add(new State(37, "NY", "NEW YORK"));
        stateList.add(new State(38, "NC", "NORTH CAROLINA"));
        stateList.add(new State(39, "ND", "NORTH DAKOTA"));
        stateList.add(new State(41, "OH", "OHIO"));
        stateList.add(new State(42, "OK", "OKLAHOMA"));
        stateList.add(new State(43, "OR", "OREGON"));
        stateList.add(new State(45, "PA", "PENNSYLVANIA"));
        stateList.add(new State(47, "RI", "RHODE ISLAND"));
        stateList.add(new State(48, "SC", "SOUTH CAROLINA"));
        stateList.add(new State(49, "SD", "SOUTH DAKOTA"));
        stateList.add(new State(50, "TN", "TENNESSEE"));
        stateList.add(new State(51, "TX", "TEXAS"));
        stateList.add(new State(52, "UT", "UTAH"));
        stateList.add(new State(53, "VT", "VERMONT"));
        stateList.add(new State(55, "VA", "VIRGINIA"));
        stateList.add(new State(56, "WA", "WASHINGTON"));
        stateList.add(new State(57, "WV", "WEST VIRGINIA"));
        stateList.add(new State(58, "WI", "WISCONSIN"));
        stateList.add(new State(59, "WY", "WYOMING"));
    }

    public Optional<State> getStateByStateCode(int code){
        return stateList.stream().filter(state->state.getCode()==code).findFirst();
    }

    public Optional<State> getStateByAbbr(String abbr){
        return stateList.stream().filter(state->state.getAbbr().equals(abbr)).findFirst();
    }
}
