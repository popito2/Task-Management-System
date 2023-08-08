package Core.Contracts;

import Models.Member;
import Models.Tasks.Enums.Priority;
import Models.Tasks.Enums.Severity;
import Models.Tasks.Enums.Size;
import Models.Tasks.Enums.Status;
import Models.Tasks.Interfaces.Feedback;
import Models.Tasks.Interfaces.Story;
import Models.Tasks.Interfaces.Task;
import Models.interfaces.Board;
import Models.interfaces.Team;

import java.util.ArrayList;
import java.util.List;

public interface TaskManagementRepository {
    List<Team> getTeams();
    List<Task> getTasks();
    List<Models.interfaces.Member> getMembers();
    Board createNewBoard(String name);
    Models.Tasks.Interfaces.Bug createNewBug(String title, String description, Status status, List<String> stepsToReproduce,
                                             Priority priority, Severity severity, String assignee);
    Models.Tasks.Interfaces.Feedback createNewFeedback(String title, String description, Status status, int rating);
    Models.interfaces.Member createNewPerson(String name);
    Story createNewStory(String title, String description, Priority priority, Size size, Status status, String assignee);
    Team createNewTeam(String name);
    Task findTaskById(int id);
    Team findTeamByName(String name);
    Models.interfaces.Member findMemberByName(String name);
    Board findBoardByName(String name);
}
