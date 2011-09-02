sig Person{
	mate: one Person,
	shake: set Person
}

fact cardinality{
	#Person = 10
}

fact noRiflexivity{	
	no iden & shake
	no iden & mate
}

fact symmetricCouples{
	~mate in mate
	~shake in shake
}

fact noShakesInCouples{
	no (mate & shake)
}

assert allDifferent{

}


pred solve () {
	some Alice: Person |
	no disj a,b: Person - Alice | #a.shake = #b.shake
}


run solve for 10 int, exactly 10 Person
