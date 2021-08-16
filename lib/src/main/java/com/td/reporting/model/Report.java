package com.td.reporting.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "topOfHouse", "segment", "viceChair", "globalBusiness", "policy",
	"desk", "portfolio", "bu", "cline", "inventory", "book", "system", "legalEntity", "tradeId",
	"version", "tradeStatus", "productType", "resettingLeg", "productSubType",
	"tdsProductType", "secCodeSubType", "swapType", "description", "tradeDate",
	"startDate", "maturityDate", "uqlOcMmbMs", "uqlOcMmbMsPc", "msPc", "breakStatus", "term" })
public class Report {
	
	@JsonProperty("topOfHouse")
	private String topOfHouse;
	
	@JsonProperty("segment")
	private String segment;
	
	@JsonProperty("viceChair")
	private String viceChair;
	
	@JsonProperty("globalBusiness")
	private String globalBusiness;
	
	@JsonProperty("Policy")
	private String policy;
	
	@JsonProperty("desk")
	private String desk;
	
	@JsonProperty("portfolio")
	private String portfolio;
	
	@JsonProperty("BU")
	private long bu;
	
	@JsonProperty("CLINE")
	private String cline;
	
	@JsonProperty("Inventory")
	private String inventory;
	
	@JsonProperty("Book")
	private String book;
	
	@JsonProperty("System")
	private String system;
	
	@JsonProperty("LegalEntity")
	private String legalEntity;
	
	@JsonProperty("TradeId")
	private long tradeId;
	
	@JsonProperty("Version")
	private int version;
	
	@JsonProperty("TradeStatus")
	private String tradeStatus;
	
	@JsonProperty("ProductType")
	private String productType;
	
	@JsonProperty("Resetting Leg")
	private String resettingLeg;
	
	@JsonProperty("ProductSubType")
	private String productSubType;
	
	@JsonProperty("TDSProductType")
	private String tdsProductType;
	
	@JsonProperty("SecCodeSubType")
	private String secCodeSubType;
	
	@JsonProperty("SwapType")
	private String swapType;
	
	@JsonProperty("Description")
	private String description;
	
	@JsonProperty("TradeDate")
	private String tradeDate;
	
	@JsonProperty("StartDate")
	private String startDate;
	
	@JsonProperty("MaturityDate")
	private String maturityDate;
	
	@JsonProperty("UQL_OC_MMB_MS")
	private BigDecimal uqlOcMmbMs;
	
	@JsonProperty("UQL_OC_MMB_MS_PC")
	private BigDecimal uqlOcMmbMsPc;
	
	@JsonProperty("MS-PC")
	private BigDecimal msPc;
	
	@JsonProperty("BreakStatus")
	private String breakStatus;
	
	@JsonProperty("Term")
	private String term;

}
