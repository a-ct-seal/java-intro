package wordsCount;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;

public class Scanner {
	private final Reader reader;
	private int numOfLine = 0;
	private int i = 0;
	private static final int BUFFER_SIZE = 1024;
	private int readenSize = BUFFER_SIZE;
	private char[] buffer = new char[BUFFER_SIZE];
	private static final String LINE_SEP = System.lineSeparator();
	private final WhitespaceChecker whitespaceChecker;

	private static class DefaultWhitespaceChecker implements WhitespaceChecker {
		public boolean isNotWhitespace(char ch) {
			return !Character.isWhitespace(ch);
		}

		public boolean isWhitespace(char ch) {
			return Character.isWhitespace(ch);
		}
	}

	public Scanner(String fileName, String charset, WhitespaceChecker whitespaceChecker) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		reader = new BufferedReader(
            new InputStreamReader(new FileInputStream(fileName), charset)
        );
		readenSize = reader.read(buffer);
		this.whitespaceChecker = whitespaceChecker;
	}

	public Scanner(String fileName, String charset) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		this(fileName, charset, new DefaultWhitespaceChecker());
	}

	public Scanner(InputStream source, WhitespaceChecker whitespaceChecker) throws IOException {
		reader = new BufferedReader(new InputStreamReader(source));
		readenSize = reader.read(buffer);
		this.whitespaceChecker = whitespaceChecker;
	}

	public Scanner(InputStream source) throws UnsupportedEncodingException, IOException {
		this(source, new DefaultWhitespaceChecker());
	}

	public Scanner(String source, WhitespaceChecker whitespaceChecker) throws IOException {
		reader = new BufferedReader(new StringReader(source));
		readenSize = reader.read(buffer);
		this.whitespaceChecker = whitespaceChecker;
	}

	public Scanner(String source) throws IOException {
		this(source, new DefaultWhitespaceChecker());
	}

	private int lineCount(int j) {
		if (LINE_SEP.charAt(j) == buffer[i]) {
			j++;
			if (j == LINE_SEP.length()) {
				numOfLine++;
				j = 0;
			}
		} else {
			j = 0;
		}
		return j;
	}

	private boolean checkAndGetBuffer() throws IOException {
		if (i < readenSize) {
			return true;
		}
        readenSize = reader.read(buffer);
        i = 0;
        return readenSize >= 0;
	}

	private void skipWhitespaces() throws IOException {
		int j = 0;
		while (checkAndGetBuffer() && whitespaceChecker.isWhitespace(buffer[i])) {
			j = lineCount(j);
           	i++;
        }
	}

	public boolean hasNextToken() throws IOException {
		skipWhitespaces();
		return (i < readenSize);
	}

	public String nextToken() throws NoSuchElementException, IOException {
		if (!hasNextToken()) {
			throw new NoSuchElementException("No elements left");
		}

		StringBuilder newToken = new StringBuilder();
		while (checkAndGetBuffer() && whitespaceChecker.isNotWhitespace(buffer[i])) {
           	newToken.append(buffer[i]);
           	i++;
        }
		
		return newToken.toString();
	}

	public int getNumOfLine() {
		return numOfLine;
	}

	public void close() throws IOException {
		reader.close();
	}

	public boolean isEmpty()  throws IOException {
		return !checkAndGetBuffer();
	}
}