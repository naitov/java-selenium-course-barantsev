package online.addressbook.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public final class ContactData {
    private int id;
    private String firstName;
    private String lastName;
    private String group = "test33";
    private String homePhone;
    private String mobilePhone;
    private String workPhone;

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

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

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
               "id=" + id +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", homePhone='" + homePhone + '\'' +
               ", mobilePhone='" + mobilePhone + '\'' +
               ", workPhone='" + workPhone + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstName, that.firstName)
               && Objects.equals(lastName, that.lastName)
               && Objects.equals(homePhone, that.homePhone)
               && Objects.equals(mobilePhone, that.mobilePhone)
               && Objects.equals(workPhone, that.workPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, homePhone, mobilePhone, workPhone);
    }

    public ContactData withoutBannedSymbolsInPhones() {
        this.homePhone = homePhone.replaceAll("[-)(\\s]", "");
        this.mobilePhone = mobilePhone.replaceAll("[-)(\\s]", "");
        this.workPhone = workPhone.replaceAll("[-)(\\s]", "");
        return this;
    }

}