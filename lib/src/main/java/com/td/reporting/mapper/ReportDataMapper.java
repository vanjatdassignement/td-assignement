package com.td.reporting.mapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.td.reporting.constants.Constants;
import com.td.reporting.enums.BreakStatus;
import com.td.reporting.enums.Term;
import com.td.reporting.model.RefData;
import com.td.reporting.model.Report;
import com.td.reporting.model.Trade;
import com.td.reporting.model.Valuation;

public class ReportDataMapper {
	
	/**
	 * Maps provided report components to {@link Report}, computes MS-PC, BreakStatus and Term fields.
	 * 
	 * @param refData
	 * @param trade
	 * @param valuation
	 * @param now
	 * @return Returns mapped {@link Report}
	 */
	public static Report mapReportData(RefData refData, Trade trade, 
			Valuation valuation, LocalDate now) {
		
		Report report = new Report();
		
		report.setTopOfHouse(Objects.isNull(refData) ? Constants.NA : refData.getTopOfHouse());
		report.setSegment(Objects.isNull(refData) ? Constants.NA : refData.getSegment());
		report.setViceChair(Objects.isNull(refData) ? Constants.NA : refData.getViceChair());
		report.setGlobalBusiness(Objects.isNull(refData) ? Constants.NA : refData.getGlobalBusiness());
		report.setPolicy(Objects.isNull(refData) ? Constants.NA : refData.getPolicy());
		report.setDesk(Objects.isNull(refData) ? Constants.NA : refData.getDesk());
		report.setPortfolio(Objects.isNull(refData) ? Constants.NA : refData.getPolicy());
		report.setBu(Objects.isNull(refData) ? 0 : refData.getBu());
		report.setCline(Objects.isNull(refData) ? Constants.NA : refData.getCline());
		
		report.setInventory(Objects.toString(trade.getInventory(), Constants.NA));
		report.setBook(Objects.toString(trade.getBook(), Constants.NA));
		report.setSystem(Objects.toString(trade.getSystem(), Constants.NA));
		report.setLegalEntity(Objects.toString(trade.getLegalEntity(), Constants.NA));
		report.setTradeId(trade.getTradeId());
		report.setVersion(trade.getVersion());
		report.setTradeStatus(Objects.toString(trade.getTradeStatus(), Constants.NA));
		report.setProductType(Objects.toString(trade.getProductType(), Constants.NA));
		report.setResettingLeg(Objects.toString(trade.getResettingLeg(), Constants.NA));
		report.setProductSubType(Objects.toString(trade.getProductSubType(), Constants.NA));
		report.setTdsProductType(Objects.toString(trade.getTdsProductType(), Constants.NA));
		report.setSecCodeSubType(Objects.toString(trade.getSecCodeSubType(), Constants.NA));
		report.setSwapType(Objects.toString(trade.getSwapType(), Constants.NA));
		report.setDescription(Objects.toString(trade.getDescription(), Constants.NA));
		report.setTradeDate(Objects.toString(trade.getTradeDate(), Constants.NA));
		report.setStartDate(Objects.toString(trade.getStartDate(), Constants.NA));
		report.setMaturityDate(Objects.toString(trade.getMaturityDate(), Constants.NA));
		
		report.setUqlOcMmbMs(Objects.isNull(valuation) ? null : valuation.getUqlOcMmbMs());
		report.setUqlOcMmbMsPc(Objects.isNull(valuation) ? null : valuation.getUqlOcMmbMsPc());
		
		report.setMsPc(calculateMsMinusPc(report.getUqlOcMmbMs(), report.getUqlOcMmbMsPc()));
		report.setBreakStatus(Objects.nonNull(report.getMsPc()) 
				? calculateBreakStatus(report.getMsPc().abs().longValue()) : null);
		report.setTerm(calculateTerm(report.getMaturityDate(), now));
		
		return report;
	}
	
	/**
	 * Calculates MS-PC value, returns null if any of the parameters is null.
	 * @param uqlOcMmbMs
	 * @param uqlOcMmbMsPc
	 * @return
	 */
	public static BigDecimal calculateMsMinusPc(BigDecimal uqlOcMmbMs, BigDecimal uqlOcMmbMsPc) {
		if(Objects.nonNull(uqlOcMmbMs) && Objects.nonNull(uqlOcMmbMsPc)) {
			return uqlOcMmbMs.subtract(uqlOcMmbMsPc);
		}
		return null;
	}
	
	/**
	 * Calculates absolute value of the MS-PC value, returns null if MS-PC value is null.
	 * 
	 * @param msPc
	 * @return
	 */
	public static BigDecimal calculateAbsMsPc(BigDecimal msPc) {
		if(Objects.nonNull(msPc)) {
			return msPc.abs();
		}
		return null;
	}
	
	/**
	 * Calculates BreakStatus based on the Break Value.
	 * 
	 * @param value
	 * @return
	 */
	public static String calculateBreakStatus(long value) {
		
		return Arrays.asList(BreakStatus.values()).stream()
				.filter(it -> it.isInRange(value))
				.findFirst().orElseThrow(IllegalArgumentException::new)
				.getLabel();
	} 
	
	/**
	 * Calculates Term based on provided maturityDate and provided date. if 
	 * 
	 * @param maturityDateString
	 * @param now
	 * @return
	 */
	public static String calculateTerm(String maturityDateString, LocalDate now) {
		
		if(StringUtils.isEmpty(maturityDateString)) {
			return null;
		}
		
		LocalDate maturityDate = null;
		try {
			maturityDate = DateTimeFormatter.BASIC_ISO_DATE
				.parse(maturityDateString, LocalDate::from);
		} catch(DateTimeParseException e) {
			// TODO: log error, should processing stop here if data contains invalid entries?
			// Assumption is that logged error is sufficient and report process shouldn't be blocked
		}
		
		if(maturityDate.isBefore(now)) {
			return null;
		} 
		
		long difference = ChronoUnit.MONTHS.between(
				now.withDayOfMonth(1),
		        maturityDate.withDayOfMonth(1));
		
		return Arrays.asList(Term.values()).stream()
				.filter(it -> it.isInRange(difference))
				.findFirst().orElseThrow(IllegalArgumentException::new)
				.getLabel();
	}

}
