package com.avg_dev.grocery_list.controller;

import com.avg_dev.grocery_list.dtos.EntryDto;
import com.avg_dev.grocery_list.service.GroceryListService;
import com.avg_dev.grocery_list.webutils.ResponseUtils;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/list")
@Validated
@RequiredArgsConstructor
public class ListController {

    private final GroceryListService groceryListService;

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addToList(@RequestBody @NotNull EntryDto dto) {
        boolean flag = groceryListService.addToList(dto);
        if (flag) {
            return ResponseUtils.getResponse(HttpStatus.CREATED, "Entry created", null);
        } else {
            return ResponseUtils.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Some error occurred", null);
        }
    }

}
