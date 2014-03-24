package pwm;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.imageio.ImageIO;

/**
 * Manages the assets of the Passwort Manager
 *
 * @author Dominik Scholz
 * @version 0.3
 */
public class Assets {

    public final static String DATA_PATH = "/data/";
    public final static String FILE_DEFAULT_SETTINGS = DATA_PATH + "settings_default";
    public final static String FILE_SETTINGS = DATA_PATH + "settings";
    public final static String FILE_LANG_EN = DATA_PATH + "lang_en";
    public final static String FILE_LANG_DE = DATA_PATH + "lang_de";
    public final static String FILE_ICON = DATA_PATH + "icon.png";

    private Properties defaultSettings;
    private Properties settings;
    private Properties language;
    private Image icon;

    private static Assets instance = new Assets();

    private Assets() {

        //loading the Properties files
        defaultSettings = loadProperties(FILE_DEFAULT_SETTINGS);
        settings = loadProperties(FILE_SETTINGS);
        language = loadProperties(DATA_PATH + "lang_" + settings.getProperty("lang"));
        if (language.isEmpty()) {
            language = loadProperties(DATA_PATH + "lang_" + defaultSettings.getProperty("lang"));
        }

        //loading the Images
        icon = loadImage(FILE_ICON);
    }

    public static Assets get() {
        return instance;
    }

    public Image getIcon() {
        return icon;
    }

    /**
     * Loads a single Properties file, from the given path
     *
     * @param path the path from which the properties file should be loaded
     * @return the loaded properties file
     */
    public Properties loadProperties(String path) {
        Properties prop = new Properties();
        try (InputStream is = getClass().getResourceAsStream(path + ".properties")) {
            if (is != null) {
                prop.load(is);
            }
        } catch (IOException ex) {
        }
        return prop;
    }

    /**
     * Loads a single Properties file, from the given path with applying the given default properties
     *
     * @param path the path from which the Properties file should be loaded
     * @param defaultProperties the Properties file, where the default values are locaded
     * @return the loaded properties file
     */
    public Properties loadProperties(String path, Properties defaultProperties) {
        Properties prop = new Properties(defaultProperties);
        try (InputStream is = getClass().getResourceAsStream(path + ".properties")) {
            if (is != null) {
                prop.load(is);
            }
        } catch (IOException ex) {
        }
        return prop;
    }

    /**
     * Returns the setting to the given key
     *
     * @param key the key to get the value from
     * @return the value to the given key
     */
    public String getSetting(String key) {
        return settings.getProperty(key);
    }

    /**
     * Returns the setting to the given key
     *
     * @param key the key to get the value from
     * @param defaultValue the defaultValue for when the key isn't found
     * @return the value to the given key
     */
    public String getSetting(String key, String defaultValue) {
        return settings.getProperty(key, defaultValue);
    }

    /**
     * Returns the integer value of a setting to the given key
     *
     * @param key the key to get the value from
     * @return the value to the given key as integer if it is an integer, if not Integer.MIN_VALUE
     */
    public int getIntSetting(String key) {
        int number = Integer.MIN_VALUE;
        try {
            number = Integer.parseInt(settings.getProperty(key));
        } catch (NumberFormatException ex) {
            System.out.println(key);
            System.out.println(language.stringPropertyNames());
            ex.printStackTrace();
        }
        return number;
    }

    /**
     * Returns the localized String to the given key
     *
     * @param key the key to get the value from
     * @return the value to the given key
     */
    public String getLocalized(String key) {
        return language.getProperty(key);
    }

    /**
     * Returns the localized String to the given key
     *
     * @param key the key to get the value from
     * @param defaultValue the defaultValue for when the key isn't found
     * @return the value to the given key
     */
    public String getLocalized(String key, String defaultValue) {
        return language.getProperty(key, defaultValue);
    }

    /**
     * Loads an image
     *
     * @param path the path from the image
     * @return the image or null if the image wasn't found
     */
    public BufferedImage loadImage(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResource(path));
        } catch (IOException ex) {
        }
        return image;
    }
}
