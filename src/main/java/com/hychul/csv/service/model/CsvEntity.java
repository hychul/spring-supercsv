package com.hychul.csv.service.model;

import java.util.List;
import java.util.Map;

public class CsvEntity {

    private String[] headers;
    private List<Map<String, Object>> columns;

    public CsvEntity(String[] headers, List<Map<String, Object>> columns) {
        this.headers = headers;
        this.columns = columns;
    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public List<Map<String, Object>> getColumns() {
        return columns;
    }

    public void setColumns(List<Map<String, Object>> columns) {
        this.columns = columns;
    }
}
