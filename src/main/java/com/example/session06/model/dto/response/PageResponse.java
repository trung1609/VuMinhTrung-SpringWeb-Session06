package com.example.session06.model.dto.response;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse <T>{
    private List<T> items;
    private Integer page;
    private Integer size;
    private Long totalItems;
    private Integer totalPages;
    private Boolean last;
}
