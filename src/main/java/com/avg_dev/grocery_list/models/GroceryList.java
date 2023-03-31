package com.avg_dev.grocery_list.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@Data
@Document("grocery_list")
public class GroceryList {

    @Id
    private String _id;
    private String name;
    private List<Entry> entries;
}
