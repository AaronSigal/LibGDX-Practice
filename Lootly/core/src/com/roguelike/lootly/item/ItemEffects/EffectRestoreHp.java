package com.roguelike.lootly.item.ItemEffects;

import java.util.concurrent.ThreadLocalRandom;

import mob.MortalEntity;

public class EffectRestoreHp extends EntityEffect implements ConfigurableEffect {
	int restoreAmount;
	
	public EffectRestoreHp() {
		setId(0);
		restoreAmount = 100;
	}
	
	//ignore all arguments except args2. We don't use args0 or args1 since they are reserved for float values.
	@Override
	public void configure(float arg0, float arg1, int arg2, int arg3) {
		restoreAmount = arg2;
	}
	
	@Override
	public void perform(MortalEntity targetEntity) {
		targetEntity.setHP(restoreAmount);
	}

	//Arg0 isn't used. Return 0.
	@Override
	public float getRandArg0(int tier) {
		
		return 0;
	}

	//Arg1 isn't used. Return 0.
	@Override
	public float getRandArg1(int tier) {
		
		return 0;
	}

	
	@Override
	public int getRandArg2(int tier) {
		 switch (tier) {
		 		case 1: //tier 1: min 1 hp, max 35 hp
		  		return ThreadLocalRandom.current().nextInt(1,35);
		  		
		  		case 2:
		  		return ThreadLocalRandom.current().nextInt(20, 45);
		  		
		  		case 3:
		  		return ThreadLocalRandom.current().nextInt(35, 50);
		  		
		  		case 4:
		  		return ThreadLocalRandom.current().nextInt(50, 100);
		  			
		  		case 5:
		 		return ThreadLocalRandom.current().nextInt(150, 200);
		  			
		  		default:
		  		return 10;
		 }
	}
	
	//Arg3 isn't used. Return 0.
	@Override
	public int getRandArg3(int tier) {
		
		return 0;
	}

}
