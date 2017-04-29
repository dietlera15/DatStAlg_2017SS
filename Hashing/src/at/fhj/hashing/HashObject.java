package at.fhj.hashing;

/**
 * Wrapper object class for objects to be stored in a hash table
 * 
 */
public class HashObject {
	private Object data = null;
	
	/**
	 * Create a hash table wrapper object for a given object
	 * @param data ... object to be stored in the hash table
	 */
	public HashObject(Object data) {
		this.data = data;
	}
	
	/**
	 * Get the object stored in the wrapper object
	 * @return ... stored object
	 */
	public Object getData() {
		return this.data;
	}
	
	/**
	 * Set the object in an existing hash table wrapper object
	 * @param obj
	 */
	public void setData(Object obj) {
		this.data = obj;
	}
}
