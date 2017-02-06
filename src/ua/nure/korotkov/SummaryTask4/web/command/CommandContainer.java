package ua.nure.korotkov.SummaryTask4.web.command;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Андрей on 11.01.2017.
 */
public class CommandContainer {
    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        // common commands
        commands.put("start", new StartCommand());//start
        commands.put("TotalListEditions", new TotalListEditionsCommand());
        commands.put("login", new LoginCommand());//вход
        commands.put("AdminListEditions", new AdminListEditionsCommand());
        commands.put("UserListEditions", new UserListEditionsCommand());
        commands.put("AllUsers", new AllUsersCommand());
        commands.put("Settings", new ViewSettingsCommand());
        commands.put("AddNewEdition", new AddNewEditionCommand());
        commands.put("addEdition", new AddEditionCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("noCommand", new NoCommand());
        commands.put("singOn", new SingOnCommand());
        commands.put("Locked", new LockedCommand());
        commands.put("NewPrivilege", new NewPrivilegeCommand());
        commands.put("UpdateEdition", new UpdateEditionCommand());
        commands.put("updateEditionForm", new UpdateEditionFormCommand());
        commands.put("DeleteEdition", new DeleteEditionCommand());
        commands.put("AllSubscriptions", new AllSubscriptionsCommand());
        commands.put("UserSubscriptions", new UserSubscriptionsCommand());
        commands.put("UserAccount", new UserAccountCommand());
        commands.put("UserRefill", new UserRefillCommand());
        commands.put("UserRefillForm", new UserRefillFormCommand());
        commands.put("Subscribe", new SubscribeCommand());
        commands.put("SortByTopic", new SortByTopicCommand());
        commands.put("SortByPrice", new SortByPriceCommand());
        commands.put("updateSettings", new UpdateSettingsCommand());
        commands.put("Language", new LanguageCommand());
        commands.put("PDF", new PDFCommand());
        commands.put("SortByName", new SortByNameCommand());


        LOG.debug("Command container was successfully initialized");
        LOG.trace("Number of commands --> " + commands.size());
    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName
     *            Name of the command.
     * @return Command object.
     */
    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }
}
