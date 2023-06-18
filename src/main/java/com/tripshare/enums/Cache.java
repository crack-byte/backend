package com.tripshare.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Cache {
    USER("user");
    private final String value;
}
