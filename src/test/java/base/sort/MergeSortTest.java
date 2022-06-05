package base.sort;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest extends SortTest{

    @Override
    protected Sort instance() {
        return new MergeSort();
    }
}