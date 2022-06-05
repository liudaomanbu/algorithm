package base.bit;

import static org.junit.jupiter.api.Assertions.*;

class PowerOfFour1Test extends PowerOfFourTest{

    @Override
    protected PowerOfFour instance() {
        return new PowerOfFour1();
    }
}