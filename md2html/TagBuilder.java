package md2html;

public class TagBuilder {
    private static final String START_OF_TAG = "<";
    private static final String END_OF_TAG = ">";
    private static final String END_TAG_SYMBOL = "/";

    static String buildHtmlTag(String tag, boolean isStartTag) {
        StringBuilder htmlTag = new StringBuilder();
        htmlTag.append(START_OF_TAG);
        if (!isStartTag) {
            htmlTag.append(END_TAG_SYMBOL);
        }
        htmlTag.append(tag).append(END_OF_TAG);
        return htmlTag.toString();
    }

    static String buildHtmlLinkTag(Text href, boolean isStartTag,
                                   String startOfLinkTag, String endOfLinkTag, String linkTag) {
        StringBuilder htmlTag = new StringBuilder();
        htmlTag.append(START_OF_TAG);
        if (isStartTag) {
            htmlTag.append(startOfLinkTag);
            href.toHtml(htmlTag);
            htmlTag.append(endOfLinkTag);
        } else {
            htmlTag.append(END_TAG_SYMBOL).append(linkTag);
        }
        htmlTag.append(END_OF_TAG);
        return htmlTag.toString();
    }
}
