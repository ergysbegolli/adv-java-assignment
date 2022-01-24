package com.company.app;

import com.company.model.Column;
import com.company.model.Record;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Pattern;

public class Utils {
    private static final String pattern = "^-file\\s\\S+\\s-stat\\s\\b(min|max)\\b\\s-limit\\s\\b([1-9]|[1-9][0-9]|100)\\b\\s-by\\s\\b(NC|NCS|ND|NDS|NT|NDPC)\\b\\s-display\\s\\b(DATE|COUNTRY|CONTINENT)\\b\\s?$";
    private static Pattern patternObj = Pattern.compile(pattern);

    public static void askForCommandInput() {
        System.out.println("Please enter the command matching the below written pattern: \n");
        System.out.println("-file `filename` -stat min|max -limit [1..100] -by NC|NCS|ND|NDS|NT|NDPC -display DATE|COUNTRY|CONTINENT\n");
    }

    public static String getInputCommand() {
        Scanner scanner = new Scanner(System.in);
        String cmd = scanner.nextLine();
        return cmd;
    }

    public static boolean isValidCommand(String cmd) {
        return patternObj.matcher(cmd).matches();
    }

    public static List<String> getValuesFromCommand(String command) {
        return Arrays.stream(command.split(" ")).filter(word -> !word.startsWith("-")).toList();
    }

    public static double safeParseToDouble(String value) {
        if (value == null || value.isEmpty()) return 0;

        return Double.parseDouble(value);
    }

    public static double safeDivide(int a1, int a2) {
        if (a2 == 0) return a2;
        return a1 / a2;
    }

    public static Boolean isNotEmpty(String str) {
        if (str == null || str.isEmpty()) return false;
        return true;
    }

    public static Boolean areNotEmpty(String... strs) {
        return Arrays.stream(strs).filter(s -> !isNotEmpty(s)).toList().size() == 0;
    }

    public static Boolean isValidColumnName(String columnName) {
        return Arrays.stream(Column.values()).map(Column::getColumn).anyMatch(columnName::equals);
    }

    public static Function<Record, Comparable> getKeyExtractorFromColumnKey(String by) {
        switch (by) {
            case "NC":
                return Record::getNew_cases;
            case "NCS":
                return Record::getNew_cases_smoothed;
            case "ND":
                return Record::getNew_deaths;
            case "NDS":
                return Record::getNew_deaths_smoothed;
            case "NT":
                return Record::getNew_tests;
            case "NDPC":
                return Record::getNew_deaths_per_case;
        }
        throw new IllegalArgumentException("invalid column code: " + by);
    }

    public static  String getDisplayData(Record rec, String display) {
        switch (display) {
            case "DATE":
                return rec.getDate();
            case "COUNTRY":
                return rec.getLocation();
            case "CONTINENT":
                return rec.getContinent();
        }
        throw new IllegalArgumentException("invalid display code: " + display);
    }
}
