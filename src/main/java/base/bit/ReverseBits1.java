package base.bit;

/**
 * @author caotc
 * @date 2022-05-10
 * @since 1.0.0
 */
public class ReverseBits1 implements ReverseBits {
    @Override
    public int reverse(int num) {
        int result = 0;
        //左右16位交换
        result = num >>> 16 | num << 16;
        //每个16位内前后8位交换
        result = (result & 0b11111111000000001111111100000000) >>> 8 | (result & 0b00000000111111110000000011111111) << 8;
        //每个8位内前后4位交换
        result = (result & 0b11110000111100001111000011110000) >>> 4 | (result & 0b00001111000011110000111100001111) << 4;
        //每个4位内前后2位交换
        result = (result & 0b11001100110011001100110011001100) >>> 2 | (result & 0b00110011001100110011001100110011) << 2;
        //每个2位内前后交换
        result = (result & 0b10101010101010101010101010101010) >>> 1 | (result & 0b01010101010101010101010101010101) << 1;
        return result;
    }
}
