package online.addressbook.model;

import lombok.Getter;

import java.io.File;
import java.util.Objects;

@Getter
public final class ContactData {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String group = "test33";
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String email;
    private String secondEmail;
    private String thirdEmail;
    private File photo;

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

    public ContactData withAddress(String address) {
        this.address = address;
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

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withSecondEmail(String email) {
        secondEmail = email;
        return this;
    }

    public ContactData withThirdEmail(String email) {
        thirdEmail = email;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstName, that.firstName)
               && Objects.equals(lastName, that.lastName)
               && Objects.equals(address, that.address)
               && Objects.equals(homePhone, that.homePhone)
               && Objects.equals(mobilePhone, that.mobilePhone)
               && Objects.equals(workPhone, that.workPhone)
               && Objects.equals(email, that.email)
               && Objects.equals(secondEmail, that.secondEmail)
               && Objects.equals(thirdEmail, that.thirdEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, homePhone,
                mobilePhone, workPhone, email, secondEmail, thirdEmail);
    }

    @Override
    public String toString() {
        return "ContactData{" +
               "id=" + id +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", address='" + address + '\'' +
               ", homePhone='" + homePhone + '\'' +
               ", mobilePhone='" + mobilePhone + '\'' +
               ", workPhone='" + workPhone + '\'' +
               ", email='" + email + '\'' +
               ", secEmail='" + secondEmail + '\'' +
               ", thirdEmail='" + thirdEmail + '\'' +
               '}';
    }

    public ContactData withoutBannedSymbolsInPhones() {
        String regEx = "[-)(\\s]";
        this.homePhone = homePhone.replaceAll(regEx, "");
        this.mobilePhone = mobilePhone.replaceAll(regEx, "");
        this.workPhone = workPhone.replaceAll(regEx, "");
        return this;
    }
}