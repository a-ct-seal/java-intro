package md2html;

public interface TextElement {
    void toHtml(StringBuilder out);

    void append(TextElement t);
}