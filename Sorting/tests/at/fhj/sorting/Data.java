package at.fhj.sorting;

public class Data implements ICompare<Data>, Comparable<Data> {

	private int value;
	static int comparisions = 0;

	public Data(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}

	@Override
	public boolean greaterEqual(Data y) {
		Data.comparisions++;
		if (this.value < y.getValue())
			return false;
		else
			return true;
	}

	@Override
	public boolean lesserEqual(Data y) {
		Data.comparisions++;

		if (this.value > y.getValue())
			return false;
		else
			return true;
	}

	@Override
	public String toString() {
		return Integer.toString(value);
	}

	@Override
	public int compareTo(Data o) {
		Data.comparisions++;
		if (this.value < o.getValue())
			return -1;
		else if (this.value > o.getValue())
			return +1;
		else
			return 0;
	}

}
