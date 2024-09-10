package com.hexagonal.domain;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Role {

    private UUID roleId;
    private String roleName;
    
}
