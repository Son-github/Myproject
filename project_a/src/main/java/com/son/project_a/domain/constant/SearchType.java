package com.son.project_a.domain.constant;

import lombok.Getter;

public enum SearchType {

    MNAME("밀키트이름");

    @Getter private final String description;

    SearchType(String description) {
        this.description = description;
    }


}
