package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author caotc
 * @date 2022-05-11
 * @since 1.0.0
 */
public class CourseScheduleTopologySortBfs implements CourseSchedule {
    @Override
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //每个课程当前依赖的课程数
        int[] courseIns = new int[numCourses];
        //每个课程可以解锁的对应课程
        Map<Integer, List<Integer>> courseToNextCourses = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            List<Integer> nextCourses = courseToNextCourses.computeIfAbsent(prerequisite[1], k -> new ArrayList<>());
            nextCourses.add(prerequisite[0]);
            courseIns[prerequisite[0]]++;
        }

        Queue<Integer> zeroInCourses = new LinkedList<>();
        for (int course = 0; course < courseIns.length; course++) {
            if (courseIns[course] == 0) {
                zeroInCourses.offer(course);
            }
        }

        while (!zeroInCourses.isEmpty()) {
            int course = zeroInCourses.poll();

            List<Integer> nextCourses = courseToNextCourses.getOrDefault(course, new ArrayList<>());
            for (int nextCourse : nextCourses) {
                courseIns[nextCourse]--;
                if (courseIns[nextCourse] == 0) {
                    zeroInCourses.offer(nextCourse);
                }
            }
        }
        for (int courseIn : courseIns) {
            if (courseIn != 0) {
                return false;
            }
        }
        return true;
    }
}
