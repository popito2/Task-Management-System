package Core;

import Models.Member;
import Models.Tasks.Bug;
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

public class TaskManagementRepository implements Core.Contracts.TaskManagementRepository {
    public static final String THE_TEAM_ALREADY_EXISTS = "The team already exists.";
    private int nextId;
    private List<Team> teams = new ArrayList<Team>();
    private List<Task> tasks = new ArrayList<Task>();
    private List<Board> boards = new ArrayList<Board>();

    private List<Models.interfaces.Member> members = new ArrayList<Models.interfaces.Member>();

    public TaskManagementRepository() {
        nextId = 0;
    }

    public List<Team> getTeams() {
        return new ArrayList<>(teams);
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public List<Models.interfaces.Member> getMembers() {
        return new ArrayList<>(members);
    }

    public Board createNewBoard(String name) {
        return new Models.Board(name);
    }

    public Models.Tasks.Interfaces.Bug createNewBug(String title, String description, Status status, List<String> stepsToReproduce,
                                                    Priority priority, Severity severity, String assignee) {
        Models.Tasks.Interfaces.Bug bug = new Bug(++nextId, title, description, status, stepsToReproduce, priority, severity, assignee);
        tasks.add(bug);
        return bug;
    }

    public Models.Tasks.Interfaces.Feedback createNewFeedback(String title, String description, Status status, int rating) {
        Feedback feedback = new Models.Tasks.Feedback(++nextId, title, description, status, rating);
        tasks.add(feedback);
        return feedback;

    }

    public Models.interfaces.Member createNewPerson(String name) {
        Models.interfaces.Member member = new Member(name);
        members.add(member);
        return member;
    }

    public Story createNewStory(String title, String description, Priority priority, Size size, Status status, String assignee) {
        Story story = new Models.Tasks.Story(++nextId, title, description, priority, size, status, assignee);
        tasks.add(story);
        return story;
    }

    public Team createNewTeam(String name) {

        Team team = new Models.Team(name);
        if (teams.contains(team)) {
            throw new IllegalArgumentException(THE_TEAM_ALREADY_EXISTS);
        }
        teams.add(team);
        return team;
    }

    public Task findTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        throw new IllegalArgumentException("Task not found.");
    }

    public Team findTeamByName(String name) {
        for (Team team : teams) {
            if (team.getName().equals(name)) {
                return team;
            }
        }
        throw new IllegalArgumentException("Team not found.");
    }

    public Models.interfaces.Member findMemberByName(String name){
        for (Models.interfaces.Member member : members){
            if(member.getName().equals(name)){
                return member;
            }
        }
        throw new IllegalArgumentException("Member not found");
    }

    public Board findBoardByName(String name){
        for (Board board : boards){
            if(board.getName().equals(name)){
                return board;
            }
        }
        throw new IllegalArgumentException("Board not found");
    }

}
