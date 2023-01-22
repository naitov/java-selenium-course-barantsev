package online.addressbook.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public final class ContactData {
    private final String firstName;
    private final String lastName;
    private final String group;

    public ContactData(String firstName, String lastName, String group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = group;
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return "ContactData[" +
               "firstName=" + firstName + ", " +
               "lastName=" + lastName + ", " +
               "group=" + group + ']';
    }

}