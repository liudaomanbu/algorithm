package leetcode.offer;

import java.util.HashSet;
import java.util.Set;

/**
 * @author caotc
 * @date 2021-04-20
 * @since 1.0.0
 */
public class FindRepeatNumber3 {
    public static void main(String[] args) {
        int[] nums=new int[]{2, 3, 1, 0, 2, 5, 3};
        System.out.println(findRepeatNumber3(nums));
    }

    public static int findRepeatNumber3(int[] nums) {
        Bitmap exist=new Bitmap(nums.length);
        for(int num:nums){
            if(exist.get(num)){
                return num;
            }else{
                exist.add(num);
            }
        }
        throw new IllegalArgumentException();
    }

    public static class Bitmap {
        private byte bitmap[];
        private int length;

        public boolean get(int number){
            //获取位置
            int site=number>>>3;//等价于 site=number/8
            //获取该字节
            byte temp=bitmap[site];

            //获取该字节的第几个
            int i=number&7;//等价于 i=number%8

            //将这个字节数右移(7-i)位（也就是把要查找的位移动到最右侧），然后与 0000 0001相与
            if(((temp>>>(7-i))&1)==0){
                return false;
            }
            return true;
        }
        private void set(int number, boolean bool){
            //获取位置
            int site=number>>>3;//等价于 site=number/8
            //获取该字节
            byte temp=bitmap[site];

            //获取该字节的第几个
            int i=number&7;//等价于 i=number%8
            //将0000 0001 左移(7-i)
            byte comp= (byte) (1<<(7-i));

            if(bool){//设置为1
                bitmap[site]= (byte) (comp|temp);//取或(0.. 1 0..)
            }else {//设置为0
                comp= (byte) ~comp;//取反
                bitmap[site]= (byte) (comp&temp);//相与(1.. 0 1..)
            }
        }
        public void add(int index){
            set(index,true);
        }
        public void delete(int index){
            set(index,false);
        }

        public Bitmap (int length){
            this.length=length;
            bitmap=new byte[(length>>>3)+1];
        }

        public int getLength() {
            return length;
        }

    }

    public static int findRepeatNumber2(int[] nums) {
        int i=0;
        while (i<nums.length){
            if(nums[i]==i){
                i++;
                continue;
            }
            if(nums[nums[i]]==nums[i]){
                return nums[i];
            }
            int temp=nums[i];
            nums[i]=nums[temp];
            nums[temp]=temp;
        }
        throw new IllegalArgumentException();
    }

    public static int findRepeatNumber1(int[] nums) {
        Set<Integer> exist=new HashSet<>();
        for(int num:nums){
            if(exist.contains(num)){
                return num;
            }else{
                exist.add(num);
            }
        }
        throw new IllegalArgumentException();
    }
}
