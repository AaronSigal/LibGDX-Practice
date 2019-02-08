package com.roguelike.lootly.item.ItemEffects;


//Provides a set of methods that generate a random value for the appropriate corresponding argument in an effect. For example, 
//getRandArg0 will return a valid and constrained random value to be passed to arg0 in a configurable effect. This value can
//be generated any which way, all that matters is that a value gets returned. Note that this interface should only ever be implement in
//conjunction with ConfigurableEffect. 
//
//In general, it is good practice to provide tier support for an effect. The lowest potion tier is 1, the highest being 5. Using a switch statement is ideal.
//For example:
/*  
  switch (tier) {
  		case 1:
  		//...
  		break;
  		case 2:
  		//...
  		break;
  		case 3:
  		//...
  		break;
  		case 4:
  		//...
  		break;
  		case 5:
  		//...
  		break;
  		default:
  		//VERY IMPORTANT TO COVER
  		break;
  }
 */
///
//These functions should only every be called by the Random Potion Generator. Generally, this isn't something that should occur at the user's demand.
//Note: only use ThreadLocalRandom to get a random value. The standard Java random generator is too slow.
public interface RandomizableEffect {
	float getRandArg0(int tier);
	float getRandArg1(int tier);
	int getRandArg2(int tier);
	int getRandArg3(int tier);
	

}
