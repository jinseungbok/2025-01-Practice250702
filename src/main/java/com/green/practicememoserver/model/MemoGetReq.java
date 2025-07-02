package com.green.practicememoserver.model;

import lombok.Getter;
import lombok.ToString;

import java.beans.ConstructorProperties;

// 주소?search_text=블라블라&page=10 >> 매칭 searchText로 맞춰주는 용도로 사용
// @BindParam("search_text") String searchText
// @BindParam("받는 키 값"),
@Getter
@ToString
public class MemoGetReq {
    private String searchText;
    private Integer page;

//    public MemoGetReq(@BindParam("search_text") String searchText, Integer page) {
//        this.searchText = searchText;
//        this.page = page;
//    }

    @ConstructorProperties({"search_text", "page"})
    // 이렇게 처리하는 게 더 효율적이나, page까지 적어야 되는 단점이 있음
    public MemoGetReq(String searchText, Integer page) {
        this.searchText = searchText;
        this.page = page;
    }
}
