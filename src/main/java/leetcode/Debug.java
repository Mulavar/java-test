package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Debug {
    List<List<Integer>> result = new ArrayList<>();

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 6, 7};
        System.out.println(new Debug().combinationSum(nums, 7));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backTrack(candidates, 0, 0, target, new ArrayList<>());
        return result;
    }

    public void backTrack(int[] candidates, int idx, int sum, int target, List<Integer> current) {
        if (sum > target || idx >= candidates.length) {
            return;
        } else if (sum == target) {
            result.add(new ArrayList<>(current));
            return ;
        }

        current.add(candidates[idx]);
        sum += candidates[idx];
        backTrack(candidates, idx, sum, target, current);
        sum -= candidates[idx];
        current.remove(current.size() - 1);
        backTrack(candidates, idx + 1, sum, target, current);
    }
}
