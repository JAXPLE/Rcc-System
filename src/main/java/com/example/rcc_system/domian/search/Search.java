package com.example.rcc_system.domian.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name="search")
public class Search {
    @Id
    private String searchType;
    private String searchText;
    private int page;

    public Search(SearchRequestDto searchRequestDto){
        this.searchType = searchRequestDto.getSearchType();
        this.searchText = searchRequestDto.getSearchText();
        this.page = searchRequestDto.getPage();
    }
}
