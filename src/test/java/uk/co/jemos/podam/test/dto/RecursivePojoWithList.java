package uk.co.jemos.podam.test.dto;

import java.util.List;

public class RecursivePojoWithList {

    private String name;

    private RecursivePojoWithList related;

    private List<RecursivePojoWithList> children;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RecursivePojoWithList getRelated() {
        return this.related;
    }

    public void setRelated(RecursivePojoWithList related) {
        this.related = related;
    }

    public List<RecursivePojoWithList> getChildren() {
        return this.children;
    }

    public void setChildren(List<RecursivePojoWithList> children) {
        this.children = children;
    }
}
