package at.fhj.hashing;

/**
 * Hash table for arbitrary objects using the double hashing algorithm with Brent.
 * The standard hashCode() method is used to get the key value of the objects
 *
 */
public class HashTable {
	/**
	 * Objects to be stored are wrapped in a HashObject object before being put into
	 * the hash table:
	 * - If an entry in the hash table contains null, the entry is empty. 
	 * - If the table entry contains a HashObject wrapper object with a null data object, 
	 *   it is free, meaning there was a data object that has been deleted.
	 * - If the wrapper object contains a valid data object, the hash table entry is in 
	 *   use.
     */
	private HashObject[] table;
	
	/**
	 * Create a hash table of the specified size
	 * @param size ... size of the hash table
	 */
	public HashTable(int size) {
		this.table = new HashObject[size];
	}

	/**
	 * Create a hash table Object from a given HashObject array (for testing purposes)
	 * @param table ... HashObject array
	 */
	public HashTable(HashObject[] table) {
		this.table = table;
	}
	
	/**
	 * Return the array containing the HashObject wrapper objects
	 * @return ... Hash array
	 */
	public HashObject[] getTable() {
		return this.table;
	}
	
	/**
	 * Insert the given into the hash table using double hashing (without Brent)
	 * @param obj ... object to insert into the hash table
	 * @return table index where the object was successfully inserted, -1 if table 
	 * is full or an object with the same key already exists within the table
	 */
	public int insert(Object obj) {
		int key = obj.hashCode();

		// Begin implementation
		int h1 = hash1(key);
		int h2 = hash2(key);	
		for (int i = 0; i < table.length; i++) {
			if (isFree(h1)) { 
				setEntry(h1, obj);
				return h1;
			} else if (getEntry(h1).hashCode() == key) {
				return -1;
			} else {
				h1 = stayPositive(h1 - h2);
			}
		}
		return -1; // change it!
		// End implementation
	}
	 
	/**
	 * Insert the given into the hash table using double hashing with Brent
	 * @param obj ... object to insert into the hash table
	 * @return table index where the object was successfully inserted, -1 if table 
	 * is full or an object with the same key already exists within the table
	 */
	public int insertBrent(Object obj) {
		int key = obj.hashCode();

		// Begin implementation
		int h1 = hash1(key);
		int h2 = hash2(key);
		for (int i = 0; i < table.length; i++) {
			if (isFree(h1)) { 
				setEntry(h1, obj);
				return h1;
			} else if (getEntry(h1).hashCode() == key) {
				return -1;
			} else {
				int keyb = getEntry(h1).hashCode();
				int h2b = hash2(keyb);
				int b1 = i - h2;
				int b2 = i - h2b;
				if (isFree(b1)) {
					setEntry(b1, getEntry(h1));
					setEntry(h1, obj);
				} else {
					h1 = stayPositive(h1 - h2);
				}
			}
		}		
		return -1;  // change it!
		// End implementation
	}
	
	/**
	 * Get the object identified by it's key (hashCode()) value from the hash table
	 * @param key 
	 * @return the object with the specified key value if it exists in the table, null otherwise
	 */
	public Object retrieve(int key) {
		// Begin implementation
		int ret = searchK(key);
		if (ret >= 0) {
			return getEntry(ret);	
		} else {
			return null;
		}
		// End implementation
	}
	
	/**
	 * Delete the object identified by a key (hashCode()) value from the hash table
	 * @param key
	 * @return true if the object with the specified key has been found and deleted, false otherwise
	 */
	public boolean delete(int key) {
		// Begin implementation
		int del = searchK(key);
		if (del >= 0) {
			deleteEntry(del);
			return true;
		} else {
			return false;
		}
		//return false;  // change it!
		// End implementation
	}
	
	/**
	 * Check if a given position in the hash table is empty, i.e. free and no deleted object either
	 * @param pos ... position in hash table
	 * @return true, if the position is empty, false otherwise
	 */
	private boolean isEmpty(int pos) {
		if (table[pos] == null)
			return true;
		else
			return false;
	}
	
	/**
	 * Check if a given position in the hash table is free, i.e. either free or it contains a deleted
	 * object
	 * @param pos ... position in hash table
	 * @return true, if the position is free, false otherwise
	 */
	private boolean isFree(int pos) {
		if (table[pos] == null) // empty
			return true;
		if (table[pos].getData() == null) // free
			return true;
		return false; // occupied
	}
	
	/**
	 * Set an entry in the hash table at a given position
	 * @param pos ... position for inserting
	 * @param obj ... object to insert
	 */
	private void setEntry(int pos, Object obj) {
		if (isEmpty(pos))
			table[pos] = new HashObject(obj);
		else
			table[pos].setData(obj);
	}
	
	/**
	 * Get the entry in the hash table at a given position
	 * @param pos ... position in the hash table
	 * @return object stored in this Position if the position is not free, null otherwise
	 */
	private Object getEntry(int pos) {
		if (!isFree(pos))
			return table[pos].getData();
		else 
			return null;
	}
	
	/**
	 * Delete an entry in the hash table at a give position
	 * @param pos ... position of the entry to be deleted
	 */
	private void deleteEntry(int pos) {
		if (!isEmpty(pos))
			setEntry(pos, null);
	}
	
	// Place your private methods here
	// Begin implementation
	/**
	 * h(k) = k mod m
	 * @param k ... key for hashing
	 * @return position for hashed key value
	 */
	private int hash1(int k) {
		return stayPositive(k % table.length);
	}
	
	/**
	 * h'(k) = 1 + [k mod (m-2)]
	 * @param k ... key for hashing
	 * @return position for hashed key value
	 */
	private int hash2(int k) {
		return stayPositive(1 + k % (table.length - 2));
	}
	
	/**
	 * [h(k) - h'(k)] mod m
	 * h(i,k) = (h(k) - i * h'(k)) mod m.
	 * search index for given key ...
	 * if an empty entry is found, key was never used.
	 * if an free entry is found, check next hash index
	 * if an entry is found, check if entry == key
	 * @param k
	 * @return
	 */
	private int searchK(int k) {
		int index = hash1(k);
		for (int i = 0; i < table.length; i++) {
			if (isEmpty(index)) {
				return -1;
			} else if (isFree(index) || getEntry(index).hashCode() != k) {
				index = stayPositive((hash1(k) - i * hash2(k)) % table.length);
			} else {
				return index;
			}
		}
		return index;
	}
	
	/**
	 * in Java the calculated modulo can be a negative number
	 * @param a
	 * @return a as a positive value
	 */
	private int stayPositive (int a) {
		return (a + table.length) % table.length; 
	}
	// End implementation
}
