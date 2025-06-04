package com.bmcho.catalogservice.feign;

import com.bmcho.catalogservice.dto.ProductTagsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "searchClient", url = "http://search-service:8080")
public interface SearchClient {
    @PostMapping(value = "/search/addTagCache")
    void addTagCache(@RequestBody ProductTagsDto dto);

    @PostMapping(value = "/search/removeTagCache")
    void removeTagCache(@RequestBody ProductTagsDto dto);
}
