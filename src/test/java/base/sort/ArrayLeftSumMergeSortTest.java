package base.sort;

import static org.junit.jupiter.api.Assertions.*;

class ArrayLeftSumMergeSortTest extends ArrayLeftSumTest{

    @Override
    protected ArrayLeftSum instance() {
        return new ArrayLeftSumMergeSort();
    }
}