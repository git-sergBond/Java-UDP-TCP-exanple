package client;

public class SettingsUtil {

    private static final String LOCAL_HOST = "localhost";

    public static String readHostFromCommandLineArguments(String[] commandLineArguments) {
        if (commandLineArguments.length < 1) {
            return LOCAL_HOST;
        }

        if (commandLineArguments[0] == null) {
            return LOCAL_HOST;
        }

        if (commandLineArguments[0].isEmpty()) {
            return LOCAL_HOST;
        }

        return commandLineArguments[0];
    }
}
