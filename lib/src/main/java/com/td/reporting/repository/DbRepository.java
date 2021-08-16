package com.td.reporting.repository;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.NotImplementedException;

import com.td.reporting.model.RefData;
import com.td.reporting.model.Report;
import com.td.reporting.model.Trade;
import com.td.reporting.model.Valuation;


public class DbRepository implements ReportingRepository {

	@Override
	public Map<String, RefData> fetchRefData(String criteria) {
		throw new NotImplementedException();
	}

	@Override
	public List<Trade> fetchTrade(String criteria) {
		throw new NotImplementedException();
	}

	@Override
	public Map<Long, Valuation> fetchValuationData(String criteria) {
		throw new NotImplementedException();
	}

	@Override
	public void saveReport(List<Report> report, String criteria) {
		throw new NotImplementedException();
	}

}
