package Week2;
import java.util.*;

public class LCM {
  private static long lcm_naive(int a, int b) {
    for (long l = 1; l <= (long) a * b; ++l)
      if (l % a == 0 && l % b == 0)
        return l;

    return (long) a * b;
  }
 
  private static int gcd_eff(int a, int b) {
	  if (b == 0) 
		  return a;
	  return (gcd_eff(b, a%b));
  }
  
  private static long lcm_eff(int a, int b) {
	  // a = d*w, b = d*x, therefore, lcd = d*w*x
	  // d*w*x are both divisble by a(=dw) and b(=nx)
	  long ans1 = gcd_eff(a, b);
	  long ans = ans1 * (a/ans1) * (b/ans1);
	  return ans;
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    	long ans = 0;
    	long startTime = 0;
    	long elapsedTime = 0;
    	
    	System.out.println("Naive LCD algorithm");
    	startTime = System.nanoTime();
    	ans = lcm_naive(a, b);
    	elapsedTime = System.nanoTime() - startTime;
    System.out.println("LCD of: " + a + "," + b + " is: "+ ans);
    System.out.println("take out: " + elapsedTime + "ns or " + 
    			elapsedTime/1000 + "us");
    
    // lcm_eff:
	System.out.println("Effective LCD algorithm");
	startTime = System.nanoTime();
	ans = lcm_eff(a, b);
	elapsedTime = System.nanoTime() - startTime;
	System.out.println("LCD of: " + a + "," + b + " is: "+ ans);
	System.out.println("take out: " + elapsedTime + "ns or " + 
			elapsedTime/1000 + "us");

  }
}
