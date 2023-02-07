package md2html;

import java.util.ArrayList;
import java.util.List;

public abstract class MarkUp implements TextElement {
    protected abstract String htmlTag();

    protected final List<TextElement> text = new ArrayList<>();

    protected MarkUp(List<TextElement> text) {
        this.text.addAll(text);
    }

    @Override
    public void toHtml(StringBuilder out) {
        out.append(TagBuilder.buildHtmlTag(htmlTag(), true));
        for (TextElement el : text) {
            el.toHtml(out);
        }
        out.append(TagBuilder.buildHtmlTag(htmlTag(), false));
    }

    @Override
    public void append(TextElement t) {
        text.add(t);
    }
}
