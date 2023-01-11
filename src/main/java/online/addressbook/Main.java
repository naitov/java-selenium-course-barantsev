package online.addressbook;

import static online.addressbook.Point.getNewPoint;

public class Main {

    public static void main(String[] args) {
        System.out.println("Line length: " + getNewPoint(2,5).getLengthTo(getNewPoint(3, 6)));
    }
}
