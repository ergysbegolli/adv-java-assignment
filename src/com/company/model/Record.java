package com.company.model;

import java.util.Map;

import static com.company.app.Utils.safeDivide;
import static com.company.app.Utils.safeParseToDouble;

public class Record {
    private String iso_code;
    private String continent;
    private String location;
    private String date;
    private int total_cases;
    private int new_cases;
    private double new_cases_smoothed;
    private int total_deaths;
    private int new_deaths;
    private double new_deaths_smoothed;
    private double reproduction_rate;
    private int new_tests;
    private double stringency_index;
    private int population;
    private double median_age;
    private double new_deaths_per_case;


    public Record(Map<Column, String> record) {
        this.iso_code = record.get(Column.COD);
        this.continent = record.get(Column.CNT);
        this.location = record.get(Column.LOC);
        this.date = record.get(Column.DT);
        this.total_cases = (int) safeParseToDouble(record.get(Column.TC));
        this.new_cases = (int) safeParseToDouble(record.get(Column.NC));
        this.new_cases_smoothed = safeParseToDouble(record.get(Column.NCS));
        this.total_deaths = (int) safeParseToDouble(record.get(Column.TD));
        this.new_deaths = (int) safeParseToDouble(record.get(Column.ND));
        this.new_deaths_smoothed = safeParseToDouble(record.get(Column.NDS));
        this.reproduction_rate = safeParseToDouble(record.get(Column.RR));
        this.new_tests = (int) safeParseToDouble(record.get(Column.NT));
        this.stringency_index = safeParseToDouble(record.get(Column.SI));
        this.population = (int) safeParseToDouble(record.get(Column.POP));
        this.median_age = safeParseToDouble(record.get(Column.MA));
        this.new_deaths_per_case = safeDivide(this.new_deaths, this.new_cases);
    }

    public String getContinent() {
        return continent;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public int getNew_cases() {
        return new_cases;
    }

    public double getNew_cases_smoothed() {
        return new_cases_smoothed;
    }

    public int getNew_deaths() {
        return new_deaths;
    }

    public double getNew_deaths_smoothed() {
        return new_deaths_smoothed;
    }

    public int getNew_tests() {
        return new_tests;
    }

    public double getNew_deaths_per_case() {
        return new_deaths_per_case;
    }
}
