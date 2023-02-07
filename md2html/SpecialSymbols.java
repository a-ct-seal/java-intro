package md2html;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class SpecialSymbols {

    private static final Set<String> SERV_SYMBOLS = Set.of(
            "*", "_", "**", "__", "--", "`", "[", "]"
    );

    private static final Map<String, String> ESCAPED_SYMBOLS = Map.of(
            "<", "&lt;",
            ">", "&gt;",
            "&", "&amp;"
    );

    private static final Set<String> SINGLE_SYMBOLS = Set.of(
            "*", "_"
    );

    static TextElement buildTextElem(String tag) {
        List<TextElement> text = new ArrayList<>();
        return switch (tag) {
            case "*", "_" -> new Emphasis(text);
            case "**", "__" -> new Strong(text);
            case "--" -> new Strikeout(text);
            case "`" -> new Code(text);
            case "[" -> new Link(text);
            default -> null;
        };
    }

    private static final Set<String> LINK_SYMBOLS = Set.of(
            "[", "]"
    );

    static boolean isSingleSymbol(String s) {
        return SINGLE_SYMBOLS.contains(s);
    }

    static boolean isServSymbol(String a) {
        return SERV_SYMBOLS.contains(a);
    }

    static boolean isEscapeSymbol(String a) {
        return ESCAPED_SYMBOLS.containsKey(a);
    }

    static boolean isLinkSymbol(String a) {
        return LINK_SYMBOLS.contains(a);
    }

    static String getEscapeSymbol(String a) {
        return ESCAPED_SYMBOLS.get(a);
    }
}
