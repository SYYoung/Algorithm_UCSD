package Week6;

public class ClassNote {
	public void KnapsackWithRepetition(int W) {
		//	value[0] <- 0
		// for w from 1 to W:
		//		value(w) <- 0
		//		for i from 1 to n:
		//			if w[i] <= w:
		//				val <- value(w-wi) + vi
		//				if val > value(w):
		//					value(w) <- val
		//	return value(W)
	}
	
	public void KnapsackWithoutRep(int W) {
		// recurrence formula:
		//	max{value(w-wi, i-1)+vi, value(w,i-1)}
		// 1st one: including wi, 2nd: wi is not used
		// init all value(0,j) <- 0
		// init all value(w,0) <- 0
		//	for i from 1 to n:
		//		for w from 1 to W:
		//			value(w,i) <- value(w,i-1)
		//			if wi <= w:
		//				val <- value(w-wi, i-1) + vi
		//				if value(w,i)<val
		//					value(w,) <- val
		//	return value(W,n)
	}
	
	public void KnapsackMemory(int W) {
		//	if w is in hash table:
		//		return value(w)
		//	value(w) <- 0
		//	for i from 1 to n:
		//		if wi <= w:
		//			val <- Knapsack(w-wi) + vi
		//			if val>value(w):
		//				value(w) <- val
		//	insert value(w) into hash table with key w
		//	return value(w)
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
		//	for i from 1 to n:
		//		m(i,i) <- di, M(i,i) <- di
		//	for s from 1 to n-1:
		//		for i from 1 to n-s:
		//			j <- i+s
		//			m(oi,j),M(i,j) <- MinAndMax(i,j)
		//	return M(1,n)
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
}
