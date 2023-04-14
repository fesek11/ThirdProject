package ua.fesenko.ThirdProject.dto;

import java.util.List;

public class ListWrapper<T> {
    private List<T> items;

    public ListWrapper(List<T> items) {
        this.items = items;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}