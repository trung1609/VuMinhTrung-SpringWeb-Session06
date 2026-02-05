package com.example.session06.model.dto.request;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {
    private Integer page;
    private Integer size;
    private String sortBy;
    private String direction;
}
