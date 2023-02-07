package md2html;

import java.util.List;

public class Strikeout extends MarkUp {
    private static final String TAG = "s";

    public Strikeout(List<TextElement> text) {
        super(text);
    }

    @Override
    protected String htmlTag() {
        return TAG;
    }
}
