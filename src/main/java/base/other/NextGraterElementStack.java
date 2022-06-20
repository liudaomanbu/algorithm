package base.other;

import java.util.Stack;

/**
 * @author caotc
 * @date 2022-06-08
 * @since 1.0.0
 */
public class NextGraterElementStack implements NextGraterElement {
    @Override
    public int[] nextGraterElement(int[] nums) {
        int[] nextGraterElements = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int index = 1;
        while (index < nums.length) {
            if (stack.isEmpty() || nums[index] <= nums[stack.peek()]) {
                stack.push(index);
                index++;
            } else {
                nextGraterElements[stack.pop()] = index;
            }
        }
        return nextGraterElements;
    }
}
