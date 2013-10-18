package algorithm.lc;

import java.util.ArrayList;

/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step you
 * may move to adjacent numbers on the row below.
 * 
 * For example, given the following triangle
 * 
 * [ [2], [3,4], [6,5,7], [4,1,8,3] ]
 * 
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * 
 * Note: Bonus point if you are able to do this using only O(n) extra space,
 * where n is the total number of rows in the triangle.
 * 
 */
public class Triangle {

  public class Solution2 {
    // O(n) space, O(n^2) time
    // use rolling array to store the intermediate results
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
      // Start typing your Java solution below
      // DO NOT write main() function
      int rows = triangle.size();
      if (rows == 0) {
        return 0;
      }
      int lastCols = triangle.get(rows - 1).size();
      int[][] mem = new int[2][lastCols];
      int idx = rows - 1;
      for (int c = 0; c < lastCols; ++c) {
        mem[idx % 2][c] = triangle.get(idx).get(c);
      }

      for (int r = rows - 2; r >= 0; --r) {
        for (int c = 0; c <= r; ++c) {
          mem[r % 2][c] = Math.min(mem[(r + 1) % 2][c], mem[(r + 1) % 2][c + 1]);
          mem[r % 2][c] += triangle.get(r).get(c);
        }
      }
      return mem[0][0];
    }
  }

  // O(n) space, O(n^2) time
  public class SolutionRecursive {
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
      // Start typing your Java solution below
      // DO NOT write main() function
      return min(triangle, 1);
    }

    private int min(ArrayList<ArrayList<Integer>> triangle, int level) {
      if (level == triangle.size()) {
        int min = Integer.MAX_VALUE;
        for (int sum : triangle.get(level - 1)) {
          min = Math.min(min, sum);
        }
        return min;
      } else {
        ArrayList<Integer> prevLayer = triangle.get(level - 1);
        ArrayList<Integer> layer = triangle.get(level);
        for (int i = 0; i < layer.size(); ++i) {
          int cur = layer.get(i);
          if (i == 0) {
            layer.set(i, cur + prevLayer.get(i));
          } else if (i == layer.size() - 1) {
            layer.set(i, cur + prevLayer.get(i - 1));
          } else {
            layer
                .set(i, Math.min(prevLayer.get(i), prevLayer.get(i - 1)) + cur);
          }
        }
        return min(triangle, level + 1);
      }
    }
  }

}
