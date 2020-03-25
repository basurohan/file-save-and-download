package com.example.filesaveanddownload;

import com.example.filesaveanddownload.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class FileSaveAndDownloadApplication implements CommandLineRunner {

//    https://github.com/chargeahead/UploadDownloadDatabase

    private final ReportService reportService;

    public FileSaveAndDownloadApplication(ReportService reportService) {
        this.reportService = reportService;
    }

    public static void main(String[] args) {
        SpringApplication.run(FileSaveAndDownloadApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting call");
        byte[] report1 = reportService.fetchSummaryReport();
        reportService.saveSummaryReport(report1);
        String report2 = reportService.fetchReport();
        reportService.saveReport(report2);
        log.info("Report saved");
    }
}
