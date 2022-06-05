package base.array;

import static org.junit.jupiter.api.Assertions.*;

public class CeilingBinarySearchTest extends CeilingTest{

    @Override
    protected Ceiling instance() {
        return new CeilingBinarySearch();
    }
}