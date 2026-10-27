class Solution {
    public int countIslands(int[][] grid, int k) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int divisibleIslandsCount = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] > 0) {
                    int islandRemainder = 0;
                    Queue<int[]> queue = new LinkedList<>();
                    
                    queue.offer(new int[]{r, c});
                    islandRemainder = (islandRemainder + grid[r][c]) % k;
                    grid[r][c] = -1; // Mark as visited immediately when pushing

                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        int currR = curr[0];
                        int currC = curr[1];

                        for (int[] dir : directions) {
                            int nr = currR + dir[0];
                            int nc = currC + dir[1];

                            if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] > 0) {
                                islandRemainder = (islandRemainder + grid[nr][nc]) % k;
                                grid[nr][nc] = -1; // Mark as visited
                                queue.offer(new int[]{nr, nc});
                            }
                        }
                    }

                    if (islandRemainder == 0) {
                        divisibleIslandsCount++;
                    }
                }
            }
        }
        return divisibleIslandsCount;
    }
}