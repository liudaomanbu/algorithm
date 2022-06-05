package base.bit;

import static org.junit.jupiter.api.Assertions.*;

class Max1Test extends MaxTest{

    @Override
    protected Max instance() {
        return new Max1();
    }
}