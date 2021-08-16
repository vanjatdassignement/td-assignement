package com.td.reporting.repository;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.ColumnType;
import com.td.reporting.constants.Constants;
import com.td.reporting.exceptions.ReportProcessingException;
import com.td.reporting.model.RefData;
import com.td.reporting.model.Report;
import com.td.reporting.model.Trade;
import com.td.reporting.model.Valuation;

/**
 * CSV implementation of the {@link ReportingRepository}
 *
 */
public class CsvRepository implements ReportingRepository {
	
	/**
	 * Fetches and parses {@link RefData#} from provided CSV path
	 */
	@Override
	public Map<String, RefData> fetchRefData(String criteria) throws ReportProcessingException {
		
		return parseCsv(criteria, RefData.class)
				.stream()
				.collect(Collectors.toMap(RefData::getInventory, Function.identity()));
	}
	
	/**
	 * Fetches and parses {@link Trade#} from provided CSV path
	 */
	@Override
	public List<Trade> fetchTrade(String criteria) throws ReportProcessingException {
		
		return parseCsv(criteria, Trade.class);
	}
	
	/**
	 * Fetches and parses {@link Valuation#} from provided CSV path
	 */
	@Override
	public Map<Long, Valuation> fetchValuationData(String criteria) throws ReportProcessingException {
		return parseCsv(criteria, Valuation.class)
				.stream()
				.collect(Collectors.toMap(Valuation::getTradeId, Function.identity()));
	}
	
	/**
	 * Stores provided {@link Report#} to path provided as criteria parameter.
	 */
	@Override
	public void saveReport(List<Report> reportData, String criteria) throws ReportProcessingException {
        File csvOutputFile = new File(criteria);
		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = CsvSchema.builder()
				.setUseHeader(true)
				.addColumns(Arrays.asList(Constants.REPORT_FIELDS), ColumnType.STRING_OR_LITERAL)
                .build();

        try {
        	mapper
        	.writerFor(Report.class)
        	.with(schema)
        	.writeValues(csvOutputFile).writeAll(reportData);
		} catch (IOException e) {
			throw new ReportProcessingException(
					String.format("Cannot save CSV report on path: ", criteria), e);
		}
		
	}
	
	private <T> List<T> parseCsv(String criteria, Class<T> type) throws ReportProcessingException {
		File csvFile = new File(criteria);
		CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
		
		try {
			MappingIterator<T> mappingIterator = new CsvMapper()
					.readerFor(type)
					.with(bootstrapSchema)
					.readValues(csvFile);
			return mappingIterator.readAll();
		} catch (IOException e) {
			throw new ReportProcessingException(
					String.format("Cannot parse CSV on path: %s to type: %s", criteria, type.getSimpleName()), e);
		}
	}

}
