package com.example.rcc_system.domian.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import javax.persistence.Id;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequestDto {
    @Id
    private String searchType;
    private String searchText;
    private int page;

    @Override
    public String toString() {
        return "SearchRequestDto{" +
                "searchType='" + searchType + '\'' +
                ", searchText='" + searchText + '\'' +
                ", page=" + page +
                '}';
    }
}
