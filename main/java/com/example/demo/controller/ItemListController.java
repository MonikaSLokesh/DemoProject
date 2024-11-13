package com.example.demo.controller;

import com.example.demo.entity.ItemList;
import com.example.demo.service.ItemListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/itemlist")
public class ItemListController {

    @Autowired
    private ItemListService itemListService;

    // Landing page to enter ID and fetch item
    @GetMapping
    public String getItemByIdForm(Model model, @RequestParam(required = false) String message) {
        model.addAttribute("message", message); // For displaying cancel or error messages
        return "get_item_by_id"; // Thymeleaf template for landing page
    }

    // Method to fetch item by ID
    @PostMapping("/fetch")
    public String getItemById(@RequestParam Long id, Model model) {
        try {
            Thread.sleep(500000); // Introduce delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Optional<ItemList> item = itemListService.getItemById(id);
        if (item.isPresent()) {
            model.addAttribute("item", item.get());
            return "item_detail"; // Redirects to detail page if item is found
        } else {
            model.addAttribute("message", "Item not found with ID: " + id);
            return "get_item_by_id"; // Redirect back to landing page if item not found
        }
    }

    // Method to handle cancel action
    @GetMapping("/cancel")
    public String cancelAction(RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("message", "Action canceled.");
        return "redirect:/itemlist"; // Redirect back to landing page with cancel message
    }

    // Optional: Edit page if you need to edit an item by ID
    @GetMapping("/edit/{id}")
    public String editItemForm(@PathVariable Long id, Model model) {
        Optional<ItemList> item = itemListService.getItemById(id);
        if (item.isPresent()) {
            model.addAttribute("item", item.get());
            return "item_form"; // Redirects to item editing form
        } else {
            return "redirect:/itemlist?message=ItemNotFound"; // Return to landing page with error message
        }
    }

    // Optional: Save the item after editing
    @PostMapping("/edit")
    public String saveItem(@RequestParam Long id, @RequestParam String name, @RequestParam String description, @RequestParam Double price) {
        Optional<ItemList> item = itemListService.getItemById(id);
        if (item.isPresent()) {
            ItemList existingItem = item.get();
            existingItem.setName(name);
            existingItem.setDescription(description);
            existingItem.setPrice(price);
            itemListService.saveItem(existingItem);
            return "redirect:/itemlist"; // Redirect back to the landing page after saving changes
        }
        return "redirect:/itemlist?message=ItemNotFound"; // If item not found
    }
}
