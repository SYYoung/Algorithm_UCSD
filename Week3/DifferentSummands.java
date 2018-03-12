package Week3;

import java.util.*;

public class DifferentSummands {
    private static List<Integer> optimalSummands(int n) {
		// R<- {}, i<-1
		// while i<=n:
		//		[l,r] <- [xi, xi+1] unit length
		//		i <- i + 1;
		//		try to find out the next leftmost point after this unit segment
		//		while i<=n and xi <= r
		//			i <- i+1
		// return R
        List<Integer> summands = new ArrayList<Integer>();
        int curr = 0;
        int leftOver = n;
        while (leftOver >= 2*curr + 3) {
        		curr += 1;
        		leftOver -= curr;
        		summands.add(curr);
        }
        summands.add(leftOver);
        //write your code here
        return summands;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}

