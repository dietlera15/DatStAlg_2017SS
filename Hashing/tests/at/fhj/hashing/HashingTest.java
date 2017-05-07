package at.fhj.hashing;

import static org.junit.Assert.*;

import org.junit.Test;

//import at.fhj.hashing.*;

public class HashingTest {
	@Test
	public void testInsert1() {
		final int size = 7;
		final Integer[] testData = {12, 53, 5, 15, 2, 19};
		final Integer[] expected = {19, 15, 2, 5, 53, 12, null};
		
		HashTable ht = new HashTable(size);
		for (Integer x: testData) 
			assertNotEquals("failed to insert "+x, -1, ht.insert(x));
		assertHashTable(ht, size, expected);
	}

	@Test
	public void testInsert2() {
		final int size = 13;
		final Integer[] testData = {14, 21, 27, 28, 8, 18, 15, 36, 5, 2};
		final Integer[] expected = {5, 14, 27, null, null, 18, 36, 28, 21, 2, 15, null, 8};
		
		HashTable ht = new HashTable(size);
		for (Integer x: testData)
			assertNotEquals("failed to insert "+x, -1, ht.insert(x));
		assertHashTable(ht, size, expected);		
	}

	@Test
	public void testInsertFullTable() {
		final int size = 7;
		final Integer[] testData = {7, 14, 21, 28, 35, 42, 49};
		final Integer[] expected = {7, 49, 14, 28, 42, 21, 35};
		
		HashTable ht = new HashTable(size);
		for (Integer x: testData)
			assertTrue("failed to insert "+x, ht.insert(x) >= 0);
		assertHashTable(ht, size, expected);
		
		assertFalse("insert into full hash table possible", ht.insert(0) >= 0);
		assertHashTable(ht, size, expected);
	}

	@Test
	public void testInsertExisting() {
		final int size = 7;
		final Integer[] testData = {12, 53, 5, 15, 2, 19};
		final Integer[] expected = {19, 15, 2, 5, 53, 12, null};
		
		HashTable ht = new HashTable(size);
		for (Integer x: testData) 
			assertNotEquals("failed to insert "+x, -1, ht.insert(x));
		assertHashTable(ht, size, expected);
		
		assertFalse("insertion of element 5 twice succeeded", ht.insert(5)>=0);
		assertHashTable(ht, size, expected);
	}
	
	
	@Test
	public void testInsertBrent1() {
		final int size = 7;
		final Integer[] testData = {12, 53, 5};
		final Integer[] expected = {null, null, 12, null, 53, 5, null};
		
		HashTable ht = new HashTable(size);
		for (Integer x: testData)
			assertTrue("failed to insert "+x, ht.insertBrent(x) >= 0);
		assertHashTable(ht, size, expected);		
	}

	@Test
	public void testInsertBrent2() {
		final int size = 13;
		final Integer[] testData = {14, 21, 27, 28, 8, 18, 15, 36, 5, 2};
		final Integer[] expected = {null, 27, 28, 8, null, 18, 14, null, 21, 2, 15, 36, 5};
		
		HashTable ht = new HashTable(size);
		for (Integer x: testData)
			assertTrue("failed to insert "+x, ht.insertBrent(x) >= 0);
		assertHashTable(ht, size, expected);		
	}

	@Test
	public void testInsertBrentExisting() {
		final int size = 7;
		final Integer[] testData = {12, 53, 5};
		final Integer[] expected = {null, null, 12, null, 53, 5, null};
		
		HashTable ht = new HashTable(size);
		for (Integer x: testData)
			assertTrue("failed to insert "+x, ht.insertBrent(x) >= 0);
		assertHashTable(ht, size, expected);	
		
		assertFalse("insertion of element 5 twice succeeded", ht.insertBrent(5)>=0);
		assertHashTable(ht, size, expected);
	}


	@Test
	public void testRetrieve() {
		HashObject table[] = { new HashObject(19), new HashObject(15), new HashObject(2), new HashObject(5),
				new HashObject(53), new HashObject(null), null };
		final Integer[] testData = {53, 5, 15, 2, 19};
		
		HashTable ht = new HashTable(table);
		for(Integer x: testData) {
			assertEquals("failed to retrieve ", x, ht.retrieve(x.hashCode()));
		}
		
	}
	
	@Test
	public void testInsertDeleteRetrieve() {
		final int size = 7;
		final Integer[] testData = {19, 4, 5};
		final Integer[] expected1 = {null, null, null, 5, 4, 19, null};
		final Integer[] expected2 = {null, null, null, 5, null, null, null};
		
		HashTable ht = new HashTable(size);
		for (Integer x: testData)
			assertTrue("failed to insert "+x, ht.insert(x) >= 0);
		assertHashTable(ht, size, expected1);
		
		ht.delete(19);
		ht.delete(4);
		assertHashTable(ht, size, expected2);
		
		assertEquals("failed to retrieve ", 5, ht.retrieve(5));		
	}
	
	private void assertHashTable(HashTable ht, int size, Integer[] expected) {
		HashObject[] hoArray = ht.getTable();
		for (int i=0; i<size; i++) {
			if (expected[i] == null) {
				if (hoArray[i] != null)
					assertNull("Index "+i+"should be empty but contains "+hoArray[i].getData(), hoArray[i].getData());
			}
			else {
				assertNotNull("Index "+i+" should contain "+expected[i]+"but is empty", hoArray[i]);
				assertNotNull("Index "+i+" should contain "+expected[i]+"but is free", hoArray[i].getData());
				assertSame("Index "+i, expected[i], hoArray[i].getData());
			}
		}
	}
}
