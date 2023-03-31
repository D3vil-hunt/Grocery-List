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


        AtomicReference<GroceryList> groceryListAtomic = new AtomicReference<>();
        if (entry.getListId() != null) {
            groceryListAtomic.set(mongoTemplate.findById(entry.getListId(), GroceryList.class));
        }
        Entry e = mapper.convertValue(entry, Entry.class);
        Optional.ofNullable(groceryListAtomic.get()).ifPresentOrElse(
                list -> Optional.ofNullable(list.getEntries()).ifPresentOrElse(entries -> entries.add(e), () -> {
                    groceryListAtomic.get().setEntries(new ArrayList<>());
                    groceryListAtomic.get().getEntries().add(e);
                }),
                () -> {
                    String generatedName = RandomStringUtils.randomAlphabetic(10);
                    groceryListAtomic.set(GroceryList.builder()
                            .entries(new ArrayList<>())
                            .name(generatedName)
                            .build());
                    groceryListAtomic.get().setEntries(new ArrayList<>());
                    GroceryList newList = mongoTemplate.save(groceryListAtomic.get());
                    e.setListId(newList.get_id());
                    newList.getEntries().add(e);
                    groceryListAtomic.set(newList);
                }
        );
        mongoTemplate.save(groceryListAtomic.get());
        return true;
    }
}
