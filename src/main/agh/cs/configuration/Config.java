package agh.cs.configuration;

/**
 * Singleton responsible for holding information about simulation parameters.
 */
public class Config {
    private static Config instance;

    public final Params params;

    private Config() {
        this.params = ResourceParser.parseParams();
    }

    public static Config getInstance() {
        if (instance == null)
            instance = new Config();

        return instance;
    }
}
