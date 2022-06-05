package base.array;

import static org.junit.jupiter.api.Assertions.*;

public class LocalMinBinarySearchTest extends LocalMinTest{

    @Override
    protected LocalMin instance() {
        return new LocalMinBinarySearch();
    }
}