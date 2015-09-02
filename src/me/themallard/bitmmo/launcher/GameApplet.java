package me.themallard.bitmmo.launcher;

import java.applet.AppletContext;
import java.applet.AppletStub;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GameApplet implements AppletStub {
	private URL documentBase;
	private URL codeBase;
	private static Map<String, String> parameters = new HashMap<String, String>();

	@Override
	public boolean isActive() {
		return false;
	}

	public void setDocumentBase(URL documentBase) {
		this.documentBase = documentBase;
	}

	public void setCodeBase(URL codeBase) {
		this.codeBase = codeBase;
	}

	public void addParameter(String name, String value) {
		parameters.put(name, value);
	}

	@Override
	public URL getDocumentBase() {
		return documentBase;
	}

	@Override
	public URL getCodeBase() {
		return codeBase;
	}

	@Override
	public String getParameter(String name) {
		return parameters.get(name);
	}

	@Override
	public AppletContext getAppletContext() {
		return null;
	}

	@Override
	public void appletResize(int width, int height) {

	}
}