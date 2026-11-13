class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        // return sol1(nums, k);

        return sol2(nums, k);
    }

    public long sol2(int[] nums, int k) {
        int n = nums.length;

        int i = 0;
        int j = 0;
        long total = 0;
        int prev = nums[0];
        int op = k;
        while(j < n) {
            if(nums[j] >= prev) {
                prev = nums[j];
                j += 1;
            }
            else if(prev - nums[j] <= op){
                op = op - (prev - nums[j]);
                j += 1;
            }
            else {
                total += (j - i);
                op = k;
                i = i + 1;
                int ind = i;
                prev = nums[ind];
                while(ind < j) {
                    if(nums[ind] >= prev) {
                        prev = nums[ind];
                    }
                    op = op - (prev - nums[ind]);
                    ind += 1;
                }
            }
        }

        // finally
        total += (long) (j-i)*(j-i+1) / 2;
        return total;
    }

    public long sol1(int[] nums, int k)  {
        int n = nums.length;

        long total = 0;
        for(int i=0; i<n ;i++) {
            long currLen = 1;
            int prev = nums[i];
            int allowedOperations = k;
            for(int j=i; j<n; j++) {
                if(nums[j] >= prev) {
                    prev = nums[j];
                    currLen = (j-i+1);
                }
                else if(prev - nums[j] > allowedOperations) {
                    break;
                }
                else {
                    allowedOperations = allowedOperations - (prev - nums[j]);
                    currLen = (j-i+1);
                }
            }

            total += currLen;
        }

        return total;
    }
}