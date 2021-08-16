package com.td.reporting.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "topOfHouse", "segment", "viceChair", "globalBusiness", "policy",
	"desk", "portfolio", "bu", "cline", "inventory"})
public class RefData {
	
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

}
