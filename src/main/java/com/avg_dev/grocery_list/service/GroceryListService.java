package com.avg_dev.grocery_list.service;

import com.avg_dev.grocery_list.dtos.EntryDto;

public interface GroceryListService {

    Boolean addToList(EntryDto entry);
}
