package com.roguelike.lootly.gui;

//TODO: Refactor this class and all the other strategy classes to share one abstract parent class.

//A simple abstract Strategy class. This is intended to be used as an anonymous inner class in the ToggleButton class. It can, however, be adapter in all sorts of ways.
abstract public class MenuStrategy {
	public abstract void execute();
}
