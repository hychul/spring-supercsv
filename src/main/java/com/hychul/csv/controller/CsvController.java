package com.hychul.csv.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hychul.csv.service.CsvService;
import com.hychul.csv.service.model.CsvEntity;

@RestController
@RequestMapping("/csv")
public class CsvController {

    @Autowired
    CsvService csvService;

    @PostMapping("/upload")
    public void upload(@RequestBody MultipartFile file) {
        throw new NotImplementedException("");
    }

    @PostMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        throw new NotImplementedException("");
    }

    @PostMapping("/relay")
    public void relay(@RequestBody MultipartFile file, HttpServletResponse response) throws IOException {
        CsvEntity csvEntity = csvService.readCsv(file);

        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"result.csv\"");
        response.setContentType("text/csv");

        csvService.writeCsv(response.getOutputStream(), csvEntity);
    }
}
