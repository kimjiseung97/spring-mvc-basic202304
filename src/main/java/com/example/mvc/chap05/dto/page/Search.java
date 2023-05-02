package com.example.mvc.chap05.dto.page;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Search extends Page{

    //검색타입, 검색키워드
    private String type;
    private String keyword;

    public Search(){
        this.type = "";
        this.keyword = "";
    }

}
