/*
Approach : Stack + StringBuilder Reversal
TC : (N ^ 2)
SC : (N)
*/

class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        int[] pair = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        // Pass 1: Build the teleportation map (wormholes)
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                int j = stack.pop();
                pair[i] = j;
                pair[j] = i;
            }
        }
        
        // Pass 2: Traverse the portals and construct the string
        StringBuilder sb = new StringBuilder();
        int direction = 1; // 1 means moving forward, -1 means moving backward
        
        for (int i = 0; i < n; i += direction) {
            char c = s.charAt(i);
            if (c == '(' || c == ')') {
                i = pair[i];        // Teleport to the matching parenthesis
                direction = -direction; // Invert movement direction
            } else {
                sb.append(c);       // Append regular characters
            }
        }
        
        return sb.toString();
    }
}