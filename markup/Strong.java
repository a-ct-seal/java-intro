package markup;

import java.util.List;

public class Strong extends MarkUp {
    protected String mark() {
        return "__";
    }

    protected String texTeg() {
        return "\\textbf";
    }

    public Strong(final List<TextElement> list) {
        super(list);
    }
}
