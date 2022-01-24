package com.company.app;

import com.company.data.impl.RecordRepositoryFromCsvFileImpl;
import com.company.data.interfaces.RecordRepository;
import com.company.model.Record;

import java.util.*;
import java.util.function.Function;

import static com.company.app.Utils.*;


public class Main {
    private final RecordRepository recordRepository;

    public Main() {
        recordRepository = new RecordRepositoryFromCsvFileImpl();
    }

    public static void main(String[] args) {
        try {
            Main app = new Main();
            app.go();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void go() {
        askForCommandInput();
        String cmd = getInputCommand();
        if (!isValidCommand(cmd)) {
            System.err.println("Command entered does not match the above pattern");
            return;
        }
        StatVariables vars = new StatVariables(getValuesFromCommand(cmd));
        computeAndPrintStatistics(vars);

    }

    private void computeAndPrintStatistics (StatVariables vars) {
        List<Record> records = recordRepository.getAll(vars.getFilepath());

        Comparator<Record> cmp = new RecordComparatorByInputValue(vars.getBy(), vars.getStat()).cmp;

        Function<Record, String> recordToDisplayData = (rec) -> {
            String result = getDisplayData(rec, vars.getDisplay());
            result += ", ";
            result += getKeyExtractorFromColumnKey(vars.getBy()).apply(rec);
            return result;
        };

        records
                .stream()
                .sorted(cmp)
                .limit(vars.getLimit())
                .map(recordToDisplayData)
                .forEach(System.out::println);
    }
}

