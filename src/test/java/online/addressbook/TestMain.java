package online.addressbook;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestMain {

    @Test(description = "Actual and expected lengths should be equal")
    public void actualAndExpectedDistancesShouldBeEqual() {
        Point p1 = Point.getNewPoint(2, 0);
        Point p2 = Point.getNewPoint(4, 0);
        assertThat("Actual and expected lengths should be equal", p1.getLengthTo(p2), is(2.0d));
    }

}
