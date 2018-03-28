package Week5;

import java.util.*;

class AlignmentScore {
	double[][] distTab;
	static double MU = 0, SIG = -0.5,MAT_SCORE = 1;
	String firstStr, secStr;
	StringBuilder newFirst = new StringBuilder();
	StringBuilder newSec = new StringBuilder();
	
	public  AlignmentScore(String A, String B) {
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
		firstStr = new String(A);
		secStr = new String(B);
		distTab = new double[A.length()+1][B.length()+1];
		for (int i=0; i<=A.length(); i++) {
			distTab[i][0] = i*SIG;
			//System.out.println(i*SIG);
		}
		for (int j=0; j<=B.length(); j++) {
			distTab[0][j] = j*SIG;
			//System.out.println(j*SIG);
		}
		
		for (int j=1; j<=B.length(); j++) {
			for (int i=1; i<=A.length(); i++) {
				double insert = distTab[i][j-1] + SIG;
				double del = distTab[i-1][j] + SIG;
				double indel = Math.max(insert, del);
				if (A.charAt(i-1) == B.charAt(j-1)) {
					double match = distTab[i-1][j-1] + MAT_SCORE;
					distTab[i][j] = Math.max(indel, match);
				}
				else {
					double misMatch = distTab[i-1][j-1] + MU;
					distTab[i][j] = Math.max(indel, misMatch);
				}
			}
		}
  }
  
	public void OutputAlignment(int i, int j) {
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
		
		if (i==0 && j==0)
			return;
		if ((i>0) && (distTab[i][j] == distTab[i-1][j]+SIG)) {
			newFirst.insert(0, firstStr.charAt(i-1));
			newSec.insert(0,  '-');
			OutputAlignment(i-1, j);
		}
		else if ((j>0) && (distTab[i][j] == distTab[i][j-1]+SIG)) {
			newFirst.insert(0,  '-');
			newSec.insert(0, secStr.charAt(j-1));
			OutputAlignment(i, j-1);
		}
		else {
			newFirst.insert(0, firstStr.charAt(i-1));
			newSec.insert(0,  secStr.charAt(j-1));
			OutputAlignment(i-1, j-1);
		}

  }
  
	public void OutputTab() {
		// print out the table: distTab
		System.out.println(firstStr + " \t" + secStr);
		for (int i=0; i<distTab.length; i++) {
			for (int j=0; j<distTab[0].length; j++) {
				System.out.print(distTab[i][j] + "\t");
			}
			System.out.println();
		}
		OutputAlignment(firstStr.length(), secStr.length());
		
		double score = distTab[firstStr.length()][secStr.length()];
		int editRes = (int) ((firstStr.length() + secStr.length() - 2*score)/2);
		System.out.println("Edit distance = " + editRes);
		for (int k = 0; k<newFirst.length(); k++) {
			System.out.print(newFirst.charAt(k) + "\t");
		}
		System.out.println();
		for (int k = 0; k<newSec.length(); k++) {
			System.out.print(newSec.charAt(k) + "\t");
		}			
	}
	
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);

    String s = scan.next();
    String t = scan.next();

    AlignmentScore ed = new AlignmentScore(s, t);
    ed.OutputTab();
  }

}
