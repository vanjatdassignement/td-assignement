package com.td.reporting.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.td.reporting.exceptions.ReportProcessingException;
import com.td.reporting.factory.RepositoryFactory;
import com.td.reporting.mapper.ReportDataMapper;
import com.td.reporting.model.RefData;
import com.td.reporting.model.Report;
import com.td.reporting.model.ReportRequest;
import com.td.reporting.model.Trade;
import com.td.reporting.model.Valuation;

/**
 * 
 * Report processing service. For this task assumption was that no frameworks should be used.
 * ReportingService and repositories could be easily transfered to Spring beans or similar.
 * Service uses repo factory to allow combination of sources for report.
 * 
 * Considering the time needed for this task some features were not considered:
 * Logging was not implemented
 * JUnit testing should also be improved since only ReportingService was tested and multiple cases
 * were not covered. 
 *
 */
public class ReportingService {
	
	/**
	 * Based on the provided reportRequest parameter method can combine report from multiple sources
	 * ie. ref data could be sourced from DB, trade data from WS and valuation from CSV, report could be
	 * stored to DB. Any combination should work given that all repos are implemented.
	 * 
	 * @param reportRequest
	 * @throws ReportProcessingException
	 */
	public void processReport(ReportRequest reportRequest) throws ReportProcessingException {
		
		if(reportRequest.anyUnset()) 
			throw new IllegalArgumentException("Provided reportRequest contains null values");
		
		Map<String, RefData> refDataMap = RepositoryFactory
				.getRepositoryInstance(reportRequest.getRefDataSource())
				.fetchRefData(reportRequest.getRefDataCriteria());
		
		List<Trade> tradeList = RepositoryFactory
				.getRepositoryInstance(reportRequest.getTradeSource())
				.fetchTrade(reportRequest.getTradeCriteria());
		
		Map<Long, Valuation> valuationMap = RepositoryFactory
				.getRepositoryInstance(reportRequest.getValuationSource())
				.fetchValuationData(reportRequest.getValuationCriteria());
		
		LocalDate now = LocalDate.now();
		
		List<Report> reportData = tradeList.stream().map( trade -> {
			return ReportDataMapper.mapReportData(
					refDataMap.get(trade.getInventory()),
					trade,
					valuationMap.get(trade.getTradeId()),
					now
			);
		}).collect(Collectors.toList());
		
		RepositoryFactory.getRepositoryInstance(reportRequest.getReportDestination())
			.saveReport(reportData, reportRequest.getReportCriteria());
	}

}
