package markup;

import java.util.List;

public abstract class TeXList implements ListElement {
    protected abstract String texEnv();

    private final List<ListItem> list;

    protected TeXList(List<ListItem> list) {
        this.list = List.copyOf(list);
    }

    private String buildStart() {
        return "\\begin{" + texEnv() + "}";
    }

    private String buildEnd() {
        return "\\end{" + texEnv() + "}";
    }

    @Override
    public void toTex(StringBuilder out) {
        out.append(buildStart());
        for (ListItem el : list) {
            el.toTex(out);
        }
        out.append(buildEnd());
    }

    @Override
    public void toMarkdown(StringBuilder out) {
    }
}
