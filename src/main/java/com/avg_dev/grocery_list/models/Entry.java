package com.avg_dev.grocery_list.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Entry {

    private String listId;
    private String name;
    private String description;

}
