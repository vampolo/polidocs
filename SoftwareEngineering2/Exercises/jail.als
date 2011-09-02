module prison

sig Gang {members: set Inmate} 

sig Inmate {room: Cell} 

sig Cell {} 


fun enemies(x: Inmate) : set Inmate{
	(Gang - giveMeGang[x]).members
}


fun giveMeGang(x : Inmate) : set Gang{
	members.x
}



pred safe(y : Inmate)  {
	no x : Inmate | y.room=x.room && x in enemies[y]		
}


pred unsafe(y : Inmate){
	some x : Inmate |  y.room = x.room && y in enemies[x]		
}

pred happy(p : Inmate){
	all x: Inmate | p.room=x.room implies giveMeGang[x]=giveMeGang[p]
}


pred show () { 
 	
	#Gang>1
	#Inmate>2
	
	all y : Inmate | safe[y]
	//some y : Inmate |  unsafe[y]
	
 } 

assert happyness{	
	all p : Inmate | safe[p] implies happy[p]
}

//fact{
	//Happyness
	//all p : Inmate | #giveMeGang[p]=1
//}


run show for 3 but 2 Gang, 3 Inmate
//check happyness
