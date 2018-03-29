package Week5;

import java.util.*;

public class LCS3 {

	double[][][] distTab;
	static double MU = 0, SIG = 0, MAT_SCORE = 1;
	String[] firstSeq, secSeq, thirdSeq;
	ArrayList<String> newFirstSeq = new ArrayList<String>();
	ArrayList<String> newSecSeq = new ArrayList<String>();
	ArrayList<String> newThirdSeq = new ArrayList<String>();
	
	public  void EditDistance(int[] a, int[] b, int[] c) {
		//write your code here
		// A[1..n], B[1..m]
		//	D(i,0) <-i and D(0,j) <- j for all i,j
		//	for j from 1 to m:
		//		for i from 1 to n:
		//			insertion <- D(i,j-1) + 1
		//			deletion <- D(i-1, j) + 1
		//			match <- D(i-1, j-1)
		//			mismatch <- D(i-1, j-1) + 1
		//			if A[i] == B[j]:
		//				D(i,j) <- min(insertion, deletion, match)
		//			else
		//				D(i,j) <- min(insertion, deletion, mismatch)
		//	return D(n,m)
		
		// initializations
		firstSeq = new String[a.length];
		secSeq = new String[b.length];
		thirdSeq = new String[c.length];
		for (int i=0; i<a.length; i++) {
			firstSeq[i] = new String(Integer.toString(a[i]));
		}
		for (int i=0; i<b.length; i++) {
			secSeq[i] = new String(Integer.toString(b[i]));
		}
		for (int i=0; i<c.length; i++) {
			thirdSeq[i] = new String(Integer.toString(c[i]));
		}		
		distTab = new double[a.length+1][b.length+1][c.length+1];
		for (int i=0; i<=a.length; i++) {
			distTab[i][0][0] = i*SIG;
		}
		for (int j=0; j<=b.length; j++) {
			distTab[0][j][0] = j*SIG;
		}
		for (int k=0; k<=c.length; k++) {
			distTab[0][0][k] = k*SIG;
		}		
		
		for (int j=1; j<=secSeq.length; j++) {
			for (int i=1; i<=firstSeq.length; i++) {
				for (int k=1; k<=thirdSeq.length; k++) {
					double indel1 = distTab[i-1][j][k] + SIG;
					double indel2 = distTab[i][j-1][k] + SIG;
					double indel3 = distTab[i][j][k-1] + SIG;
					double indel = Math.max(indel1, Math.max(indel2, indel3));
					if ((firstSeq[i-1].equals(secSeq[j-1])) &&
							(firstSeq[i-1].equals(thirdSeq[k-1]))) {
						double match = distTab[i-1][j-1][k-1] + MAT_SCORE;
						distTab[i][j][k] = Math.max(indel, match);
					}
					else {
						double misMatch = distTab[i-1][j-1][k-1] + MU;
						distTab[i][j][k] = Math.max(indel, misMatch);
					}
				} /* end of k index */
			} /* end of i index */
		} /* end of j index */
  }
  
	public void OutputAlignment(int i, int j, int k) {
		//	if i=0 and j=0:
		//		return
		//	if i>0 and D(i,j) = D(i-1,j)+1:
		//		outputAlignment(i-1,j)
		//		print(A[i],-)
		//	else if j>0 and K(i,j)=D(i,j-1)+1:
		//		outputAlignment(i,j-1)
		//		print(-,B[j])
		//	else:
		//		outputAlignment(i-1,j-1)
		//		print(A[i],B[j])	  
		System.out.println("i, j, k: " + "\t" + i + ",\t" + j + ",\t" + k);
		if ((i+j+k == 1) || (i+j+k == 0))
			return;
		if ((i>0) && (distTab[i][j][k] == distTab[i-1][j][k]+SIG)) {
			newFirstSeq.add(0, new String(firstSeq[i-1]));
			newSecSeq.add(0, "-");
			newThirdSeq.add(0,"-");
			OutputAlignment(i-1, j, k);
		}
		else if ((j>0) && (distTab[i][j][k] == distTab[i][j-1][k]+SIG)) {
			newFirstSeq.add(0, "-");
			newSecSeq.add(0, new String(secSeq[j-1]));
			newThirdSeq.add(0, "-");
			OutputAlignment(i, j-1, k);	
		}
		else if ((j>0) && (distTab[i][j][k] == distTab[i][j][k-1]+SIG)) {
			newFirstSeq.add(0, "-");
			newSecSeq.add(0, "-");
			newThirdSeq.add(0, new String(thirdSeq[k-1]));
			OutputAlignment(i, j, k-1);
		}
		else {
			newFirstSeq.add(0, new String(firstSeq[i-1]));
			newSecSeq.add(0, new String(secSeq[j-1]));
			newThirdSeq.add(0, new String(thirdSeq[k-1]));
			OutputAlignment(i-1, j-1, k-1);
		}

  }
  
	public void OutputTab() {
		// print out the table: distTab
		for (int i=0; i<distTab.length; i++) {
			for (int j=0; j<distTab[0].length; j++) {
				System.out.print(distTab[i][j] + "\t");
			}
			System.out.println();
		}
		OutputAlignment(firstSeq.length, secSeq.length, thirdSeq.length);
		
		double score = distTab[firstSeq.length][secSeq.length][thirdSeq.length];
		//int editRes = (int) ((firstSeq.length + secSeq.length - 2*score)/2);
		//System.out.println("Edit distance = " + editRes);
		for (int k = 0; k< newFirstSeq.size(); k++) {
			System.out.print(newFirstSeq.get(k) + "\t");
		}
		System.out.println();
		for (int k = 0; k<newSecSeq.size(); k++) {
			System.out.print(newSecSeq.get(k) + "\t");
		}	
		System.out.println();
		for (int k = 0; k<newThirdSeq.size(); k++) {
			System.out.print(newThirdSeq.get(k) + "\t");
		}			
	}
	
	public int CalLCS3() {
		ArrayList<String> ans = new ArrayList<String>();
		int res = 0;
		for (int k=0; k<newFirstSeq.size(); k++) {
			if ((newFirstSeq.get(k).equals(newSecSeq.get(k))) &&
					(newFirstSeq.get(k).equals(newThirdSeq.get(k)))) {
				res++;
				ans.add(newFirstSeq.get(k));
			}
		}
		System.out.println("\nThe LCS is : " + ans);
		return res;
	}
	
    public LCS3(int[] a, int[] b, int[] c) {
        //Write your code here
    		EditDistance(a, b, c);
        OutputTab();
        System.out.println("\n" + CalLCS3());
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int an = scanner.nextInt();
        int[] a = new int[an];
        for (int i = 0; i < an; i++) {
            a[i] = scanner.nextInt();
        }
        int bn = scanner.nextInt();
        int[] b = new int[bn];
        for (int i = 0; i < bn; i++) {
            b[i] = scanner.nextInt();
        }
        int cn = scanner.nextInt();
        int[] c = new int[cn];
        for (int i = 0; i < cn; i++) {
            c[i] = scanner.nextInt();
        }
        
        LCS3 tmpLcs3 = new LCS3(a, b, c);
    }
}

