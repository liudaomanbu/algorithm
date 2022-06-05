package base.sort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class QuickSortTest extends SortTest {

    @Override
    protected Sort instance() {
        return new QuickSort();
    }
}