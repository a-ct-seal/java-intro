package markup;

import java.util.List;

public abstract class MarkUp implements TextElement {
    protected abstract String mark();

    protected abstract String texTeg();

    private final List<TextElement> list;

    protected MarkUp(List<TextElement> list) {
        this.list = List.copyOf(list);
    }

    @Override
    public void toMarkdown(StringBuilder out) {
        out.append(mark());
        for (TextElement el : list) {
            el.toMarkdown(out);
        }
        out.append(mark());
    }

    @Override
    public void toTex(StringBuilder out) {
        out.append(texTeg()).append("{");
        for (TextElement el : list) {
            el.toTex(out);
        }
        out.append("}");
    }
}
