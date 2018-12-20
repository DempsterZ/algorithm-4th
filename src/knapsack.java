import java.util.Arrays;

public class knapsack {


    private static int count = 0;
    private static int[][] memo;


    public static void main(String[] args) {
        int[] w = {5,4,6,3};
        int[] v = {10,40,30,50};
        System.out.println(knapsack0(w, v, 10));
        System.out.println("count of bestValue() exec：" + count);
    }

    // 记忆化搜索
    public static int knapsack0(int[] w, int[] v, int C) {
        int n = w.length;
        memo = new int[n][C + 1];
        for(int i = 0;i<n;i++)
            Arrays.fill(memo[i],-1);

        return bestValue(w, v, n - 1, C);
    }
    private static int bestValue(int[] w, int[] v, int i, int C) {
        count++;
        if (i < 0 || C <= 0)
            return 0;

        if (memo[i][C] != -1) // 记忆化搜索
            return memo[i][C];

        int res = 0;
        res = bestValue(w, v, i - 1, C);
        if (C >= w[i])
            res = Math.max(res, v[i] + bestValue(w, v, i - 1, C - w[i]));

        return memo[i][C] = res;
    }

    // 动态规划，空间复杂度O(n * C)
    public static int knapsack01(int[] w, int[] v, int C) {
        int n = w.length;
        int[][] memo = new int[n][C + 1];

        if (n == 0 || C == 0)
            return 0;

        for (int j = 0; j <= C; j++)
            memo[0][j] = (j >= w[0] ? v[0] : 0);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= C; j++) {
                memo[i][j] = memo[i - 1][j];
                if (j >= w[i]) {
                    memo[i][j] = Math.max(memo[i][j], v[i] + memo[i - 1][j - w[i]]);
                }
            }
        }

        return memo[n - 1][C];
    }

    // 动态规划，空间复杂度O(n * 2)
    public static int knapsack02(int[] w, int[] v, int C){

        if(w == null || v == null || w.length != v.length)
            throw new IllegalArgumentException("Invalid w or v");

        if(C < 0)
            throw new IllegalArgumentException("C must be greater or equal to zero.");

        int n = w.length;
        if(n == 0 || C == 0)
            return 0;

        int[][] memo = new int[2][C + 1];

        for(int j = 0 ; j <= C ; j ++)
            memo[0][j] = (j >= w[0] ? v[0] : 0);

        for(int i = 1 ; i < n ; i ++)
            for(int j = 0 ; j <= C ; j ++){
                memo[i % 2][j] = memo[(i-1) % 2][j];
                if(j >= w[i])
                    memo[i % 2][j] = Math.max(memo[i % 2][j], v[i] + memo[(i-1) % 2][j - w[i]]);
            }

        return memo[(n-1) % 2][C];
    }

    // 动态规划，空间复杂度O(n)
    public static int knapsack03(int[] w, int[] v, int C){

        if(w == null || v == null || w.length != v.length)
            throw new IllegalArgumentException("Invalid w or v");

        if(C < 0)
            throw new IllegalArgumentException("C must be greater or equal to zero.");

        int n = w.length;
        if(n == 0 || C == 0)
            return 0;

        int[] memo = new int[C+1];

        for(int j = 0 ; j <= C ; j ++)
            memo[j] = (j >= w[0] ? v[0] : 0);

        for(int i = 1 ; i < n ; i ++)
            for(int j = C ; j >= w[i] ; j --)
                memo[j] = Math.max(memo[j], v[i] + memo[j - w[i]]);

        return memo[C];
    }
}