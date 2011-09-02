module russell 

sig Man {shaves: set Man} 

one sig Barber extends Man {} 


fact { 
	all m : Man | m not in m.shaves <=> m in Barber.shaves
} 

pred show{
	
}

run show
