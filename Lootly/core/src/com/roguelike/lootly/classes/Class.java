package com.roguelike.lootly.classes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.roguelike.lootly.command.Command;

public class Class {
	
	/* Member Fields*/
	private static String className;
	private static Sprite classSprite;
	private static Command primaryMove;
	private static Command secondaryMove;
	private static Classes classEnum;
	
	/* Constructors */
	public Class() {
	
	}
	
	
	/* Getters and Setters */
	public static String getClassName() {
		return className;
	}
	public static void setClassName(String className) {
		Class.className = className;
	}
	public static Sprite getClassSprite() {
		return classSprite;
	}
	public static void setClassSprite(Sprite classSprite) {
		Class.classSprite = classSprite;
	}
	public static void setClassSprite(Texture classSprite) {
		Class.classSprite = new Sprite(classSprite);
	}
	public static Command getPrimaryMove() {
		return primaryMove;
	}
	public static void setPrimaryMove(Command primaryMove) {
		Class.primaryMove = primaryMove;
	}
	public static Command getSecondaryMove() {
		return secondaryMove;
	}
	public static void setSecondaryMove(Command secondaryMove) {
		Class.secondaryMove = secondaryMove;
	}
	public static Classes getClassEnum() {
		return classEnum;
	}
	public static void setClassEnum(Classes classEnum) {
		Class.classEnum = classEnum;
	}
	
	
	
	
}
