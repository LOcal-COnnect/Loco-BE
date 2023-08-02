package com.likelion.loco.global.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RoleType {
    BUYER(0, "BUYER"),
    SELLER(1, "SELLER");

    private final Integer value;

    private final String roleType;

    @JsonValue
    public String getRoleType() {
        return this.roleType;
    }

}
