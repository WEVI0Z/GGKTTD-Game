package by.garkaviy.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.useVsync(true);
		config.setTitle("GGKTTD-Game");
		config.setWindowIcon("drop.png");
		// Установка полноэкранного режима
//		config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
		config.setWindowedMode(1500, 1000);
		config.setResizable(false); // Разрешить растягивание окна без изменения разрешения
		new Lwjgl3Application(new GGKTTDGame(), config);
	}
}
