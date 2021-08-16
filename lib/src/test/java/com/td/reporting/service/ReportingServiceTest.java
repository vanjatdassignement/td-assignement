package com.td.reporting.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.td.reporting.enums.DataSourceType;
import com.td.reporting.exceptions.ReportProcessingException;
import com.td.reporting.model.ReportRequest;

public class ReportingServiceTest {
	
	private ReportingService reportingService = new ReportingService();
	
	Path resourceDirectory = Paths.get("src","test","resources");
	String absolutePath = resourceDirectory.toFile().getAbsolutePath();
	
	File refDataCsv = Paths.get("src","test","resources", "refdata (2) (2) (1) (1) (1).csv").toFile();
	File tradeCsv = Paths.get("src","test","resources", "trade (2) (2) (1) (1) (1).csv").toFile();
	File valuationCsv = Paths.get("src","test","resources", "valuation (2) (2) (1) (1) (1).csv").toFile();
	File expectedCsv = Paths.get("src","test","resources", "output.csv").toFile();
	
	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();
	
	@Test
	public void withValidSources_ReportIsGeneratedSuccessfully() throws IOException, ReportProcessingException {
		
		String testFilePath = temporaryFolder.getRoot().getAbsolutePath() + "\\test.csv";
		ReportRequest reportRequest = new ReportRequest();
		
		reportRequest.setRefDataSource(DataSourceType.CSV);
		reportRequest.setRefDataCriteria(refDataCsv.getAbsolutePath());
		reportRequest.setTradeSource(DataSourceType.CSV);
		reportRequest.setTradeCriteria(tradeCsv.getAbsolutePath());
		reportRequest.setValuationSource(DataSourceType.CSV);
		reportRequest.setValuationCriteria(valuationCsv.getAbsolutePath());
		reportRequest.setReportDestination(DataSourceType.CSV);
		reportRequest.setReportCriteria(testFilePath);
		
		reportingService.processReport(reportRequest);
		
		assertEquals(Files.readString(expectedCsv.toPath()), 
				Files.readString(Path.of(testFilePath)));
    }
	
	@Test(expected = ReportProcessingException.class)
	public void withInvalidRefDataPath_ReportProcessingExceptionIsThrown() throws IOException, ReportProcessingException {
		
		String testFilePath = temporaryFolder.getRoot().getAbsolutePath() + "\\test.csv";
		ReportRequest reportRequest = new ReportRequest();
		
		reportRequest.setRefDataSource(DataSourceType.CSV);
		reportRequest.setRefDataCriteria("fakePath");
		reportRequest.setTradeSource(DataSourceType.CSV);
		reportRequest.setTradeCriteria(tradeCsv.getAbsolutePath());
		reportRequest.setValuationSource(DataSourceType.CSV);
		reportRequest.setValuationCriteria(valuationCsv.getAbsolutePath());
		reportRequest.setReportDestination(DataSourceType.CSV);
		reportRequest.setReportCriteria(testFilePath);
		
		reportingService.processReport(reportRequest);
    }
	
	@Test(expected = ReportProcessingException.class)
	public void withInvalidTradePath_ReportProcessingExceptionIsThrown() throws IOException, ReportProcessingException {
		
		String testFilePath = temporaryFolder.getRoot().getAbsolutePath() + "\\test.csv";
		ReportRequest reportRequest = new ReportRequest();
		
		reportRequest.setRefDataSource(DataSourceType.CSV);
		reportRequest.setRefDataCriteria(refDataCsv.getAbsolutePath());
		reportRequest.setTradeSource(DataSourceType.CSV);
		reportRequest.setTradeCriteria("fakePath");
		reportRequest.setValuationSource(DataSourceType.CSV);
		reportRequest.setValuationCriteria(valuationCsv.getAbsolutePath());
		reportRequest.setReportDestination(DataSourceType.CSV);
		reportRequest.setReportCriteria(testFilePath);
		
		reportingService.processReport(reportRequest);
    }
	
	@Test(expected = ReportProcessingException.class)
	public void withInvalidValuationPath_ReportProcessingExceptionIsThrown() throws IOException, ReportProcessingException {
		
		String testFilePath = temporaryFolder.getRoot().getAbsolutePath() + "\\test.csv";
		ReportRequest reportRequest = new ReportRequest();
		
		reportRequest.setRefDataSource(DataSourceType.CSV);
		reportRequest.setRefDataCriteria(refDataCsv.getAbsolutePath());
		reportRequest.setTradeSource(DataSourceType.CSV);
		reportRequest.setTradeCriteria(tradeCsv.getAbsolutePath());
		reportRequest.setValuationSource(DataSourceType.CSV);
		reportRequest.setValuationCriteria("fakePath");
		reportRequest.setReportDestination(DataSourceType.CSV);
		reportRequest.setReportCriteria(testFilePath);
		
		reportingService.processReport(reportRequest);
    }
	
	@Test(expected = ReportProcessingException.class)
	public void withInvalidReportPath_ReportProcessingExceptionIsThrown() throws IOException, ReportProcessingException {
		
		ReportRequest reportRequest = new ReportRequest();
		
		reportRequest.setRefDataSource(DataSourceType.CSV);
		reportRequest.setRefDataCriteria(refDataCsv.getAbsolutePath());
		reportRequest.setTradeSource(DataSourceType.CSV);
		reportRequest.setTradeCriteria(tradeCsv.getAbsolutePath());
		reportRequest.setValuationSource(DataSourceType.CSV);
		reportRequest.setValuationCriteria(valuationCsv.getAbsolutePath());
		reportRequest.setReportDestination(DataSourceType.CSV);
		reportRequest.setReportCriteria("G:/fakePath");
		
		reportingService.processReport(reportRequest);
    }
	
	@Test(expected = IllegalArgumentException.class)
	public void withMissingReportValues_IllegalArgumentExceptionIsThrown() throws IOException, ReportProcessingException {
		
		ReportRequest reportRequest = new ReportRequest();
		
		reportingService.processReport(reportRequest);
    }

}
