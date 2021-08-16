package com.td.reporting.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "inventory", "book", "system", "legalEntity", "tradeId",
	"version", "tradeStatus", "productType", "resettingLeg", "productSubType",
	"tdsProductType", "secCodeSubType", "swapType", "description", "tradeDate",
	"startDate", "maturityDate" })
public class Trade {
	
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

}
