package me.themallard.bitmmo.launcher;

import java.applet.Applet;
import java.awt.Dimension;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import javax.swing.JOptionPane;

import me.themallard.bitmmo.launcher.gui.GameFrame;

public class BitLauncher {
	public static String CLIENT_PATH = "./gamepack.jar";
	public static String SERVER_ADDR = "core.8bitmmo.net";
	public static String SERVER_PORT = "1337";
	public static String RESOURCE_PACK = "https://s3.amazonaws.com/8BitMMO/HTMudWeb.zip";

	public static URLClassLoader classLoader;
	public static Applet applet;
	public static GameFrame frame;

	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("--gamepack")) {
				CLIENT_PATH = args[i + 1];
			}

			if (args[i].equals("--server")) {
				SERVER_ADDR = args[i + 1];
			}

			if (args[i].equals("--port")) {
				SERVER_PORT = args[i + 1];
			}

			if (args[i].equals("--resource")) {
				RESOURCE_PACK = args[i + 1];
			}

			if (args[i].equals("--help")) {
				System.out.println("--help                = Show this message");
				System.out.println("--gamepack <gamepack> = Select a different gamepack");
				System.out.println("--server <host>       = Select a different server");
				System.out.println("--port <port>         = Select a different port");
				System.out.println("--resource <url>      = Select a different asset pack");
				System.exit(0);
			}
		}

		try {
			frame = new GameFrame();
			File file = new File(CLIENT_PATH); // load up the jar

			if (!file.exists()) {
				JOptionPane.showMessageDialog(null, "Failed to find gamepack.jar", "Whoops!",
						JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}

			classLoader = URLClassLoader.newInstance(new URL[] { file.toURI().toURL() });

			applet = (Applet) classLoader.loadClass("pulpcore.platform.applet.CoreApplet").newInstance();

			GameApplet stub = new GameApplet();
			stub.setDocumentBase(new URL("https://s3.amazonaws.com/8BitMMO/HTMudWeb.jar"));
			stub.setCodeBase(new URL("https://s3.amazonaws.com/8BitMMO/HTMudWeb.jar"));
			stub.addParameter("isWeb", "true");
			stub.addParameter("scene", "HTMud.MainMenu");
			stub.addParameter("assets", RESOURCE_PACK);
			stub.addParameter("wmode", "opaque");
			stub.addParameter("serverName", SERVER_ADDR);
			stub.addParameter("serverPort", SERVER_PORT);

			applet.setStub(stub);
			applet.setPreferredSize(new Dimension(1280, 720));
			frame.addApplet(applet);
			applet.init();
			applet.start();

			frame.pack();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
			return;
		}
	}
}
