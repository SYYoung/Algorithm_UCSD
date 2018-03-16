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
	
	public void quickSort(int[] A, int l, int r) {
		// if l>= r:
		//		return
		//	m <- Partition(A, l, r)
		//	{A[m] is in the final position
		//	quickSort(A, l, m-1)
		//	quickSort(A, m+1, r)
	}
	
	public void partition(int[]A, int l, int r) {
		// the pivot is x = A[l]
		// move i from l+1 to r maintaining the following invariant
		//		A[k] <= x for all l+1 <= k <= j
		//		A[k] > x for all j+1 <= k <= i
		// move A[l] to its final place
		// here is the code:
		//	x <- A[l] : pivot
		//	j <- l
		//	for i from l+1 to r:
		//		if A[i] <= x:
		//			j <- j+1
		//			swap A[j] and A[i] so that A[l+1...j]<=x, A[j+1...i] > x
		//	swap A[l] and A[j]
		//	return j
	}
	
	public void randomizedQuickSort(int[] A, int l, int r) {
		//	if l>= r:
		//		return
		//	k <- random number between l and r; try to build a balanced partition
		//	swap A[l] and A[k]
		//	m <- partition(A, l, r)
		// randomizedQuickSort(A, l, m-1)
		// randomizedQuickSort(A, m+1, r)
		
		// to deal with equal numbers, partition into 3 regions: <, =, >
		// such that: l<=k<=m1-1, A[k] < x
		// m1 <= k <= m2, A[k] = x
		// m1+1 <= k <=r, A[k] > x
		// (m1, m2) <- partition3(A, l, r)
	}
	
	public void quickSortTailRecur(int[] A, int l, int r) {
		// while l < r:
		//		m <- partition(A, l, r)
		//		if (m-l) < (r-m):
		//			quickSortTailRecur(A, l, m-1)
		//			l <- m + 1
		//		else:
		//			quickSortTailRecur(A, m+1, r)
		//			r <- m-1
	}
}
