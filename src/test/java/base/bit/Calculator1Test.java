package base.bit;

import static org.junit.jupiter.api.Assertions.*;

class Calculator1Test extends CalculatorTest{

    @Override
    protected Calculator instance() {
        return new Calculator1();
    }
}