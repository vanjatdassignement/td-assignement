package com.td.reporting.factory;

import org.apache.commons.lang3.NotImplementedException;

import com.td.reporting.enums.DataSourceType;
import com.td.reporting.repository.CsvRepository;
import com.td.reporting.repository.DbRepository;
import com.td.reporting.repository.ReportingRepository;
import com.td.reporting.repository.WebServiceRepository;

/**
 * Abstract repository factory.
 *
 */
public class RepositoryFactory {

	private static CsvRepository csvRepository = new CsvRepository();
	private static DbRepository dbRepository = new DbRepository();
	private static WebServiceRepository webServiceRepository = new WebServiceRepository();
	
	/**
	 * Factory method which return appropriate repository based on provided datasourceType.
	 * 
	 * @param dataSourceType
	 * @return {@link ReportingRepository} based on {@link DataSourceType} parameter
	 */
	public static ReportingRepository getRepositoryInstance(DataSourceType dataSourceType) {
		switch (dataSourceType) {
		case CSV:
			return csvRepository;
		case DB:
			return dbRepository;
		case WEB_SERVICE:
			return webServiceRepository;
		default:
			throw new NotImplementedException();
		}
	}

}
