package com.codesupreme.kenanustaapi.api.tag.controller;


import com.codesupreme.kenanustaapi.dto.tag.TagDto;
import com.codesupreme.kenanustaapi.service.impl.tag.TagServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v666/tag")
public class TagController {

    private final TagServiceImpl service;
    public TagController(TagServiceImpl service) {this.service = service;}

    @GetMapping
    public ResponseEntity<List<TagDto>> getAllTag() {
        List<TagDto> all = service.getAllTag();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{tagId}")
    public ResponseEntity<TagDto> getTagById(@PathVariable("tagId") Long id) {
        TagDto det = service.getTagById(id);
        if (det != null) {
            return ResponseEntity.ok(det);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<TagDto> createTag(@RequestBody TagDto dto) {
        TagDto created = service.createTag(dto);
        return ResponseEntity.ok(created);
    }

    // Update
    @PutMapping("/{tagId}")
    public ResponseEntity<?> updateTag(
            @PathVariable("tagId") Long id,
            @RequestBody TagDto tagDto) {
        TagDto updatedTag = service.updateTag(id, tagDto);
        if (updatedTag != null) {
            return ResponseEntity.ok(updatedTag);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{tagId}")
    public ResponseEntity<String> deleteTag(@PathVariable("tagId") Long id) {
        Boolean deleted = service.deleteTag(id);
        if (deleted) {
            return ResponseEntity.ok("Tag deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
