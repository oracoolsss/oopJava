package main.commands;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class CommandFactory {
    private Properties factoryProperties = new Properties();
    private Logger logger = getLogger(CommandFactory.class.getName());

    private static volatile CommandFactory instance;

    public static CommandFactory getInstance() throws IOException {
        if (instance == null)
            synchronized (CommandFactory.class)
            {
                if (instance == null) {
                    instance = new CommandFactory();
                }
            }
        return instance;
    }

    private CommandFactory() throws IOException {
            InputStream is = getClass().getResourceAsStream("/config.properties");
            if(is == null) {
                logger.info("Exception: config.properties not found");
                throw new NullPointerException();
            }
            factoryProperties.load(is);
    }


    public Command createCommand(String operationName) {
        Command command;
        try {
            command = (Command) Class.forName(factoryProperties.getProperty(operationName)).getDeclaredConstructor().newInstance();
            logger.info("Operation " + operationName + " was created successfully");
        }
        catch (Exception e) {
            logger.info("Exception: problems with command creating");
            return null;
        }

        return command;
    }

}
