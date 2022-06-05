package base.sort;

import static org.junit.jupiter.api.Assertions.*;

class InsertionSortTest extends SortTest{

    @Override
    protected Sort instance() {
        return new InsertionSort();
    }
}