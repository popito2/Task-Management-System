package Models.interfaces;

import java.util.List;

public interface Team{
    String getName();
    List<Member> getMembers();
    List<Models.interfaces.Board> getBoards();
    void addMember(Member member);
    void removeMember(Member member);
    void addBoard(Models.interfaces.Board board);
    void removeBoard(Board board);
     List<List<String>> getHistory();

}
