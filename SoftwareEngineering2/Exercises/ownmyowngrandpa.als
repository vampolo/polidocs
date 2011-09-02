/*
 * An Alloy model of the song "I Am My Own Grandpa"
 * by Dwight B. Latham and Moe Jaffe
 * 
 * The challenge is to produce a man who is his own grandfather
 * without resorting to incest or time travel.  Executing the predicate
 * "ownGrandpa" will demonstrate how such a thing can occur.
 * 
 * The full song lyrics, which describe an isomorophic solution,
 * are included at the end of this file.
 * 
 * model author: Daniel Jackson
 */
module examples/tutorial/grandpa

abstract sig Person {
  father: lone Man,
  mother: lone Woman
  }

sig Man extends Person { wife: lone Woman }

sig Woman extends Person { husband: lone Man }

fact Biology { no p: Person | p in p.^(mother+father) }
//fact Biology2 { no p: Person | p in p.^(mother.husband + father.wife) }

fact Terminology { wife = ~husband }

fact SocialConvention {
  no wife & *(mother+father).mother
  no husband & *(mother+father).father
  }

fun grandpas (p: Person): set Person {
  let parent = mother + father + father.wife + mother.husband |
    p.parent.parent & Man
  }

pred ownGrandpa (m: Man) { m in grandpas[m]  }

run ownGrandpa for 4 Person

