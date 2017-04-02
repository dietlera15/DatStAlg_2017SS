package at.fhj.sorting;

public interface ICompare<T> {
	boolean greaterEqual(T o);
	boolean lesserEqual(T o);
}
