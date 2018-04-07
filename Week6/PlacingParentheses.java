package Week6;

import java.util.Scanner;

public class PlacingParentheses {
	int[] data;
	char[] op;
	long[][] maxArr;
	long[][] minArr;
	StringBuilder finalExp = new StringBuilder();
	
    private long getMaximValue(String exp) {
    	// first parse the input string into data and op arrays
    	parseInput(exp);
    	maxArr = new long[data.length][data.length];
    	minArr = new long[data.length][data.length];
		//	for i from 1 to n:
		//		m(i,i) <- di, M(i,i) <- di
		//	for s from 1 to n-1:
		//		for i from 1 to n-s:
		//			j <- i+s
		//			m(oi,j),M(i,j) <- MinAndMax(i,j)
		//	return M(1,n)
    	for (int i=1; i<=data.length-1; i++) {
    		maxArr[i][i] = data[i];
    		minArr[i][i] = data[i];
    	}
    	displayArr(maxArr, "MaxArr");
    	displayArr(minArr, "MinArr");
    	int numData = data.length-1;
    	for (int s=1; s<= numData-1; s++) {
    		for (int i=1; i<=numData-s; i++) {
    			int j = i+s;
    			long[] minMax = MinAndMax(i, j);
    			minArr[i][j] = minMax[0];
    			maxArr[i][j] = minMax[1];
    		}
    	}
    	displayArr(maxArr, "This is maxArray");
    	displayArr(minArr, "This is minArray");
      return maxArr[1][numData];
    }

    private void displayArr(long[][] arr, String title) {
    	System.out.println(title);
		for (int i=1; i<arr.length; i++) {
			for (int j=1; j<arr.length; j++)
				System.out.print("\t" + arr[i][j]);
			System.out.println();
		}
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
	public long[] MinAndMax(int i, int j) {
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
		long maxVal = Integer.MIN_VALUE;
		long minVal = Integer.MAX_VALUE;
		for (int k=i; k<=j-1; k++) {
			long a = eval(maxArr[i][k], maxArr[k+1][j], op[k]);
			long b = eval(maxArr[i][k], minArr[k+1][j], op[k]);		
			long c = eval(minArr[i][k], maxArr[k+1][j], op[k]);
			long d = eval(minArr[i][k], minArr[k+1][j], op[k]);
			minVal = Math.min(minVal, Math.min(Math.min(a, b), Math.min(c, d)));
			maxVal = Math.max(maxVal, Math.max(Math.max(a, b), Math.max(c, d)));
		}
		long[] ret = new long[2];
		ret[0] = minVal; 
		ret[1] = maxVal;
		return ret;
	}
	public void buildup() {
		long ans = maxArr[1][data.length-1];
		int start = 1, end = data.length-1;
		buildupSub(start, end);
		System.out.println("The max expression is: " + finalExp.toString());
	}
	
	public void buildupSub(int start, int end) {
		// sequece: 5-8+7x4-8+9
		// check M[1,n] find out how we get this answer. 
		// in order to calculate M[1,n], it can be (1,6) ->(1,1) - (2,6)
		// i.e. for (int i=1; i<n; i++) {
		//		check if M[1,n] = M[1,i] - M[2,6]
		//		since it matches, we will get M[2,6]: min value.
		//	
		if (start == end) {
			finalExp.append(data[start]);
			return;
		}
		int bIndex = reconstruct(start, end, maxArr[start][end]);
		finalExp.append("(");
		buildupSub(start, bIndex);
		finalExp.append(op[bIndex]);
		buildupSub(bIndex+1, end);
		finalExp.append(")");
	}
	
	public int reconstruct(int start, int end, long ans) {
		int bIndex = start;
		for (int k=start; k<end; k++) {
			long a = eval(maxArr[start][k], maxArr[k+1][end], op[k]); 
			long b = eval(maxArr[start][k], minArr[k+1][end], op[k]); 
			long c = eval(minArr[start][k], maxArr[k+1][end], op[k]); 
			long d = eval(minArr[start][k], minArr[k+1][end], op[k]); 
			if ((a==ans) || (b==ans) || (c==ans) || (d==ans)) {
				bIndex = k;
				break;
			}
		}
		return bIndex;
	}
	
	private void parseInput(String exp) {
        int dataIndex = 1, opIndex = 1;
        data = new int[(exp.length()+1)/2 +1];
        op = new char[exp.length()/2 +1];
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
        for (int i=1; i<data.length; i++)
        	System.out.print("\t" + data[i]);
        System.out.println("This is operators.\n");
        for (int i=1; i<op.length; i++)
        	System.out.print("\t" + op[i]);
	}
	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();

        PlacingParentheses path = new PlacingParentheses();
        long ans = path.getMaximValue(exp);
        System.out.println("The max value of the expression = " + ans);
        path.buildup();
    }
}

