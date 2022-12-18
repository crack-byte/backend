package com.tripshare.dto;

import lombok.Data;

@Data
public class FilterDTO {

	private int limit = 10;
	private int page = 0;
	private String filter;
	private String sortBy;
	private String sortOrder;

}
