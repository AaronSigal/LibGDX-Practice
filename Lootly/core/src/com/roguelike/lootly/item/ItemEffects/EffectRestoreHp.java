package com.roguelike.lootly.item.ItemEffects;

import com.roguelike.lootly.item.ConfigurableEffect;
import com.roguelike.lootly.item.EntityEffect;

import mob.MortalEntity;

public class EffectRestoreHp extends EntityEffect implements ConfigurableEffect {
	int restoreAmount;
	
	public EffectRestoreHp() {
		setId(0);
		restoreAmount = 100;
	}
	
	//ignore all arguments except args2. We don't use args0 or args1 since they are reserved for float values.
	@Override
	public void configure(float args0, float args1, int args2, int args3) {
		restoreAmount = args2;
	}
	
	@Override
	public void perform(MortalEntity targetEntity) {
		targetEntity.setHP(restoreAmount);
	}

}
