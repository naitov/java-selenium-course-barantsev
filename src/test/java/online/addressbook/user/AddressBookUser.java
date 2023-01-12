package online.addressbook.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Random;

import static online.addressbook.test.AddressBookTest.USER_NAME;
import static online.addressbook.test.AddressBookTest.USER_PASS;

@Getter
@NoArgsConstructor
public class AddressBookUser {
    private final String name = USER_NAME;
    private final String password = USER_PASS;

    public String getRandomPassword() {
        Random random = new Random();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            s.append((char) random.nextInt(48, 90));
        }
        return s.toString();
    }
}
