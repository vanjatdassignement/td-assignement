package com.td.reporting.model;

import java.util.Objects;

import com.td.reporting.enums.DataSourceType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportRequest {
	
	private DataSourceType refDataSource;
	private String refDataCriteria;
	private DataSourceType tradeSource;
	private String tradeCriteria;
	private DataSourceType valuationSource;
	private String valuationCriteria;
	private DataSourceType reportDestination;
	private String reportCriteria;
	
	public boolean anyUnset() {
		if(Objects.isNull(refDataSource))
			return true;
		if(Objects.isNull(refDataCriteria))
			return true;
		if(Objects.isNull(tradeSource))
			return true;
		if(Objects.isNull(tradeCriteria))
			return true;
		if(Objects.isNull(valuationSource))
			return true;
		if(Objects.isNull(valuationCriteria))
			return true;
		if(Objects.isNull(reportDestination))
			return true;
		if(Objects.isNull(reportCriteria))
			return true;
		return false;
	}

}
