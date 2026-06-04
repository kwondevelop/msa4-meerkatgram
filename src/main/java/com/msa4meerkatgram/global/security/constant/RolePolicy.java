package com.msa4meerkatgram.global.security.constant;

import lombok.Getter;

@Getter
public enum RolePolicy {
    NORMAL("Normal"),
    SUPER("Super");
    
    private final String role;
    
    RolePolicy(String role) {
        this.role = role;
    }
}
