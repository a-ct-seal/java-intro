package md2html;

import java.util.List;

public class Link extends MarkUp {
    private static final String START_OF_LINK_TAG = "a href='";
    private static final String END_OF_LINK_TAG = "'";
    private static final String LINK_TAG = "a";

    private Text href = new Text("");

    public Link(List<TextElement> text) {
        super(text);
    }

    public void addHref(Text href) {
        this.href = href;
    }

    @Override
    protected String htmlTag() {
        return "";
    }

    @Override
    public void toHtml(StringBuilder out) {
        out.append(TagBuilder.buildHtmlLinkTag(href, true,
                START_OF_LINK_TAG, END_OF_LINK_TAG, LINK_TAG));
        for (TextElement el : text) {
            el.toHtml(out);
        }
        out.append(TagBuilder.buildHtmlLinkTag(href, false,
                START_OF_LINK_TAG, END_OF_LINK_TAG, LINK_TAG));
    }
}
