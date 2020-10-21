class Solution {
    String S1, S2, S3; //Global variables so as to not bloat the signature of the dfs function
    public boolean isInterleave(String s1, String s2, String s3) {
        S1 = s1;
        S2 = s2;
        S3 = s3;        
        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();
        if(l1 == 0)return s2.equals(s3); //Edge case S1 is empty. Check if S3 is a copy of S2
        if(l2 == 0)return s1.equals(s3); //Edge case same as above with S1 and S2 switched
        Boolean[][] memo = new Boolean[l1+1][l2+1];
        memo[0][0] = true; //An empty string ALWAYS matches an empty string
        if(l1 + l2 != l3)return false; //Sanity Check: S3.length() MUST be equal to S2.length() plus S1.length()
        return dfs(l1 - 1, l2 - 1, l3 -1, memo);
    }
    //The recursion function
    public boolean dfs(int i, int j, int k, Boolean[][] memo){
        if(memo[i+1][j+1] != null)return memo[i+1][j+1]; //Base case: if this value has been computed before, DO NOT recompute, just return it
        //If we have consumed all of the characters from S1, we no longer attempt to use it to test
        //We only use S2 from now on
        if(i < 0){
            memo[i+1][j+1] = S2.charAt(j) == S3.charAt(k) && dfs(i, j-1, k-1, memo);
            return memo[i+1][j+1];
        }
        //Same as last if-block except S2 is out of characters now
        //So we just use S1
        if(j < 0){
            memo[i+1][j+1] = S1.charAt(i) == S3.charAt(k) && dfs(i-1, j, k-1, memo);
            return memo[i+1][j+1];
        }
        //Case 4
        if(S1.charAt(i) != S3.charAt(k) && S2.charAt(j) != S3.charAt(k)){
            memo[i+1][j+1] = false;
            return memo[i+1][j+1];
        }
        if(i >= 0 && S1.charAt(i) == S3.charAt(k) ){
            if(j >= 0 && S2.charAt(j) == S3.charAt(k)){
                memo[i+1][j+1] = dfs(i-1, j, k-1, memo) || dfs(i, j-1, k-1, memo); //Case 3
            }else{
                memo[i+1][j+1] = dfs(i-1, j, k-1, memo); //Case 1
            }
        }else if(j >= 0 && S2.charAt(j) == S3.charAt(k)){
            memo[i+1][j+1] = dfs(i, j-1, k-1, memo); //Case 2
        }else{
            memo[i+1][j+1] = false;
        }        
        return memo[i+1][j+1];
        
    } 
}
