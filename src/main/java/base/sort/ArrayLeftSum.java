package base.sort;

/**
 * @author caotc
 * @date 2022-05-16
 * @since 1.0.0
 */
public interface ArrayLeftSum {
    /**
     * 在一个数组中，一个数左边比它小的数的总和，叫该数的小和
     * 所有数的小和累加起来，叫数组小和
     * 例子： [1,3,4,2,5]
     * 1左边比1小的数：没有
     * 3左边比3小的数：1
     * 4左边比4小的数：1、3
     * 2左边比2小的数：1
     * 5左边比5小的数：1、3、4、 2
     * 所以数组的小和为1+1+3+1+1+3+4+2=16
     * 给定一个数组arr，求数组小和
     *
     * @param array 数组
     * @return 数组左小和
     */
    int arrayLeftSum(int[] array);
}
