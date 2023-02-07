package markup;

import java.util.List;

public class ListItem implements Markable {
    private static final String texTeg = "\\item";
    private final List<ListElement> children;

    public ListItem(List<ListElement> children) {
        this.children = List.copyOf(children);
    }

    @Override
    public void toTex(StringBuilder out) {
        out.append(texTeg + " ");
        for (ListElement child : children) {
            child.toTex(out);
        }
    }

    @Override
    public void toMarkdown(StringBuilder out) {
    }
}
