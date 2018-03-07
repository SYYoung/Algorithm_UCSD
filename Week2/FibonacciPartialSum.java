package Week2;
import java.util.*;

public class FibonacciPartialSum {
    private static long getFibonacciPartialSumNaive(long from, long to) {
        long sum = 0;

        long current = 0;
        long next  = 1;

        for (long i = 0; i <= to; ++i) {
            if (i >= from) {
                sum += current % 10;
            }

            long new_current = next;
            next = (next + current) % 10;
            current = new_current;
        }

        return sum % 10;
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

private static long getFibPartialSumEffPisanoP(long n, long m) {
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
		// since we find out the period, we can calcuate the answer
		long ans = getFibonacciPartialSumNaive(n%k, m%k);
		return ans;
}

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long from = scanner.nextLong();
        long to = scanner.nextLong();
        
        System.out.println("From naive algorithm:");
        long startTime = System.nanoTime();
        long ans = getFibonacciPartialSumNaive(from, to);
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("ans = " + ans + ", take " + 
        			elapsedTime + " ns to finish.");
        
        System.out.println("From efficient algorithm:");
        startTime = System.nanoTime();
        ans = getFibPartialSumEffPisanoP(from, to);
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("ans = " + ans + ", take " +
        			elapsedTime + " ns to finish.");
    }
}

