package Models.interfaces;

import java.util.List;

public interface Board extends Editor {
    String getName();

    List<String> getHistory();
}

