package online.addressbook.model;

import java.util.Objects;

public record ContactData(String firstName, String lastName, String group) {
    public ContactData {
        Objects.requireNonNull(firstName);
    }
}