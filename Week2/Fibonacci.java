package Week2;
import java.util.Scanner;

public class Fibonacci {
  private static long calc_fib(int n) {
    if (n <= 1)
      return n;

    return calc_fib(n - 1) + calc_fib(n - 2);
  }
  
  public static long calc_fib_eff(int n) {
	  long[] fibArr = new long[n+1];
	  fibArr[0] = 0;
	  fibArr[1] = 1;
	  for (int k=2; k<=n; k++) {
		  fibArr[k] = fibArr[k-1] + fibArr[k-2];
	  }
	  return fibArr[n];
  }

  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    long startTime = 0;
    long elapsedTime = 0;
    long ans = 0;
    
    System.out.println("this is from naive algorithm:");
    startTime = System.nanoTime();
    ans = calc_fib(n);
    elapsedTime = System.nanoTime() - startTime;
    System.out.println("ans = " + ans );
    System.out.println(" , take time = " + elapsedTime +"ns or " + elapsedTime/1000
    		+ "us");
    
    System.out.println("this is from efficient algorithm:");
    startTime = System.nanoTime();
    ans = calc_fib_eff(n);
    elapsedTime = System.nanoTime() - startTime;
    System.out.println("ans = " + ans );
    System.out.println("take time = " + elapsedTime +"ns." +
    			" or " + elapsedTime/1000 + "us");
        
  }
}
