package Week5;

import java.util.*;

public class PrimitiveCalculator {
	static String M2 = "X2", M3 = "X3", P1 = "+1"; // M2 refers multiply by 2, M3 refers to multiply by 3, P1 refers plus 1
	//static int[] op = {M2, M3, P1};
	
    private static List<Integer> optimal_sequence_greed(int n) {
        List<Integer> sequence = new ArrayList<Integer>();

        while (n >= 1) {
            sequence.add(n);
            if (n % 3 == 0) {
                n /= 3;
            } else if (n % 2 == 0) {
                n /= 2;
            } else {
                n -= 1;
            }
        }
        Collections.reverse(sequence);
        return sequence;
    }

    
    private static List<Integer> optimal_sequence_dp(int n) {
    		ArrayList<Integer> midResult = new ArrayList<Integer>();
    		ArrayList<String> opSeq = new ArrayList<String>();
    		int[] minOps = new int[n+1];
    		minOps[0]= 0; minOps[1] = 0;
    		//midResult.add(0); midResult.add(1);
    		//opSeq.add(""); opSeq.add("");
    		String currOp = "";
    		
    		for (int k=2; k<=n; k++) {
    			minOps[k] = Integer.MAX_VALUE;
    			int opSoFar = 0;
    			if (( k%2 == 0 ) && (k%3 != 0)){
    				minOps[k] = Math.min(minOps[k/2], minOps[k-1]) + 1;
    			}
    			else if ((k%2 !=0 ) && ( k%3 == 0 )) {
    				minOps[k] = Math.min(minOps[k/3],  minOps[k-1]) + 1;
    			}
    			else if ((k%2 ==0 ) && ( k%3 == 0 )) {
    				int tmp = Math.min(minOps[k/2],  minOps[k/3]);
    				minOps[k] = Math.min(tmp, minOps[k-1]) + 1;
    			}
    			else {
    				minOps[k] = minOps[k-1] + 1;
    				currOp = P1;
    			}
    		}
    		
    		// print out the minOps
    		System.out.println("minOps: num, # of operations");
    		for (int k=1; k<=n; k++) {
    			System.out.println(k + "\t" + minOps[k]);
    		}
    		// now traces back the sequence
    		int val = n;
    		while ( val > 1) {
    			if ((val % 2 == 0) && (val%3 != 0)){
    				System.out.println("compare: " + minOps[val/2] +  "\t" + minOps[val-1]);
    				if (minOps[val/2] < minOps[val-1]) {
    					opSeq.add(M2);
    					midResult.add(val/2);
    					val = val/2;
    					System.out.println("new val = " + val);
    				}
    				else {
    					opSeq.add(P1);
    					midResult.add(val-1);
    					val = val - 1;
    					System.out.println("new val =" + val);
    				}
    			}
    			else if ((n%3 == 0) && (n%2 != 0)) {
    				if (minOps[val/3] < minOps[val-1]) {
    					opSeq.add(M3);
    					midResult.add(val/3);
    					val = val/3;
    				}
    				else {
    					opSeq.add(P1);
    					midResult.add(val-1);
    					val = val - 1;
    				}
    			}
    			else if ((n%3 == 0) && (n%2 == 0)) {
    				if (minOps[val/3] <= minOps[val/2]){
    					if (minOps[val/3] < minOps[val-1]) {
    						opSeq.add(M3);
    						midResult.add(val/3);
    						val = val/3;
    					}
    					else {
    						opSeq.add(P1);
    						midResult.add(val-1);
    						val = val-1;
    					}
    				}
    				else if ((minOps[val/2] < minOps[val-1])) {
    					opSeq.add(M2);
    					midResult.add(val/2);
    					val = val/2;
    				}
    				else {
    					opSeq.add(P1);
    					midResult.add(val-1);
    					val = val-1;
    				}
    			}
    			else {
    					opSeq.add(P1);
    					midResult.add(val-1);
    					val = val-1;
    				}
    		}
    		
    		Collections.reverse(opSeq);
    		Collections.reverse(midResult);
    		
    		System.out.println("\nthe operating sequence is : ");
    		for (String k: opSeq) {
    			System.out.print(", \t" + k);
    		}
    		return midResult;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence_greed(n);
        System.out.println("\nfrom greedy. " + (sequence.size() - 1));
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
        
        List<Integer> seq2 = optimal_sequence_dp(n);
        System.out.println("\n. from dp: " + (sequence.size() - 1));
        for (Integer x: sequence) {
        		System.out.print(x + " " );
        }
    }
}

