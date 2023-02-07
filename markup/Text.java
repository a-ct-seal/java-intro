package markup;

public class Text implements TextElement {
    private final String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public void toMarkdown(StringBuilder out) {
        out.append(text);
    }

    @Override
    public void toTex(StringBuilder out) {
        out.append(text);
    }
}
