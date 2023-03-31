package com.avg_dev.grocery_list.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EntryDto {

    @NotNull
    private String listId;
    @NotNull
    private String name;

    private String description;
}
