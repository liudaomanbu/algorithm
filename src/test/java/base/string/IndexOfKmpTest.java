package base.string;

import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class IndexOfKmpTest extends IndexOfTest{

    @Override
    protected IndexOf instance() {
        return new IndexOfKmp();
    }
}