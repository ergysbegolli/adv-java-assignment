package com.company.data.interfaces;

import com.company.model.Record;

import java.util.List;

public interface RecordRepository {
    List<Record> getAll(String filepath);
}
