class Solution {
    public int minCost(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        boolean[][] dp = new boolean[m][1024];
        dp[0][grid[0][0]] = true;

        for (int i = 0; i < n; i++) {
            boolean[][] next = new boolean[m][1024];

            for (int j = 0; j < m; j++) {
                for (int k = 0; k < 1024; k++) {
                    if (!dp[j][k]) continue;

                    if (j + 1 < m) {
                        dp[j + 1][k ^ grid[i][j + 1]] = true;
                    }

                    if (i + 1 < n) {
                        next[j][k ^ grid[i + 1][j]] = true;
                    }
                }
            }

            if (i + 1 < n) {
                dp = next;
            }
        }

        for (int k = 0; k < 1024; k++) {
            if (dp[m - 1][k]) return k;
        }

        return -1;
    }
}