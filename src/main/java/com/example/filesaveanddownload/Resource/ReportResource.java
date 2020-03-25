package com.example.filesaveanddownload.Resource;

import com.example.filesaveanddownload.model.Report;
import com.example.filesaveanddownload.service.ReportService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportResource {

    private final ReportService reportService;

    public ReportResource(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable final Integer id) {
        Report report = reportService.getReport(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(report.getReportType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+report.getReportName()+"\"")
                .body(new ByteArrayResource(report.getSummaryReport()));
    }

}
