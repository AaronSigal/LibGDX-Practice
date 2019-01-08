package com.roguelike.lootly.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.roguelike.lootly.Lootly;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Lootly";
	    config.width = 1920;
	    config.height = 1080;
	    config.samples = 8;
		new LwjglApplication(new Lootly(), config);
	}
}
