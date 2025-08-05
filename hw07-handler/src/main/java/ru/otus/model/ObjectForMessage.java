package ru.otus.model;

import java.util.List;

public class ObjectForMessage {

    private List<String> data;

    public ObjectForMessage() {
    }

    public ObjectForMessage(ObjectForMessage other) {
        if (other != null && other.data != null) {
            this.data = List.copyOf(other.data);
        }
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

      @Override
    public String toString() {
        return "ObjectForMessage{" +
                "data=" + data +
                '}';
    }
}
