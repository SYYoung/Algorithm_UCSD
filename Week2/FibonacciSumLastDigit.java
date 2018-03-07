package Week2;
import java.util.*;

public class FibonacciSumLastDigit {
    private static long getFibonacciSumNaive(long n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;
        long sum      = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = (tmp_previous + current)%10;
            sum = (sum + current) %10;
        }

        return sum;
    }
    
    private static long getFibSumLDEffPisanoP(long n) {
		// find next cycle which has {0,1}
		long tmp_previous = 1, previous = 1, current = 1;
		long k = 2;
		while (!(tmp_previous == 0) || !(previous == 1)) {
			k ++;
			current = ((tmp_previous % 10) + (previous % 10)) % 10;
			tmp_previous = previous;
			previous = current;
		}
		k--;
		System.out.println("the period is : " + k);
		// since we find out the period, we can calcuate the answer
		long ans = getFibonacciSumNaive(n%k);
		return ans;
}

    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        
        System.out.println("from naive");
        long startTime = System.nanoTime();
        long ans = getFibonacciSumNaive(n);
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("the sum to n=" + n + ", ans = " + ans);
        System.out.println(" finished in: " + elapsedTime + " ns");
        
        System.out.println("from effective");
        startTime = System.nanoTime();
        ans = getFibSumLDEffPisanoP(n);
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("the sum is: " + ans);
        System.out.println(" finished in: " + elapsedTime + " ns");
    }
}

