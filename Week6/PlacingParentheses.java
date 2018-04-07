package Week6;

import java.util.Scanner;

public class PlacingParentheses {
	int[] data;
	char[] op;
	
    private long getMaximValue(String exp) {
    	// first parse the input string into data and op arrays
    	parseInput(exp);
      //write your code here
		//	for i from 1 to n:
		//		m(i,i) <- di, M(i,i) <- di
		//	for s from 1 to n-1:
		//		for i from 1 to n-s:
		//			j <- i+s
		//			m(oi,j),M(i,j) <- MinAndMax(i,j)
		//	return M(1,n)
      return 0;
    }

    private static long eval(long a, long b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }
	public void MinAndMax(int i, int j) {
		// min <- +infinity
		//	min <- -infinity
		//	for k from i to j-1:
		//		a <- M(i,k) op_k M(k+1,j)
		//		b <- M(i,k) op_k m(k+1,j)
		//		c <- m(i,k) op_k M(k+1,j)
		//		d <- m(i,k) op_k m(k+1,j)
		//		min <- min(min, a,b,c,d)
		//		max <- max(max, a,b,c,d)
		//	return (min, max)
	}
	
	public void Parentheses(int d1, int op1, int d2, int op2, int dn) {

	}
	
	public void reconstruct() {
		// sequece: 5-8+7x4-8+9
		// check M[1,n] find out how we get this answer. 
		// in order to calculate M[1,n], it can be (1,6) ->(1,1) - (2,6)
		// i.e. for (int i=1; i<n; i++) {
		//		check if M[1,n] = M[1,i] - M[2,6]
		//		since it matches, we will get M[2,6]: min value.
		//		
	}
	
	private void parseInput(String exp) {
        int dataIndex = 0, opIndex = 0;
        data = new int[(exp.length()+1)/2];
        op = new char[exp.length()/2];
        for (int i=0; i<exp.length(); i++) {
        	if (i%2 == 0) {
        		data[dataIndex] = Character.getNumericValue(exp.charAt(i));
        		dataIndex++;
        	}
        	else {
        		op[opIndex] = exp.charAt(i);
        		opIndex++;
        	}		
        }
        System.out.println("this is the data");
        for (int i=0; i<data.length; i++)
        	System.out.print("\t" + data[i]);
        System.out.println("This is operators.");
        for (int i=0; i<op.length; i++)
        	System.out.print("\t" + op[i]);
	}
	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();

        PlacingParentheses path = new PlacingParentheses();
        long ans = path.getMaximValue(exp);
    }
}

