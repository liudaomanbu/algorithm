package base.sort;

import static org.junit.jupiter.api.Assertions.*;

class ReversePairCountMergeSortTest extends ReversePairCountTest{

    @Override
    protected ReversePairCount instance() {
        return new ReversePairCountMergeSort();
    }
}