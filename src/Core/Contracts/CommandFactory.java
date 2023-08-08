package Core.Contracts;

import Commands.Contracts.Command;

public interface CommandFactory {
    Command createCommandFromCommandName(String commandName, TaskManagementRepository taskManagementRepository);
}
