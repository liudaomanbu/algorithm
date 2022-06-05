package base.bit;

import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class ReverseBits1Test extends ReverseBitsTest{

    @Override
    protected ReverseBits reverseBits() {
        return new ReverseBits1();
    }
}