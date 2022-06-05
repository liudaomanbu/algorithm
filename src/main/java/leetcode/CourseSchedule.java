package leetcode;

/**
 * https://leetcode.cn/problems/course-schedule/
 *
 * @author caotc
 * @date 2022-05-11
 * @since 1.0.0
 */
public interface CourseSchedule {
    /**
     * 你这个学期必须选修 numCourses 门课程，记为0到numCourses - 1 。
     * <p>
     * 在选修某些课程之前需要一些先修课程。 先修课程按数组prerequisites 给出，其中prerequisites[i] = [ai, bi] ，表示如果要学习课程ai 则 必须 先学习课程 bi 。
     * <p>
     * 例如，先修课程对[0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
     * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
     *
     * @param numCourses 课程数
     * @param prerequisites 课程依赖关系数组
     * @return 能否完成
     */
    boolean canFinish(int numCourses, int[][] prerequisites);
}
