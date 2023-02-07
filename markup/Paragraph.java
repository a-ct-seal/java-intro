package markup;

import java.util.List;

public class Paragraph implements ListElement {
    private final List<TextElement> list;

    public Paragraph(List<TextElement> list) {
        this.list = List.copyOf(list);
    }

    @Override
    public void toMarkdown(StringBuilder out) {
        for (TextElement el : list) {
            el.toMarkdown(out);
        }
    }

    @Override
    public void toTex(StringBuilder out) {
        for (TextElement el : list) {
            el.toTex(out);
        }
    }
}
