package markup;

import java.util.List;

public class UnorderedList extends TeXList {
    public UnorderedList(final List<ListItem> list) {
        super(list);
    }

    protected String texEnv() {
        return "itemize";
    }
}
