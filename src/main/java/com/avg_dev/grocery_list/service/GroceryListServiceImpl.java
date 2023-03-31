package com.avg_dev.grocery_list.service;

import com.avg_dev.grocery_list.dtos.EntryDto;
import com.avg_dev.grocery_list.models.Entry;
import com.avg_dev.grocery_list.models.GroceryList;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class GroceryListServiceImpl implements GroceryListService {

    private final MongoTemplate mongoTemplate;
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Boolean addToList(EntryDto entry) {

        AtomicReference<GroceryList> groceryList = new AtomicReference<>(mongoTemplate.findById(entry.getListId(), GroceryList.class));
        Entry e = mapper.convertValue(entry, Entry.class);
        Optional.ofNullable(groceryList.get()).ifPresentOrElse(
                list -> Optional.ofNullable(groceryList.get().getEntries()).ifPresentOrElse(entries -> entries.add(e), () -> {
                    groceryList.get().setEntries(new ArrayList<>());
                    groceryList.get().getEntries().add(e);
                }),
                () -> {
                    String generatedName = RandomStringUtils.randomAlphabetic(10);
                    groceryList.set(GroceryList.builder()
                            .entries(new ArrayList<>())
                            .name(generatedName)
                            .build());
                    groceryList.get().setEntries(new ArrayList<>());
                    groceryList.get().getEntries().add(e);
                }
        );
        mongoTemplate.save(groceryList.get());
        return true;
    }
}
