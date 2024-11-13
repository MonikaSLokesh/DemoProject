package com.example.demo.service;

import com.example.demo.entity.ItemList;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface ItemListService {

    // Method to fetch an item by ID
    Optional<ItemList> getItemById(Long id);

    // Asynchronous method to fetch an item by ID with timeout simulation
    CompletableFuture<ItemList> getItemByIdWithTimeout(Long id);

    // Method to save an item
    ItemList saveItem(ItemList item);

    // Method to add a new item
    ItemList addNewItem(ItemList newItem);

    // Method to fetch an item with timeout handling
    ItemList fetchItemWithTimeout(Long id) throws Exception;
}
