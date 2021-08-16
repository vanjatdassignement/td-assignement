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
@JsonPropertyOrder({ "tradeId", "uqlOcMmbMS", "uqlOcMmbMsPc"})
public class Valuation {
	
	@JsonProperty("TradeId")
	private long tradeId;
	
	@JsonProperty("UQL_OC_MMB_MS")
	private BigDecimal uqlOcMmbMs;
	
	@JsonProperty("UQL_OC_MMB_MS_PC")
	private BigDecimal uqlOcMmbMsPc;

}
