package wordsCount;

import java.util.Arrays;

public class IntList {
	private int size;
	private int[] array;

	public IntList() {
		size = 0;
		array = new int[1];
	}

	private void resize(int newSize) {
		array = Arrays.copyOf(array, newSize);
	}

	public void add(int num) {
		if (array.length == size) {
			resize((array.length * 3 + 1) / 2);
		}
		array[size] = num;
		size++;
	}

	public int get(int idx) throws IndexOutOfBoundsException {
		if (idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		return array[idx];
	}

	public void set(int idx, int newNum) throws IndexOutOfBoundsException {
		if (idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		array[idx] = newNum;
	}

	public int pop() throws IndexOutOfBoundsException {
		if (size == 0) {
			throw new IndexOutOfBoundsException();
		}
		size--;
		return array[size];
	}

	public int size() {
		return size;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < size; i++) {
			str.append(array[i]);
			str.append(' ');
		}
		return str.substring(0, str.length() - 1);
	}
}