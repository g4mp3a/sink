package gp.random;

class NumOriginalMessagesSolution {
  
  static int helper(String code, int sizeOfSuffix) {
    
    if (sizeOfSuffix == 0) return 1;
    
    int s = code.length - sizeOfSuffix;
    if (code.charAt(s) == 0) return 0;
    
    int result = helper(code, sizeOfSuffix-1);
    if (sizeOfSuffix >= 2 
        && Integer.valueOf(code.subString(s, s+2))) <= 26) {
        
      result += helper(code, sizeOfSuffix-2);
    }
    
    return result;
  }
  
  static int memoizedHelper(String code, int sizeOfSuffix, int[] memo) {
    
    if (sizeOfSuffix == 0) return 1;
    
    int s = code.length - sizeOfSuffix;
    if (code.charAt(s) == 0) return 0;
    
    if (memo[sizeOfSuffix] != null) return memo[sizeOfSuffix];
    
    int result = helper(code, sizeOfSuffix-1, memo);
    if (sizeOfSuffix >= 2 
        && Integer.valueOf(code.subString(s, s+2))) <= 26) {
        
      result += helper(code, sizeOfSuffix-2, memo);
    }
    memo[sizeOfSuffix] = result;
    
    return result;
  }
  
  static int numMessages(String code) {
    return helper(code, code.length);
  }
  
  static int memoizedNumMessages(String code) {
    
    int[] memo = new int[code.length+1];
    return helper(code, code.length, memo);
  }
}