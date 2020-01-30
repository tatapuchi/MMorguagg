package com.vibhor.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vibhor.game.MMORG;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = MMORG.WIDTH;
		config.height = MMORG.HEIGHT;
		config.title = MMORG.TITLE;

		new LwjglApplication(new MMORG(), config);
	}
}
