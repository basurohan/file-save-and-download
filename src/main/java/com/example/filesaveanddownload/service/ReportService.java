package com.example.filesaveanddownload.service;

import com.example.filesaveanddownload.model.Report;
import com.example.filesaveanddownload.repository.ReportRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
public class ReportService {

    private RestTemplate restTemplate = new RestTemplate();

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public byte[] fetchSummaryReport() {
        HttpEntity<String> request = createHttpEntity();
        ResponseEntity<byte[]> response = restTemplate.exchange("http://localhost:3000/api/vehicle/history/summary",
                HttpMethod.POST, request, byte[].class);

        return response.getBody();
    }

    public String fetchReport() {
        HttpEntity<String> request = createHttpEntity();
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:3000/api/vehicle/history/accident",
                HttpMethod.POST, request, String.class);

        return response.getBody();
    }

    public void saveSummaryReport(byte[] report) {
        reportRepository.save(Report.builder()
                .reportName("summary-report")
                .reportType(MediaType.TEXT_HTML_VALUE)
                .created(Instant.now())
                .summaryReport(report)
                .build());
    }

    public void saveReport(String report) {
        reportRepository.save(Report.builder()
                .reportName("other-report")
                .reportType(MediaType.APPLICATION_JSON_VALUE)
                .created(Instant.now())
                .historyReport(report)
                .build());
    }

    public Report getReport(Integer id) {
        return reportRepository.findById(id).orElse(null);
    }

    public HttpEntity<String> createHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", "eblock-api-key");
        headers.set("x-autotec-id", "eblock-client-id");
        return new HttpEntity<>(headers);
    }

}
