package md2html;

public class Text implements TextElement {
    private final String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public void toHtml(StringBuilder out) {
        out.append(text);
    }

    @Override
    public void append(TextElement t) {
    }
}
