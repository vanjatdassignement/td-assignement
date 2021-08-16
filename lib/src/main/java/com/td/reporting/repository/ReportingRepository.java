package com.td.reporting.repository;

import java.util.List;
import java.util.Map;

import com.td.reporting.exceptions.ReportProcessingException;
import com.td.reporting.model.RefData;
import com.td.reporting.model.Report;
import com.td.reporting.model.Trade;
import com.td.reporting.model.Valuation;

public interface ReportingRepository {
	
	public Map<String, RefData> fetchRefData(String criteria) throws ReportProcessingException;
	
	public List<Trade> fetchTrade(String criteria) throws ReportProcessingException;
	
	public Map<Long, Valuation> fetchValuationData(String criteria) throws ReportProcessingException;
	
	public void saveReport(List<Report> report, String criteria) throws ReportProcessingException;

}
