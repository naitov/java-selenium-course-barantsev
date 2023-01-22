package online.addressbook.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public final class ContactData {
    private String firstName;
    private String lastName;
    private String group;

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
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