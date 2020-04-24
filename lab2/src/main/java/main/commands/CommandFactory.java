package main.commands;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class CommandFactory {
    private Properties factoryProperties = new Properties();
    Logger logger = getLogger("Logger");

    private static volatile CommandFactory instance;

    public static CommandFactory getInstance() {
        if (instance == null)
            synchronized (CommandFactory.class)
            {
                if (instance == null) {
                    instance = new CommandFactory();
                }
            }
        return instance;
    }

    private CommandFactory() {
        try {
            factoryProperties.load(new FileReader(new File("./src/main/resources/config.properties")));
        } catch (IOException e) {
            System.out.println("oops... config.properties was deleted(\n" + e.getLocalizedMessage());
        }
    }


    public Command createCommand(String operationName) {
        Command command;
        try {
            command = (Command) Class.forName("main.commands." + factoryProperties.getProperty(operationName)).getDeclaredConstructor().newInstance();
            logger.info("Operation " + operationName + " was created successfully");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return command;
    }

}
