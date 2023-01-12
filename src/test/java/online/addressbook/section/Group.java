package online.addressbook.section;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Group {
    private final String name;
    private final String header;
    private final String footer;
    @Setter
    private String parent;

    public Group(String name, String header, String footer) {
        this.name = name;
        this.header = header;
        this.footer = footer;
    }
}
