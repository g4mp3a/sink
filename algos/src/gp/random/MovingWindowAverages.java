package gp.random;

public class MovingWindowAverages {

  public static long[] getAverages(final int[] arr, final int windowSize) {
    final long[] result=new long[(arr.length-windowSize)+1];
    if (arr.length<=windowSize) {
      for (final int element : arr) {
        result[0]=result[0]+element;
      }
      result[0]=result[0]/arr.length;
      return result;
    }
    for (int i=0; i<((arr.length-windowSize)+1); ++i) {
      int sum=0;
      for (int j=0; j<windowSize; ++j) {
        sum=sum+arr[i+j];
      }
      result[i]=sum/windowSize;
    }
    return result;
  }

  public static long[] efficientGetAverages(final int[] arr, final int windowSize) {
    final long[] result=new long[(arr.length-windowSize)+1];
    int sum=0;
    for (int i=0; i<arr.length; ++i) {
      if (i<windowSize) {
        sum=sum+arr[i];
        continue;
      }
      result[i-windowSize]=sum/windowSize;
      sum=(sum+arr[i])-arr[i-windowSize];
    }
    if (result[0]==0) {
      result[0]=sum/arr.length;
    }
    return result;
  }
}
