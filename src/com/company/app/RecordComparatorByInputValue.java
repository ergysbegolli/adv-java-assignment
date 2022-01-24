package com.company.app;

import com.company.model.Record;

import java.util.Comparator;

import static com.company.app.Utils.getKeyExtractorFromColumnKey;

public class RecordComparatorByInputValue {
    Comparator<Record> cmp;

    public RecordComparatorByInputValue(String by, String stat){
        cmp = Comparator.comparing(getKeyExtractorFromColumnKey(by));

        if (stat.equals("max"))
            cmp = cmp.reversed();
    }
}
