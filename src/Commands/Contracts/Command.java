package Commands.Contracts;

import java.util.List;

public interface Command {
    String execute(List<String> parameters);

}
