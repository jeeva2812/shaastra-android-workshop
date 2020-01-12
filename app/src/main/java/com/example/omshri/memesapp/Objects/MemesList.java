package com.example.omshri.memesapp.Objects;

import java.util.List;

public class MemesList {
    Boolean success;
    DataClass data;
    public List<MemesClass> getMemes(){
        return data.memes;
    }
    public String getId(Integer i){
            return data.memes.get(i).getId();
    }
    public String getName(Integer i){
        return data.memes.get(i).getName();
    }
    public String getURL(Integer i){
        return data.memes.get(i).getUrl();
    }
    public Integer getHeight(Integer i){
        return data.memes.get(i).getHeight();
    }
    public Integer getWidth(Integer i){
        return data.memes.get(i).getWidth();
    }
}
