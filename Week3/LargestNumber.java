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

	public static void StressTest() {
		// n is between 1 and 100
		// those numbers are between 1 and 1000
		int num = (int)(Math.random() * 10);
		if (num ==0)
			num = 1;
		// generate those numbers
		String[] a = new String[num];
		for (int k=0; k<num; k++) {
			int val;
			val = (int)(Math.random() * 100);
			a[k] = String.valueOf(val);
		}
		//display the numbers
		System.out.println("number generated: " + num);
		for (String s : a) {
			System.out.print("\t" + s);
		}
		System.out.println();
		System.out.println(largestNumber(a));
	}
	
    public static void main(String[] args) {
    	/*
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        System.out.println(largestNumber(a));
        */
        StressTest();
    }
}

