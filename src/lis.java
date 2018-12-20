import java.util.Arrays;import java.util.Arrays;

/***
 * 最长上升子序列
 */
public class lis {

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(nums));

    }

    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] memo = new int[nums.length];
        Arrays.fill(memo, 1);

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    memo[i] = Math.max(memo[i], memo[j] + 1);
                }
            }
        }

        int res = 0;
        for (int i = 0; i < memo.length; i++) {
            res = Math.max(res, memo[i]);
        }

        return res;
    }


}