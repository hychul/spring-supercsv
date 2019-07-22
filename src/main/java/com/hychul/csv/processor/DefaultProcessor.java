package com.hychul.csv.processor;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.util.CsvContext;

public class DefaultProcessor extends CellProcessorAdaptor implements StringCellProcessor {

    @Override
    @SuppressWarnings("unchecked")
    public Object execute(Object value, CsvContext context) {
        return value;
    }
}
