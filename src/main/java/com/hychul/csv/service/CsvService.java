package com.hychul.csv.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.CsvContext;

import com.hychul.csv.processor.DefaultProcessor;
import com.hychul.csv.service.model.CsvEntity;

@Service
public class CsvService {
    static final String CHARSET_NAME = "UTF-8";

    public CsvEntity readCsv(MultipartFile file) throws IOException {
        ICsvMapReader mapReader = new CsvMapReader(new InputStreamReader(file.getInputStream()), CsvPreference.STANDARD_PREFERENCE);

        final String[] headers = mapReader.getHeader(true);
        final CellProcessor[] processors = getProcessors(headers.length);

        final List<Map<String, Object>> columns = new ArrayList<>();
        Map<String, Object> columnMap;
        while( (columnMap = mapReader.read(headers, processors)) != null ) {
            columns.add(columnMap);
        }

        return new CsvEntity(headers, columns);
    }

    public void writeCsv(OutputStream outputStream, CsvEntity csvEntity) throws IOException {
        ICsvMapWriter mapWriter = new CsvMapWriter(new OutputStreamWriter(outputStream, Charset.forName(CHARSET_NAME)), CsvPreference.STANDARD_PREFERENCE);

        final String[] headers = csvEntity.getHeaders();
        final CellProcessor[] processors = getProcessors(headers.length);
        final List<Map<String, Object>> columns = csvEntity.getColumns();

        mapWriter.writeHeader(headers);

        for (int i = 0; i < columns.size(); i++) {
            mapWriter.write(columns.get(i), headers, processors);
        }
    }

//    private static CellProcessor[] getProcessors(int size) {
//        final CellProcessor[] cellProcessors = new CellProcessor[size];
//        for (int i = 0; i < size; i++) {
//            cellProcessors[i] = new DefaultProcessor();
//        }
//        return cellProcessors;
//    }


    private CellProcessor[] getProcessors(int size) {
        CellProcessor[] cellProcessors = new CellProcessor[size];
        for (int i = 0; i < size; i++) {
            cellProcessors[i] = new CellProcessor() {
                @Override
                public String execute(Object value, CsvContext context) {
                    return value.toString();
                }
            };
        }
        return cellProcessors;
    }
}
