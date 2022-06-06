package base.stack;

import static org.junit.jupiter.api.Assertions.*;

class GetMinStack1Test extends GetMinStackTest{

    @Override
    protected GetMinStack<Integer> instance() {
        return new GetMinStack1<>();
    }
}