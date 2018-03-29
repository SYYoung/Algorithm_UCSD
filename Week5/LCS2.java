package Week5;

import java.util.*;

public class LCS2 {
	double[][] distTab;
	static double MU = 0, SIG = 0, MAT_SCORE = 1;
	String[] firstSeq, secSeq;
	ArrayList<String> newFirstSeq = new ArrayList<String>();
	ArrayList<String> newSecSeq = new ArrayList<String>();
	
	public  void EditDistance(int[] a, int[] b) {
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
		firstSeq = new String[a.length];
		secSeq = new String[b.length];
		for (int i=0; i<a.length; i++) {
			firstSeq[i] = new String(Integer.toString(a[i]));
		}
		for (int i=0; i<b.length; i++) {
			secSeq[i] = new String(Integer.toString(b[i]));
		}
		distTab = new double[a.length+1][b.length+1];
		for (int i=0; i<=a.length; i++) {
			distTab[i][0] = i*SIG;
		}
		for (int j=0; j<=b.length; j++) {
			distTab[0][j] = j*SIG;
		}
		
		for (int j=1; j<=secSeq.length; j++) {
			for (int i=1; i<=firstSeq.length; i++) {
				double insert = distTab[i][j-1] + SIG;
				double del = distTab[i-1][j] + SIG;
				double indel = Math.max(insert, del);
				if (firstSeq[i-1].equals(secSeq[j-1])) {
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
		
		if ((i+j == 0) || (i+j == 1))
			return;
		if ((i>0) && (distTab[i][j] == distTab[i-1][j]+SIG)) {
			newFirstSeq.add(0, new String(firstSeq[i-1]));
			newSecSeq.add(0, "-");
			OutputAlignment(i-1, j);
		}
		else if ((j>0) && (distTab[i][j] == distTab[i][j-1]+SIG)) {
			newFirstSeq.add(0, "-");
			newSecSeq.add(0, new String(secSeq[j-1]));
			OutputAlignment(i, j-1);
		}
		else {
			newFirstSeq.add(0, new String(firstSeq[i-1]));
			newSecSeq.add(0, new String(secSeq[j-1]));
			OutputAlignment(i-1, j-1);
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
		OutputAlignment(firstSeq.length, secSeq.length);
		
		double score = distTab[firstSeq.length][secSeq.length];
		int editRes = (int) ((firstSeq.length + secSeq.length - 2*score)/2);
		System.out.println("Edit distance = " + editRes);
		for (int k = 0; k< newFirstSeq.size(); k++) {
			System.out.print(newFirstSeq.get(k) + "\t");
		}
		System.out.println();
		for (int k = 0; k<newSecSeq.size(); k++) {
			System.out.print(newSecSeq.get(k) + "\t");
		}			
	}
	
	public int CalLCS() {
		ArrayList<String> ans = new ArrayList<String>();
		int res = 0;
		for (int k=0; k<newFirstSeq.size(); k++) {
			if (newFirstSeq.get(k).equals(newSecSeq.get(k))) {
				res++;
				ans.add(newFirstSeq.get(k));
			}
		}
		System.out.println("\nThe LCS is : " + ans);
		return res;
	}
	
    public LCS2(int[] a, int[] b) {
        //Write your code here
    		EditDistance(a, b);
        OutputTab();
        System.out.println("\n" + CalLCS());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
     
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = scanner.nextInt();
        }

        LCS2 tmpLcs2 = new LCS2(a, b);
    }
}

