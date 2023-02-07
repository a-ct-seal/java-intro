package markup;

import java.util.List;

public class Emphasis extends MarkUp {
    protected String mark() {
        return "*";
    }

    protected String texTeg() {
        return "\\emph";
    }

    public Emphasis(List<TextElement> list) {
        super(list);
    }
}
