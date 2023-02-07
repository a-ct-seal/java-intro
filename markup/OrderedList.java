package markup;

import java.util.List;

public class OrderedList extends TeXList {
    protected String texEnv() {
        return "enumerate";
    }

    public OrderedList(final List<ListItem> list) {
        super(list);
    }
}
