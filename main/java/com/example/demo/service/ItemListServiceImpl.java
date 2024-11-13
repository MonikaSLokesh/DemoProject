package com.example.demo.service;

import com.example.demo.entity.ItemList;
import com.example.demo.repository.ItemListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class ItemListServiceImpl implements ItemListService {

    @Autowired
    private ItemListRepository itemListRepository;

    // Method to fetch Item by ID
    @Override
    public Optional<ItemList> getItemById(Long id) {
        return itemListRepository.findById(id);
    }

    // Asynchronous method to fetch Item by ID with timeout simulation
    @Override
    @Async
    public CompletableFuture<ItemList> getItemByIdWithTimeout(Long id) {
        try {
            // Simulate delay (e.g., database or service call)
            TimeUnit.SECONDS.sleep(100000); // Simulate a 10-second delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Optional<ItemList> item = itemListRepository.findById(id);
        return CompletableFuture.completedFuture(item.orElse(null));  // Return the item or null
    }

    // Method to save an Item after editing
    @Override
    public ItemList saveItem(ItemList item) {
        return itemListRepository.save(item);  // Save the updated item to the database
    }

    // Method to add a new Item
    @Override
    public ItemList addNewItem(ItemList newItem) {
        return itemListRepository.save(newItem); // Save the new item
    }

    // Method to simulate a fetch with timeout
    @Override
    public ItemList fetchItemWithTimeout(Long id) throws Exception {
        Optional<ItemList> item = itemListRepository.findById(id);
        if (item.isPresent()) {
            return item.get();
        } else {
            throw new Exception("Item not found");
        }
    }
}
