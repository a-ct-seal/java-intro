package markup;

import java.util.List;

public class Strikeout extends MarkUp {
    protected String mark() {
        return "~";
    }

    protected String texTeg() {
        return "\\textst";
    }

    public Strikeout(List<TextElement> list) {
        super(list);
    }
}
