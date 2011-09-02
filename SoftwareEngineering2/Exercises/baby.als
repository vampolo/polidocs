
sig Person { 
	loves: set Person
}

one sig Baby in Person {}

one sig Me in Person {}

fact initialVersion{
	all p : Person | Baby in p.loves 
     no p: Person - Me | p in Baby.loves
}

//fact version2{
//	all p: Person - Baby | Baby in p.loves
//	all p: Person - Baby | p in Baby.loves implies p=Me
//	#Baby.loves >0
//}

pred show(){}
run show

//assert IamBaby {Me = Baby}
//check IamBaby
