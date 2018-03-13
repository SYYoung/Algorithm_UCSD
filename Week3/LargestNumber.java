package Week3;

import java.util.*;

public class LargestNumber {
	static String largestNumber(String[] a) {
        //write your code here
    		ArrayList<String> answer = new ArrayList<String>();
    		ArrayList<String> digits = new ArrayList<String>();
    		for (int k=0; k<a.length; k++)
    			digits.add(a[k]);
    		while (!digits.isEmpty()) {
    			String maxDigit = "";
    			for (String d: digits) {
    				String dFirst = d + maxDigit;
    				String mFirst = maxDigit + d;
    				if (dFirst.compareTo(mFirst) >= 0) {
    					maxDigit = d;
    				}
    			}
    			answer.add(maxDigit);
    			digits.remove(maxDigit);
    		}
    		
    		String result = "";
    		for (int k=0; k<answer.size(); k++)
    			result = result + answer.get(k);
    		return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        System.out.println(largestNumber(a));
    }
}

