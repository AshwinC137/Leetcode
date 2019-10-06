/*Dynamic programming based soultion for https://leetcode.com/problems/get-equal-substrings-within-budget/
a sliding window based solution IS MUCH MUCH BETTER but this solution is a good practice on how to
implement dynamic programming based solutions*/
class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int[][] cost = new int[s.length()][s.length()];
        int totalCost = 0;
        for(int i = 0; i < s.length(); i++){
            int temp = getCost(s.charAt(i),t.charAt(i));
            cost[i][i] = temp;
            totalCost = totalCost + temp;
        }
        
        if(totalCost < maxCost)
            return s.length();
        else
            cost[s.length() - 1][0] = totalCost;
        
        for(int i = s.length() - 2; i >= 0; i--){
            int k = i;
            for(int j = 0; j < s.length() - i; j++){    //start from largest substring this helps because once you find a string which satisifes the criteria you can simply return
                if(k+1 >= s.length())                   //whereas if you start from smallest substring (ie single chars) you will still have to search for larger strings which satisfy the constraints
                    cost[k][j] = cost[k][j-1] - cost[j-1][j-1];
                else
                    cost[k][j] = cost[k+1][j] - cost[k+1][k+1];
                if(cost[k][j] <= maxCost)
                    return i+1;
                k++;
            }
        }
        return 0;
        
        // eg consider 5x5 cost table, we start by filling [0,0],[1,1]...[4,4] (ie smallest substrings) and also [4,0] (ie largest substring)
        // after this we fill the table int this manner 
        // iteration1 - fill cost of [3,0] and [4,1] (ie all substrings of length 4)
        // iteration2 - fill cost of [2,0], [3,1] and [4,2] (ie all substrings of length 3) and so on..
        // the if statement is needed when filling the last column ie column with x co ordinate = 4 in this case
        // the last column has no column to its right hence we need to get its cost from the cost of the substring above it in the same column
        // this will give you an idea->    0      1      2     3        4
        //                             0   a      ab    abc   abcd    abcde
        //                             1          b     bc    bcd     bcde
        //                             2                c     cd      cde
        //                             3                      d       de
        //                             4                              e
        // a  means diff between 1st char in s and t , b means diff between 2nd char in s and t so on..
        // ab means sum of diff between 1st and 2nd chars in s and t, abc means sum of diff between 1st,2nd and 3rd chars in s and t and so on..
        
    }
    
    public int getCost(char s, char t){
        return Math.abs((int)s - (int)t);
    }
    
}
