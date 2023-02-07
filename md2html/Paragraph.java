package md2html;

public class Paragraph extends TextBlock {
    private static final String PARAGRAPH_TAG = "p";

    public Paragraph(String text) {
        super(text);
    }

    @Override
    public String toHtml() {
        return toHtml(PARAGRAPH_TAG);
    }
}
