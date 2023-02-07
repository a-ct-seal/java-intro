package md2html;

import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Objects;

abstract class TextBlock {

    private static final char BACKSLASH = '\\';
    private static final char CLOSING_LINK_TAG = ')';

    protected List<TextElement> text;

    public TextBlock(String text) {
        this.text = parseText(text);
    }

    public abstract String toHtml();

    protected String toHtml(String tag) {
        StringBuilder resHtmlText = new StringBuilder();
        resHtmlText.append(TagBuilder.buildHtmlTag(tag, true));
        for (TextElement i : text) {
            i.toHtml(resHtmlText);
        }
        resHtmlText.append(TagBuilder.buildHtmlTag(tag, false)).append(System.lineSeparator());
        return resHtmlText.toString();
    }

    private List<TextElement> parseText(String text) {
        List<TextElement> textBlock = new ArrayList<>();
        Deque<TextElement> textBlockStack = new ArrayDeque<>();
        Deque<String> servSymbolsStack = new ArrayDeque<>();
        StringBuilder newText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            String symb = Character.toString(text.charAt(i));
            if (i != text.length() - 1 && SpecialSymbols.isServSymbol(symb + text.charAt(i + 1))) {
                symb += text.charAt(++i);
            }
            if (SpecialSymbols.isServSymbol(symb) && !checkSingleSymbol(symb, text, i)) {
                i = processSpecialSymbol(i, text, symb, newText, textBlock, textBlockStack, servSymbolsStack);
            } else {
                if (text.charAt(i) == BACKSLASH) {
                    newText.append(text.charAt(++i));
                } else if (SpecialSymbols.isEscapeSymbol(symb)) {
                    newText.append(SpecialSymbols.getEscapeSymbol(symb));
                } else {
                    newText.append(symb);
                }
            }
        }
        if (!newText.isEmpty()) {
            textBlockStack.add(new Text(newText.toString()));
        }
        textBlock.addAll(textBlockStack);
        return textBlock;
    }

    private boolean checkSingleSymbol(String symbol, String text, int i) {
        return (SpecialSymbols.isSingleSymbol(symbol) &&
                (i == 0 || Character.isWhitespace(text.charAt(i - 1))) &&
                (i == text.length() - 1 || Character.isWhitespace(text.charAt(i + 1))));
    }

    private int processSpecialSymbol(int idx, String text, String symbol, StringBuilder builderOfText,
                                     List<TextElement> textBlock, Deque<TextElement> textBlockStack,
                                     Deque<String> servSymbolsStack) {
        Text newText = new Text(builderOfText.toString());
        builderOfText.setLength(0);
        if (servSymbolsStack.isEmpty()) {
            textBlock.add(newText);
            addToStack(symbol, textBlockStack, servSymbolsStack);
        } else if (Objects.equals(servSymbolsStack.peekLast(), symbol)) {
            TextElement lastElem = removeFromStack(newText, textBlockStack, servSymbolsStack);
            addToBlock(lastElem, textBlockStack, textBlock);
        } else if (SpecialSymbols.isLinkSymbol(servSymbolsStack.peekLast()) &&
                SpecialSymbols.isLinkSymbol(symbol)) {
            Link lastElem = (Link) removeFromStack(newText, textBlockStack, servSymbolsStack);
            idx = processLink(idx, text, lastElem);
            addToBlock(lastElem, textBlockStack, textBlock);
        } else {
            textBlockStack.peekLast().append(newText); // peekLast never returns null in this situation
            addToStack(symbol, textBlockStack, servSymbolsStack);
        }
        return idx;
    }

    private int processLink(int idx, String text, Link link) {
        StringBuilder href = new StringBuilder();
        idx += 2;
        while (text.charAt(idx) != CLOSING_LINK_TAG) {
            href.append(text.charAt(idx));
            idx++;
        }
        link.addHref(new Text(href.toString()));
        return idx;
    }

    private void addToStack(String symbol, Deque<TextElement> textBlockStack, Deque<String> servSymbolsStack) {
        servSymbolsStack.add(symbol);
        textBlockStack.add(SpecialSymbols.buildTextElem(symbol));
    }

    private TextElement removeFromStack(Text newText, Deque<TextElement> textBlockStack, Deque<String> servSymbolsStack) {
        servSymbolsStack.pollLast();
        TextElement lastElem = textBlockStack.pollLast(); // pollLast never returns null in this situation
        lastElem.append(newText);
        return lastElem;
    }

    private void addToBlock(TextElement lastElem, Deque<TextElement> textBlockStack, List<TextElement> textBlock) {
        if (!textBlockStack.isEmpty() && !(textBlockStack.peekLast() instanceof Text)) {
            textBlockStack.peekLast().append(lastElem);
        } else {
            textBlock.add(lastElem);
        }
    }
}
