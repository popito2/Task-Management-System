package Core;

import Commands.*;
import Commands.Contracts.Command;
import Commands.Enums.CommandType;
import Core.Contracts.TaskManagementRepository;
import Utils.ParsingHelpers;

public class CommandFactory implements Core.Contracts.CommandFactory {

    private static final String INVALID_COMMAND = "Invalid command name: %s!";

    public Command createCommandFromCommandName(String commandName, TaskManagementRepository taskManagementRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandName, CommandType.class);
        switch (commandType){
            case CREATENEWBOARDINATEAM:
                return new CreateANewBoardInATeam(taskManagementRepository);
            case CREATENEWBUG:
                return new CreateNewBug(taskManagementRepository);
            case CREATENEWFEEDBACK:
                return new CreateNewFeedback(taskManagementRepository);
            case CREATENEWPERSON:
                return new CreateNewPerson(taskManagementRepository);
            case CREATENEWSTORY:
                return new CreateNewStory(taskManagementRepository);
            case CREATENEWTEAM:
                return new CreateNewTeam(taskManagementRepository);
            case ADDPERSONTOTEAM:
                return new AddPersonToTeam(taskManagementRepository);
            case ASSIGNATASKTOAPERSON:
                return new AssignATaskToAPerson(taskManagementRepository);
            case SHOWALLPEOPLE:
                return new ShowAllPeople(taskManagementRepository);
            case SHOWALLTEAMS:
                return new ShowAllTeams(taskManagementRepository);
            case SHOWALLTHETEAMMEMBERS:
                return new ShowAllTheTeamMembers(taskManagementRepository);
            case SHOWBOARDSACTIVITY:
                return new ShowBoardsActivity(taskManagementRepository);
            case SHOWPERSONACTIVITY:
                return new ShowPersonsActivity(taskManagementRepository);
            case SHOWTEAMSACTIVITY:
                return new ShowTeamsActivity(taskManagementRepository);
            case SHOWWALLTEAMBOARDS:
                return new ShowAllTeamBoards(taskManagementRepository);
            default:
                throw new IllegalArgumentException("Command you tried to use is non existend");
        }
    }

}
