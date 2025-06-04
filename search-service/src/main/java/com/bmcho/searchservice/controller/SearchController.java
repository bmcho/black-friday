package com.bmcho.searchservice.controller;

import com.bmcho.searchservice.dto.ProductTagsDto;
import com.bmcho.searchservice.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private  final SearchService searchService;

    @PostMapping("/search/addTagCache")
    public void addTagCache(@RequestBody ProductTagsDto dto) {
        searchService.addTagCache(dto.productId, dto.tags);
    }

    @PostMapping("/search/removeTagCache")
    public void removeTagCache(@RequestBody ProductTagsDto dto) {
        searchService.removeTagCache(dto.productId, dto.tags);
    }

    @GetMapping("/search/tags/{tag}/productIds")
    public List<Long> getTagProductIds(@PathVariable String tag) {
        return searchService.getProductIdsByTag(tag);
    }
}
