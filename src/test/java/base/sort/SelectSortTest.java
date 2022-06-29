package base.sort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class SelectSortTest extends SortTest {

    @Override
    protected Sort instance() {
        return new SelectSort();
    }
}