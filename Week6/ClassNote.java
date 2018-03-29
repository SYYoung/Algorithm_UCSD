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
}
