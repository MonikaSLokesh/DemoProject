package com.example.demo.repository;

import com.example.demo.entity.ItemList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemListRepository extends JpaRepository<ItemList, Long> {

    List<ItemList> findByNameContaining(String name);
}
