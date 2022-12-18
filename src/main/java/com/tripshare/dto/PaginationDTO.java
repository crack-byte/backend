package com.tripshare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationDTO<T> {
	private List<T> list;
	private int page;
	private int total;
}
