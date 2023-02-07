package md2html;

public class Header extends TextBlock {
    private static final String HEADER_TAG = "h";

    private final int level;

    public static final int MAX_HEADER_SIZE = 6;

    public Header(String text, int level) {
        super(text);
        this.level = level;
    }

    @Override
    public String toHtml() {
        return toHtml(HEADER_TAG + level);
    }
}
