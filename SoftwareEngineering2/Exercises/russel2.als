module russell 

abstract sig Human {shaves: set Man} 

sig Man extends Human{}

sig Woman extends Human{}

one sig Barber in Human {} 

fact { 
	Barber.shaves = { m: Man | m not in m.shaves } 
} 

pred show{
 	#Human >2
}

run show
