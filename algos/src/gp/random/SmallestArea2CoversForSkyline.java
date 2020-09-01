package gp.random;

/*
There are N rectangular buildings standing along the road next to each other. The K-th building is of size H[K] × 1.

Because a renovation of all of the buildings is planned, we want to cover them with rectangular banners until the renovations are finished. Of course, to cover a building, the banner has to be at least as high as the building. We can cover more than one building with a banner if it is wider than 1.

For example, to cover buildings of heights 3, 1, 4 we could use a banner of size 4×3 (i.e. of height 4 and width 3), marked here in blue:

Buildings of sizes (3 × 1), (1 × 1), (4 × 1), covered with scaffolding of size 4×3

We can order at most two banners and we want to cover all of the buildings. Also, we want to minimize the amount of material needed to produce the banners.

What is the minimum total area of at most two banners which cover all of the buildings?

Test cases:
Example test:   [3, 1, 4]
OK

Example test:   [5, 3, 2, 4]
OK

Example test:   [5, 3, 5, 2, 1]
WRONG ANSWER (got 25 expected 19)

Example test:   [7, 7, 3, 7, 7]
OK

Example test:   [1, 1, 7, 6, 6, 6]
OK
*/

class SmallestArea2CoversForSkyline {

  public int solution(int[] H) {

    int lastBlockIdx=H.length-1;

    int[] tallestBldg=getMaxHtAndIdx(H,0,H.length-1);
    int tallestBldgIdx=tallestBldg[0];
    int tallestBldgHt=tallestBldg[1];

    int secondTallestBldgToRightIdx=-1;
    int secondTallestBldgToRightHt=-1;
    int secondTallestBldgToLeftIdx=-1;
    int secondTallestBldgToLeftHt=-1;

    if ( tallestBldgIdx<lastBlockIdx ) {
      int[] secondTallestBldgToRight=getMaxHtAndIdx(H,tallestBldgIdx+1,lastBlockIdx);
      secondTallestBldgToRightIdx=secondTallestBldgToRight[0];
      secondTallestBldgToRightHt=secondTallestBldgToRight[1];
    }
    if ( tallestBldgIdx>0 ) {
      int[] secondTallestBldgToLeft=getMaxHtAndIdx(H,0,tallestBldgIdx-1);
      secondTallestBldgToLeftIdx=secondTallestBldgToLeft[0];
      secondTallestBldgToLeftHt=secondTallestBldgToLeft[1];
    }

    int area1=-1, area2=-1;

    if ( secondTallestBldgToRightHt!=-1&&secondTallestBldgToRightHt!=tallestBldgHt ) {
      area1=tallestBldgHt*(tallestBldgIdx+1)
          +secondTallestBldgToRightHt*(lastBlockIdx-tallestBldgIdx);
    } else {
      // tallest bldg is the right most bldg
      while ( secondTallestBldgToLeftHt!=tallestBldgHt )
        area1=tallestBldgHt*1+secondTallestBldgToLeftHt*lastBlockIdx;
    }

    if ( secondTallestBldgToLeftHt!=-1 ) {
      area2=tallestBldgHt*(lastBlockIdx-tallestBldgIdx+1)
          +secondTallestBldgToLeftHt*(tallestBldgIdx);
    } else {
      // tallest bldg is the left most bldg
      area2=tallestBldgHt*1+secondTallestBldgToRightHt*lastBlockIdx;
    }
    int minArea=area1<area2? area1 : area2;
    return minArea;
  }

  /*    if ( lastBlockIdx-tallestBldgIdx>tallestBldgIdx ) {
  
      int[] tallestBldg2=getMaxHtAndIdx(H,tallestBldgIdx+1,lastBlockIdx);
      int tallestBldg2Ht=tallestBldg2[1];
      return tallestBldgHt*(tallestBldgIdx+1)+tallestBldg2Ht*(lastBlockIdx-tallestBldgIdx);
    } else if ( lastBlockIdx-tallestBldgIdx<tallestBldgIdx ) {
  
      int[] tallestBldg2=getMaxHtAndIdx(H,0,tallestBldgIdx-1);
      int tallestBldg2Ht=tallestBldg2[1];
      return tallestBldgHt*(lastBlockIdx-tallestBldgIdx+1)+tallestBldg2Ht*tallestBldgIdx;
    } else {
  
      int[] tallestBldg21=getMaxHtAndIdx(H,tallestBldgIdx+1,lastBlockIdx);
      int[] tallestBldg22=getMaxHtAndIdx(H,0,tallestBldgIdx-1);
  
      if ( tallestBldg21[1]<tallestBldg22[1] ) {
  
        return tallestBldg21[1]*(lastBlockIdx-tallestBldgIdx)+tallestBldgHt*(tallestBldgIdx+1);
      } else if ( tallestBldg21[1]>tallestBldg22[1] ) {
  
        return tallestBldg22[1]*tallestBldgIdx+tallestBldgHt*(lastBlockIdx-tallestBldgIdx+1);
      } else {
        int blockSize21=lastBlockIdx-tallestBldgIdx;
        int blockSize22=tallestBldgIdx;
  
        if ( blockSize21<=blockSize22 ) {
          return tallestBldg21[1]*(lastBlockIdx-tallestBldgIdx)+tallestBldgHt*(tallestBldgIdx+1);
        } else {
          return tallestBldg22[1]*tallestBldgIdx+tallestBldgHt*(lastBlockIdx-tallestBldgIdx+1);
        }
      }
    }
  }
  */
  private int[] getMaxHtAndIdx(int[] H, int startIdx, int endIdx) {

    int maxHtIdx=startIdx;
    int maxHt=H[startIdx];

    if ( startIdx==endIdx ) return new int[] { maxHtIdx, maxHt };
    for (int i=startIdx; i<=endIdx; i++) {
      if ( H[i]>maxHt ) {
        maxHt=H[i];
        maxHtIdx=i;
      }
    }
    return new int[] { maxHtIdx, maxHt };
  }
}
