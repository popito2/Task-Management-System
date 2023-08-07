package Models.interfaces;

import java.util.List;

public interface Team{
    String getName();
    List<Member> getMembers();
    List<Models.interfaces.Board> getBoards();
}
