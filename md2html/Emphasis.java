package md2html;

import java.util.List;

public class Emphasis extends MarkUp {
    private static final String TAG = "em";

    public Emphasis(List<TextElement> text) {
        super(text);
    }

    @Override
    protected String htmlTag() {
        return TAG;
    }
}