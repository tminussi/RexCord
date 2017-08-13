package utils;

import rexcord.RexCord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Reads config file
 */
public class ConfigReader {

    /**
     * Configuration's File Path
     */
    private static final String DEFAULT_CONFIG_PATH
            = System.getProperty("user.dir") + "/config/config.cfg";

    /**
     * Print this message when a field definition is empty
     */
    private static final String MISSING_DEFINITION =
            "Error while loading config.cfg\n[Line %d] Missing %s definition";

    /**
     * Config missing Error Message
     */
    private static final String CONFIG_NOT_FOUND_ERROR
            = "RexCord: Config file not found. "
            + "Make sure it is created and located in "
            + "the correct directory.";

    /**
     * Creates an instance of ConfigReader
     *
     * @throws FileNotFoundException in case doesnt find config file
     */
    public ConfigReader() throws FileNotFoundException {
        readFile();
    }

    /**
     * Reads config file and assigns each parameter value
     *
     * @throws FileNotFoundException if config file is not found
     */
    private void readFile() throws FileNotFoundException {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(DEFAULT_CONFIG_PATH));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(CONFIG_NOT_FOUND_ERROR);
        }

        int lineNumber = 0;

        while (sc.hasNext()) {
            String currentLine = sc.nextLine();
            lineNumber++;

            if (!currentLine.trim().equals("")
                    && !currentLine.startsWith(RexCord.CONFIG_COMMENT)) {

                String[] lineSplitted = currentLine.split("=");
                String parameter = lineSplitted[0].trim();
                String option = null;
                try {
                    option = lineSplitted[1].trim();
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new ArrayIndexOutOfBoundsException(
                            String.format(MISSING_DEFINITION,
                                    lineNumber, parameter));
                }

                switch (parameter) { //config parameters should be handled here
                    case "token":
                        RexCord.setBotToken(option);
                        break;
                    case "banned_commands":
                        RexCord.setBotBannedCommands(option);
                    case "prefix":
                        RexCord.setBotPrefix(option);
                        break;
                    default:
                        break;
                }
            }
        }

        sc.close();
    }
}
