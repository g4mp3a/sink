package gp.binarysearch;

/**
 * Binary search to find a target number in a sorted array. O(log(n)) time
 * complexity.
 *
 * @author gautampriya
 */
public class BinarySearch {

  public static boolean binarySearch(final int target, final int[] nums) {
    // floorIndex and ceilingIndex are like walls around the possible
    // positions of the search target, so -1 below represents the
    // starting position of the left wall to the left of the 0th index,
    // similarly nums.length represents the starting position of the
    // right wall to the right of the last index.
    int floorIndex=-1, ceilingIndex=nums.length;

    // if there isnt at least 1 index between the floor and ceiling,
    // we have run out of guesses
    while ((floorIndex+1)<ceilingIndex) {

      // find index ~halfway between floor and ceiling
      final int distance=ceilingIndex-floorIndex;
      final int halfDistance=distance/2;
      final int guessIndex=floorIndex+halfDistance;

      final int guessValue=nums[guessIndex];

      if (guessValue==target)
        return true;

      if (guessValue<target) {
        // target is in the 2nd half of the array
        floorIndex=guessIndex;
      } else {
        ceilingIndex=guessIndex;
      }
    }

    return false;
  }

}
