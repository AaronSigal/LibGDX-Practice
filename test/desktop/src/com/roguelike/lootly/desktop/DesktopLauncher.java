package com.roguelike.lootly.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.roguelike.lootly.Lootly;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Lootly";
	    config.width = 800;
	    config.height = 480;
	    config.samples = 8;
		new LwjglApplication(new Lootly(), config);
	}
}
