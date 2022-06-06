package base.stack;

import static org.junit.jupiter.api.Assertions.*;

class GetMinStack2Test extends GetMinStackTest{

    @Override
    protected GetMinStack<Integer> instance() {
        return new GetMinStack2<>();
    }
}