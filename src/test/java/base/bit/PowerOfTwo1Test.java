package base.bit;

import static org.junit.jupiter.api.Assertions.*;

class PowerOfTwo1Test extends PowerOfTwoTest {

    @Override
    protected PowerOfTwo instance() {
        return new PowerOfTwo1();
    }
}