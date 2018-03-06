package Week2;
import java.util.*;

public class FibonacciHuge {
    private static long getFibonacciHugeNaive(long n, long m) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        return current % m;
    }
    
    private static long getFibHugeEffDirect(long n, long m) {
    		// this one directly calcautes the mod without using the pisano period
    		long tmp_previous = 0, previous = 1;
    		long current = 0;
    		for (long k=2; k<=n; k++) {
    			current =  ((tmp_previous % m) + (previous % m)) % m;
    			tmp_previous = previous;
    			previous = current;
    		}
    		return current;
    }
    
    private static long getFibHugeEffPisanoP(long n, long m) {
    		// find next cycle which has {0,1}
    		long tmp_previous = 1, previous = 1, current = 1;
    		long k = 2;
    		while (!(tmp_previous == 0) || !(previous == 1)) {
    			k ++;
    			current = ((tmp_previous % m) + (previous % m)) % m;
    			tmp_previous = previous;
    			previous = current;
    		}
    		k--;
    		// since we find out the period, we can calcuate the answer
    		long ans = getFibHugeEffDirect(n%k, m);
    		return ans;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        
        System.out.println("This is from Naive :");
        long startTime = System.nanoTime();
        long ans = getFibonacciHugeNaive(n,m);
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("n=" + n + ",m=" + m + ", ans=" + ans);
        System.out.println("elapsed time = " + elapsedTime + "ns");
        
        System.out.println("This is from DirectEff.");
        startTime = System.nanoTime();
        ans = getFibHugeEffDirect(n,m);
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("n=" + n + ",m=" + m + ", ans=" + ans);
        System.out.println("elapsed time = " + elapsedTime + "ns");
        
        System.out.println("This is from PisanoEff.");
        startTime = System.nanoTime();
        ans = getFibHugeEffPisanoP(n,m);
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("n=" + n + ",m=" + m + ", ans=" + ans);
        System.out.println("elapsed time = " + elapsedTime + "ns");
        
    }
}

