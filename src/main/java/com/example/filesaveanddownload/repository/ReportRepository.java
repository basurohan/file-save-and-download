package com.example.filesaveanddownload.repository;

import com.example.filesaveanddownload.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Integer> {
}
