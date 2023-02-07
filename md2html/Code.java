package md2html;

import java.util.List;

public class Code extends MarkUp {
    private static final String TAG = "code";

    public Code(List<TextElement> text) {
        super(text);
    }

    @Override
    protected String htmlTag() {
        return TAG;
    }
}