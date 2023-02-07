package md2html;

import java.io.Writer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;

public class Md2Html {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int LINE_SEPARATOR_SIZE = LINE_SEPARATOR.length();

    private static TextBlock toTextBlock(String text) {
        text = text.substring(0, text.length() - LINE_SEPARATOR_SIZE);
        int i = 0;
        while (i < text.length() && text.charAt(i) == '#' && i < Header.MAX_HEADER_SIZE) {
            i++;
        }
        if (0 < i && i < text.length() && Character.isWhitespace(text.charAt(i)) || i == text.length()) {
            return new Header(text.substring(i + 1), i);
        }
        return new Paragraph(text);
    }

    private static void writeHtmlTextBlock(final String text, final Writer writer) throws IOException {
        if (!text.isEmpty()) {
            writer.write(toTextBlock(text).toHtml());
        }
    }

    private static void convertText(final BufferedReader reader, final Writer writer) throws IOException {
        final StringBuilder text = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            if (!line.isEmpty()) {
                text.append(line).append(LINE_SEPARATOR);
            } else {
                writeHtmlTextBlock(text.toString(), writer);
                text.setLength(0);
            }
            line = reader.readLine();
        }

        writeHtmlTextBlock(text.toString(), writer);
    }

    public static void main(final String[] args) {
        try {
            final BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8)
            );
            try {
                final Writer writer = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8)
                );
                try {
                    convertText(reader, writer);
                } catch (IOException e) {
                    System.out.println("Cannot convert text because of I/O error: " + e.getMessage());
                } finally {
                    writer.close();
                }
            } catch (final FileNotFoundException e) {
                System.out.println("Output file was not found: " + e.getMessage());
            } catch (final SecurityException e) {
                System.out.println("Access to output file denied: " + e.getMessage());
            } catch (final IOException e) {
                System.out.println("Can not close output file: " + e.getMessage());
            } finally {
                reader.close();
            }
        } catch (final FileNotFoundException e) {
            System.out.println("Input file was not found: " + e.getMessage());
        } catch (final SecurityException e) {
            System.out.println("Access to input file denied: " + e.getMessage());
        } catch (final IOException e) {
            System.out.println("Can not close input file: " + e.getMessage());
        }
    }
}
