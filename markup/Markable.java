package markup;

public interface Markable {
    void toMarkdown(StringBuilder out);

    void toTex(StringBuilder out);
}
