package com.ezchen.Gust;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = Gust.TITLE;
		cfg.useGL20 = false;
		cfg.width = Gust.V_WIDTH * Gust.SCALE;
		cfg.height = Gust.V_HEIGHT * Gust.SCALE;
		
		new LwjglApplication(new Gust(), cfg);
	}
}
