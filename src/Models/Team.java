package Models;

import Models.interfaces.Board;
import Models.interfaces.Member;
import Utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class Team implements Models.interfaces.Team {
    public static final int MIN_NAME_VALUE = 5;
    public static final int MAX_NAME_VALUE = 15;
    public static final String NAME_ERROR_LENGTH = "Name must be between 5 and 15 symbols.";
    String name;
    private List<Models.interfaces.Member> members;
    private List<Models.interfaces.Board> boards;

    public Team(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        ValidationHelpers.validateIntRange(name.length(), MIN_NAME_VALUE, MAX_NAME_VALUE, NAME_ERROR_LENGTH);
        this.name = name;
    }


    public List<Models.interfaces.Member> getMembers() {
        return members;
    }

    public List<Models.interfaces.Board> getBoards() {
        return boards;
    }

    public void addMember(Models.interfaces.Member member){
        this.members.add(member);
    }
    public void removeMember(Member member){
        this.members.remove(member);
    }
    public void addBoard(Models.interfaces.Board board){
        this.boards.add(board);
    }
    public void removeBoard(Board board){
        this.boards.remove(board);
    }
    public List<List<String>> getHistory(){
        List<List<String>>teamHistory=new ArrayList<>();
        for (Member member:members) {
            teamHistory.add(member.getHistoryChanges());

        }
        return teamHistory;
    }
}
