package com.example.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> implements Serializable {
    private List<T> content;
    private long totalElements;
    private int totalPages;
    private int size;
    private int number;
    private boolean first;
    private boolean last;

    public static <T> PageResponse<T> fromPage(Page<?> page, List<T> content) {
        PageResponse<T> r = new PageResponse<>();
        r.setContent(content);
        r.setTotalElements(page.getTotalElements());
        r.setTotalPages(page.getTotalPages());
        r.setSize(page.getSize());
        r.setNumber(page.getNumber());
        r.setFirst(page.isFirst());
        r.setLast(page.isLast());
        return r;
    }
}
