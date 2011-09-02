sig Num {
	prev : lone Num,
	next : lone Num,
	greater : set Num,
	smaller : set Num
}

fact NoReflexivity{
	no iden & prev
	no iden & next
	no iden & greater
	no iden & smaller
}

fact Symmetry{
	all n, m : Num | m = n.prev <=> n = m.next
	all n, m : Num | m in n.greater <=> n in m.(^prev)
	all n, m : Num | m in n.smaller <=> n in m.(^next)
}

fact MaxMin{
	#Num - #prev =1
}

pred show{
	#Num=4
}

assert NoCycle{
 no n : Num | n in n.(^next)
}


run show for 4
//check NoCycle
