package Week5;

public class Week5Code {
	public void GreedyChange(int money) {
		// change <- empty collection of coins
		// while money > 0:
		//		coin <- largest denomination that does not exceed money
		//		add coin to Change
		//		money <- money - coin
		//	return Change
	}
	
	public void RecursiveChange(int money, int[] coins) {
		//	if money = 0:
		//		return 0
		//	MinNumCoins <- infinity
		//	for i from 1 to |coins|:
		//		if money >= coin[i]:
		//			NumCoins <- RecursiveChange(money=coin[i], coins)
		//		if NumCoins + 1 < MinNumCoins:
		//			MinNumCoins <- NumCoins + 1
		//	return MinNumCoins
		// MinNumCoins(money) = min(MinNumCoins(money-coin[1])+1, MinNumCoins(money-coin[2]+1,...
		//							MinNumCoins(money-coin[d])+1
	}
	
	public void DPChange(int money, int[]coins) {
		// MinNumCoins(0) <- 0
		//	for m from 1 to money:
		//		MinNumCoins(m) >- infinity
		//		for i from 1 to |coins|:
		//			if m >= coin[i]:
		//				NumCoins <- MinNumCoins(m-coin[i]) + 1
		//				if NumCoins < MinNumCoins(m):
		//					MinNumCoins(m) <- NumCoins
		//	return MinNumCoins(money)
	}
	
	public void EditDistance( int[] A, int[] B) {
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
	}
	
	public void outputAlignment(int i, int j) {
		//	if i = 0 and j = 0:
		//		return
		//	if backtrack(i,j)= down_arrow:
		//		outputAlignment(i-1, j)
		//		print( A[i], -)
		//	else if backtrack(i,j) = right_arrow
		//		outputAlignment(i,j-1)
		//		print( -, B[j])
		//	else:
		//		outputAlignment(i-1,j-1)
		//		print(A[i],N[j])
	}
	
	public void outputAlignment2(int i, int j) {
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
	}
	
}
