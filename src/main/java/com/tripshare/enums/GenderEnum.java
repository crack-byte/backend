package com.tripshare.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderEnum {
	M("Male"), F("Female"), O("Other");
	private String value;
}
