package com.springmongo.spring.rest;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ItemsController {
	
	@Autowired
	ItemsRepository itemsRepository;

	
	
	@GetMapping("/")
    public List<Item> getAll() {
		Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
		return itemsRepository.findAll(sortByCreatedAtDesc);
    }
	

	@GetMapping(value="/{id}")
	public ResponseEntity<Item> getItem(@PathVariable("id") String id) {
		
		 return itemsRepository.findById(id)
				 .map(item -> ResponseEntity.ok().body(item))
				 .orElse(ResponseEntity.notFound().build());
	}	

	
	@PostMapping(value = "/")
	public Item newItem(@RequestBody Item item) {
		item.setChecked(false);
		return itemsRepository.save(item);
		
	}

	
	@PutMapping(value="/{id}")
	public ResponseEntity<Item> updateItem(@PathVariable("id") String id, @RequestBody Item item) {
		
		return itemsRepository.findById(id)
				.map(itemData -> { 
					itemData.setDescription(item.getDescription());
                    itemData.setChecked(item.isChecked());
                    Item updatedItem = itemsRepository.save(itemData);
                    return ResponseEntity.ok().body(updatedItem);
				})
				 .orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<?> deleteItem(@PathVariable("id") String id) {
		return itemsRepository.findById(id)
                .map(todo -> {
                    itemsRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
		
	}
}
