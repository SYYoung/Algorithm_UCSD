package Week2;
import java.util.*;

public class GCD {
  private static int gcd_naive(int a, int b) {
    int current_gcd = 1;
    for(int d = 2; d <= a && d <= b; ++d) {
      if (a % d == 0 && b % d == 0) {
        if (d > current_gcd) {
          current_gcd = d;
        }
      }
    }

    return current_gcd;
  }
  
  public static int gcd_eff(int a, int b) {
	  if (b == 0) 
		  return a;
	  return (gcd_eff(b, a%b));
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    long startTime = 0;
    long elapsedTime = 0;
    
    startTime = System.nanoTime();
    int ans = gcd_naive(a,b);
    elapsedTime = System.nanoTime() - startTime;
    System.out.println("naive form: The GCD of " + a + ", " + b + " = " + ans);
    System.out.println("Time taken: " + elapsedTime/1000 + "us");
    
    startTime = System.nanoTime();
    ans = gcd_eff(a,b);
    elapsedTime = System.nanoTime() - startTime;
    System.out.println("Eff form: The GCD of " + a + ", " + b + " = " + ans);
    System.out.println("Time taken: " + elapsedTime/1000 + "us");
    
  }
}
