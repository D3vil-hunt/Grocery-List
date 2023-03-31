package com.avg_dev.grocery_list.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Entry {

    @Id
    private String id = null;
    private String listId;
    private String name;
    private String description;

}
