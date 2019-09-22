/*Backtracking based solution for finding lexicographically smallest array satisfying criteria given in this problem -->https://leetcode.com/problems/di-string-match*/
class Solution {
    public int[] diStringMatch(String S) {
        boolean[][] table = new boolean[S.length()+1][S.length()+1];
        for(int i = 0; i <= S.length(); i++){
            for(int j = 0; j <= S.length(); j++){
                if(i != j){
                    if(i > j)
                        table[i][j] = true;
                    else
                        table[i][j] = false;
                }
            }
        }
        int[] nums = new int[S.length() + 1];
        Arrays.fill(nums,-1);
        nums[0] = 0;
        return fun(0,nums,table,S);
    }
    
    public int[] fun(int i,int[] nums,boolean[][] table,String S){
        int count = 0;
        while(nums[nums.length - 1] == -1){
            int ret = -1;
            while(ret == -1){
                if(count == nums.length - 1){
                    if(i == 0){
                        nums[i] = nums[i] + 1;
                        count = 0;
                    }
                    else{
                        nums[i] = -1;
                        return nums;
                    }
                }
                ret = performCheck(i,count,nums,table,S);
                count++;
            }
            nums[i+1] = ret;
            nums = fun(i+1,nums,table,S);                
        }
        return nums;
    }
    
    public boolean contains(int n,int[] nums){
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == n)
                return true;
        }
        return false;
    }
    
    public int performCheck(int i,int count,int[] nums,boolean[][] table,String S){
        if(Character.toString(S.charAt(i)).equals("D"))
            return getSmaller(nums[i],count,nums,table);
        else
            return getLarger(nums[i],count,nums,table);
    }
    
    public int getSmaller(int num,int count,int[] nums,boolean[][] table){
        for(int ind = 0;ind < nums.length; ind++){
            if(ind != num && !contains(ind,nums)){
                if(table[num][ind] == true){
                    if(count == 0)
                        return ind;
                    else
                        count--;
                }
            }
        }
        return -1;
    }
    
    public int getLarger(int num,int count,int[] nums,boolean[][] table){
        for(int ind = 0;ind < nums.length; ind++){
            if(ind != num && !contains(ind,nums)){
                if(table[num][ind] == false){
                    if(count == 0)
                        return ind;
                    else
                        count--;
                }
            }
        }
        return -1;
    }
}
