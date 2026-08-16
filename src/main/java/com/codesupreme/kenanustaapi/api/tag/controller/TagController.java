package com.codesupreme.kenanustaapi.api.tag.controller;

import com.codesupreme.kenanustaapi.dto.tag.TagBulkResultDto;
import com.codesupreme.kenanustaapi.dto.tag.TagDto;
import com.codesupreme.kenanustaapi.service.impl.tag.TagServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v666/tag")
public class TagController {

    private final TagServiceImpl service;
    private final String writeKey;

    public TagController(TagServiceImpl service,
                         @Value("${tag.write-key:}") String writeKey) {
        this.service = service;
        this.writeKey = writeKey == null ? "" : writeKey.trim();
    }

    @GetMapping
    public ResponseEntity<List<TagDto>> getAllTag() {
        return ResponseEntity.ok(service.getAllTag());
    }

    @GetMapping("/{tagId}")
    public ResponseEntity<TagDto> getTagById(@PathVariable("tagId") Long id) {
        TagDto tag = service.getTagById(id);
        return tag != null ? ResponseEntity.ok(tag) : ResponseEntity.notFound().build();
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<TagDto> getTagBySlug(@PathVariable String slug) {
        TagDto tag = service.getTagBySlug(slug);
        return tag != null ? ResponseEntity.ok(tag) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<TagDto> createTag(
            @RequestHeader(value = "X-Tag-Write-Key", required = false) String suppliedKey,
            @Valid @RequestBody TagDto dto) {
        verifyWriteKey(suppliedKey);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTag(dto));
    }

    @PostMapping("/bulk")
    public ResponseEntity<TagBulkResultDto> createTagsBulk(
            @RequestHeader(value = "X-Tag-Write-Key", required = false) String suppliedKey,
            @RequestBody List<@Valid TagDto> dtos) {
        verifyWriteKey(suppliedKey);
        return ResponseEntity.ok(service.createTagsIfMissing(dtos));
    }

    @PutMapping("/{tagId}")
    public ResponseEntity<?> updateTag(
            @RequestHeader(value = "X-Tag-Write-Key", required = false) String suppliedKey,
            @PathVariable("tagId") Long id,
            @RequestBody TagDto tagDto) {
        verifyWriteKey(suppliedKey);
        TagDto updatedTag = service.updateTag(id, tagDto);
        return updatedTag != null ? ResponseEntity.ok(updatedTag) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{tagId}")
    public ResponseEntity<String> deleteTag(
            @RequestHeader(value = "X-Tag-Write-Key", required = false) String suppliedKey,
            @PathVariable("tagId") Long id) {
        verifyWriteKey(suppliedKey);
        return service.deleteTag(id)
                ? ResponseEntity.ok("Tag deleted successfully")
                : ResponseEntity.notFound().build();
    }

    private void verifyWriteKey(String suppliedKey) {
        // Backward-compatible: TAG_WRITE_KEY set edilməyibsə köhnə davranış qalır.
        // Production-da mütləq TAG_WRITE_KEY təyin etmək tövsiyə olunur.
        if (!writeKey.isBlank() && !writeKey.equals(suppliedKey)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid tag write key");
        }
    }
}
