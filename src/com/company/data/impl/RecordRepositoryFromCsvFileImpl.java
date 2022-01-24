package com.company.data.impl;

import com.company.data.interfaces.RecordRepository;
import com.company.model.Record;
import com.company.model.Column;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.company.app.Utils.areNotEmpty;
import static com.company.app.Utils.isValidColumnName;

public class RecordRepositoryFromCsvFileImpl implements RecordRepository {
    @Override
    public List<Record> getAll(String filepath) {
        try {
            List<String[]> rows = getRowsFromCsv(filepath);
            return retrieveRecords(rows);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<String[]> getRowsFromCsv(String filepath) throws IOException {
        List<String[]> rowData = new ArrayList<>();
        Files.lines(Paths.get(filepath))
                .map(line -> Arrays.copyOf(line.split(","), 50))
                .forEach(rowData::add);

        return rowData;
    }

    private List<Record> retrieveRecords(List<String[]> rows) {
        String[] headers = rows.remove(0);

        Set<Integer> validIndexes = IntStream
                .range(0, headers.length)
                .filter(i -> isValidColumnName(headers[i]))
                .boxed()
                .collect(Collectors.toSet());

        Function<String[], Map<Column, String>> rowToLabeledRowCells = (row) -> validIndexes
                .stream()
                .collect(HashMap<Column, String>::new,
                        (m, i) -> m.put(Column.fromColumn(headers[i]), row[i]),
                        Map::putAll);

        return rows
                .stream()
                .map(rowToLabeledRowCells)
                .filter(checkDisplayColumnsAreNotEmpty)
                .map(Record::new)
                .toList();
    }

    private Predicate<Map<Column, String>> checkDisplayColumnsAreNotEmpty = (map) -> areNotEmpty(map.get(Column.DT), map.get(Column.LOC), map.get(Column.CNT));
}
