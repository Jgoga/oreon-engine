package org.oreon.core.context;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.oreon.core.math.Quaternion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Configuration {
	
	// render parameters
	private boolean wireframe;
	private boolean underwater;
	private boolean reflection;
	private boolean refraction;
	
	// post processing filter
	private boolean bloomEnabled;
	private boolean dephtOfFieldEnabled;
	
	// render settings
	private float sightRange;
	private Quaternion clipplane;
	
	// screen settings
	private int multisamples;
	private int x_ScreenResolution;
	private int y_ScreenResolution;

	// window settings
	private String displayTitle;
	private int windowWidth;
	private int windowHeight;
	
	private Configuration(){
		
		Properties properties = new Properties();
		try {
			InputStream stream = Configuration.class.getClassLoader().getResourceAsStream("render-config.properties");
			properties.load(stream);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		multisamples = Integer.valueOf(properties.getProperty("multisamples"));
		windowWidth = Integer.valueOf(properties.getProperty("display.width"));
		windowHeight = Integer.valueOf(properties.getProperty("display.height"));
		displayTitle = properties.getProperty("display.title");
		x_ScreenResolution = Integer.valueOf(properties.getProperty("screen.resolution.x"));
		y_ScreenResolution = Integer.valueOf(properties.getProperty("screen.resolution.y"));
		
	}
}
