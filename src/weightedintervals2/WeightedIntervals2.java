package weightedintervals2;
import java.io.*;
import java.util.*;
public class WeightedIntervals2 {
   static int[][] interval;
   static int[] memos = new int[500];
   public static int p(int j) {
      int max;
      max = 0;
      for (int i = 1; i < j; i++) {
          if ((interval[i][1] < interval[j][0]) &&
                  i > max) max = i;
      } return max;
   }
   public static int opt(int j) {
       if (j == 0) return 0;
       if (memos[j] == -1)
         memos[j] = Math.max(interval[j][2] + opt(p(j)), opt(j-1));
       
       return memos[j];
   }
   public static void getIntervals(int j) {
       if (j == 0) return;
       else if (interval[j][2] + memos[p(j)] > memos[j-1]) {
           System.out.print(j + " ");
           getIntervals(p(j));
       } else
           getIntervals(j-1);
   }
   public static void readFile(String fileName) throws FileNotFoundException {
      int n;

      File F = new File(fileName);
      Scanner fileIn = new Scanner(F);
      n = Integer.parseInt(fileIn.nextLine());
      interval = new int[n+1][3];
      for(int i = 1; i < n+1; i++) {
          for(int j = 0; j < 3; j++) {
              interval[i][j] = Integer.parseInt(fileIn.next());
          }if (i != n) fileIn.nextLine();
      }
      for(int i = 1; i < n+1; i++) {
          for(int j = 0; j < 3; j++) {
              System.out.print(interval[i][j] + " ");
          }System.out.println();
      }
      System.out.println("Maximum Profit is : " + opt(n));
      System.out.print("Using intervals : ");
      getIntervals(n);
   }
   public static void main(String[] args) throws FileNotFoundException {
      String fileName;
      Scanner stdin = new Scanner(System.in);
      for(int i = 0; i < memos.length; i++) memos[i] = -1;
      System.out.print("File Name: ");
      fileName = stdin.next();
      readFile(fileName);
   }
}