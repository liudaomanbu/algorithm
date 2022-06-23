package base.other;

import java.util.LinkedList;
import java.util.List;

/**
 * @author caotc
 * @date 2022-06-06
 * @since 1.0.0
 */
public class AllOrderDfs implements AllOrder {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new LinkedList<>();
        dfs(n, k, new LinkedList<>(), result);
        return result;
    }

    private void dfs(int n, int k, List<Integer> prefix, List<List<Integer>> visited) {
        if (prefix.size() == k) {
            visited.add(prefix);
            return;
        }
        int start = prefix.size() == 0 ? 0 : prefix.get(prefix.size() - 1);
        if (start > n) {
            return;
        }
        for (int i = start; i < n; i++) {
            List<Integer> newPrefix = new LinkedList<>(prefix);
            newPrefix.add(i + 1);
            dfs(n, k, newPrefix, visited);
        }
    }
}
