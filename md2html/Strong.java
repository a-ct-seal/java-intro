package md2html;

import java.util.List;

public class Strong extends MarkUp {
    private static final String TAG = "strong";

    public Strong(List<TextElement> text) {
        super(text);
    }

    @Override
    protected String htmlTag() {
        return TAG;
    }
}
