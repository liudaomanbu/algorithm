package base.sort;

class HeapSortTest extends SortTest{

    @Override
    protected Sort instance() {
        return new HeapSort();
    }
}