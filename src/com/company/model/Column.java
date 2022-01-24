package com.company.model;

public enum Column {
    COD("iso_code"),
    CNT("continent"),
    LOC("location"),
    DT("date"),
    TC("total_cases"),
    NC("new_cases"),
    NCS("new_cases_smoothed"),
    TD("total_deaths"),
    ND("new_deaths"),
    NDS("new_deaths_smoothed"),
    RR("reproduction_rate"),
    NT("new_tests"),
    SI("stringency_index"),
    POP("population"),
    MA("median_age"),
    NDPC("new_deaths_per_case");

    private final String column;

    public String getColumn() {
        return column;
    }

    Column(String column) {
        this.column = column;
    }

    public static Column fromColumn(String column) {
        switch (column) {
            case "iso_code":
                return COD;
            case "continent":
                return CNT;
            case "location":
                return LOC;
            case "date":
                return DT;
            case "total_cases":
                return TC;
            case "new_cases":
                return NC;
            case "new_cases_smoothed":
                return NCS;
            case "total_deaths":
                return TD;
            case "new_deaths":
                return ND;
            case "new_deaths_smoothed":
                return NDS;
            case "reproduction_rate":
                return RR;
            case "new_tests":
                return NT;
            case "stringency_index":
                return SI;
            case "population":
                return POP;
            case "median_age":
                return MA;
            case "new_deaths_per_case":
                return NDPC;
            default:
                throw new IllegalArgumentException("invalid column name: " + column);
        }
    }
}
