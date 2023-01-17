package online.addressbook.model;

import java.util.Objects;

public record ContactData(String address, String middleName, String lastName, String homePhone) {
    public ContactData {
        Objects.requireNonNull(address);
    }
}