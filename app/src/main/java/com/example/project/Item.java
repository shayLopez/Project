package com.example.project;

import java.io.Serializable;

public class Item implements Serializable {
    private String name,info,uri;
    public Item(String name,String info,String uri)
    {
        this.name=name;
        this.info=info;
        this.uri=uri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
