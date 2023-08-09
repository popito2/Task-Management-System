package Utils;

import java.util.ArrayList;
import java.util.List;

public class ListingHelpers {

    public static <T> String elementsToString(List<T> elements) {
        List<String> result = new ArrayList<>();
        for (T element : elements) {
            result.add(element.toString());
        }

        return result.toString();
    }

}
