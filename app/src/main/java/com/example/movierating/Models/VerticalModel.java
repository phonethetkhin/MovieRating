package com.example.movierating.Models;

import java.util.List;

public class VerticalModel {
    private String title;
    List<HorizontalModel> horizontalModelList;

    public VerticalModel(String title, List<HorizontalModel> horizontalModelList) {
        this.title = title;
        this.horizontalModelList = horizontalModelList;
    }

    public String getTitle() {
        return title;
    }

    public List<HorizontalModel> getHorizontalModelList() {
        return horizontalModelList;
    }
}
