package online.addressbook.model;

import java.util.Objects;

public record GroupData(String name, String header, String footer) {
    public GroupData {
        Objects.requireNonNull(name);
    }
}