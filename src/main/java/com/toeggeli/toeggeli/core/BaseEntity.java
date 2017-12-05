package com.toeggeli.toeggeli.core;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public abstract class BaseEntity {
    @Id
    private String id;


}
