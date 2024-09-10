package com.hexagonal.domain;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private Role userRole;
    private UUID userId;
    private String userName;

}
