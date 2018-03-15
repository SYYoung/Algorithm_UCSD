package Week4;

public class Sort {
	public void selectionSort(int[] A) {
		// for i from 1 to n:
		//		minIndex <- 1
		//		for j from i+1 to n:
		//			if A[j] < A[minIndex]:
		//				minIndex <- j
		//		swap(A[i], A[minIndex])
	}
	
	public void mergeSort(int[] A) {
		// if n== 1:
		//		return A
		//	m <- [n/2]
		//	B <- mergeSort(A[1..m])
		//	C <- mergeSort(A[m+1..n])
		//	A' <- merge(B,C)
		// 	return A'
	}
	
	public void merge(int[]B, int[]C) {
		// D <- empty array of size p+q
		// while B and C are both non-empty:
		//		b <- the first element of B
		//		c <- the first element of C
		//		if b<=c:
		//			move b from B to the end of D
		//		else:
		//			move c from C to the end of D
		//	move the rest of B and C to the end of D
		//	return D			
	}
	
	public void countingSort(int[] A) {
		// assume that all elements of A are int from 1 to M
		// By a single scan, count num of occurences 
		// between 1<=k<=M and store in count[k]
		// count [1..m] <- [0...0]
		// for i from 1 to n:
		//		count[A[i]] <- count[A[k]] + 1
		// pos[1..m] <- [0...0]
		// pos[1] <- 1
		// for j from 2 to m:
		// 		pos[j] <- pos[j-1] + count[j-1]
		// for i from 1 to n:
		// 		A'[pos[A[i]]] <- A[i]
		//		pos[A[i]] <- pos[A[i]] + 1
	}
}
