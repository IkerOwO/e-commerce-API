package com.iker.ecommerce_api.dtos.Category;

import com.iker.ecommerce_api.validation.IsRequired;
import jakarta.validation.constraints.NotBlank;

public class RegisterCategoryRequest {
    @NotBlank
    @IsRequired
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
