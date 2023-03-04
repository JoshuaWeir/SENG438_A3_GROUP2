package org.jfree.data;

import static org.junit.Assert.*;
import org.jfree.data.DataUtilities;
import org.junit.*;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jfree.data.KeyedValues;

public class DataUtilitiesTest extends DataUtilities {
	
	private Values2D mockValues;
	private double[] doubleArray;
	
	@BeforeClass public static void setUpBeforeClass() throws Exception {
	}
	@Before
	public void setUp() throws Exception { 
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getColumnCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(3));
				one(values).getValue(0, 1);
				will(returnValue(6));
				one(values).getValue(1, 0);
				will(returnValue(4));
				one(values).getValue(1, 1);
				will(returnValue(8));
			}
		});
		mockValues = values;
		doubleArray = new double[] {1.2,3.4,5.5,9.6};
	}
//////////////////////////////////////////NEW
	@Test
		public void test_equal_difflength() {
		double[][] data = {{3.2, 3.5}, {2.1, 7.5}};
		double[][] data2 = {{3.2, 3.5}, {2.1, 7.5}, {7.0, 5.5}};
		assertFalse(DataUtilities.equal(data,  data2));
	}
	
	@Test 
		public void test_equal_dataNull(){
		double[][] data = null;
		double[][] data2 = {{3.2, 3.5}, {2.1, 7.5}};
		assertFalse(DataUtilities.equal(data,  data2));
	}
	@Test 
		public void test_equal_data2Null(){
		double[][] data = {{3.2, 3.5}, {2.1, 7.5}};
		double[][] data2 = null;
		assertFalse(DataUtilities.equal(data,  data2));
	}
	
	@Test
		public void test_clone() {
		double[][] data = {{1, 2, 3}, {4, 9.7, 5}};
		double[][] data2 = clone(data);
		assertArrayEquals(data,data2);
	}
	//61.6% does not increase
	//@Test (expected = IllegalArgumentException.class)
	//public void test_clone_Null() {
	//double[][] data = null;
	//double[][] data2 = clone(data);
	//}
	
	//@Test
	//public void test_clone_change() {
	//double[][] data = {{1, 2, 3}, {4, 5.5, 6}, {7, 8, 9}};
	//double[][] data2 = {{10, 11, 12}, {13, 14, 14.9}};
	//data2 = clone(data);
	//assertArrayEquals(data,data2);
	//}
	//@Test
	//public void test_createNumberArrayFunction() {
	//double data1 = 1;
	//double data2 = 2;
	//double data3 = 3;
	//double data4 = 4;
	//double [] arr = {1, 2, 3, 4};
	//Number array[] = new Number[4];
	//array[0] = data1;
	//array[1] = data2;
	//array[2] = data3;
	//array[3] = data4;
	//Number[] resultArray = DataUtilities.createNumberArray(arr);
	//assertArrayEquals(array, resultArray);
	//}
	@Test
		public void test_equal_inf() {
		double[][] a = {{Double.POSITIVE_INFINITY, 0, -3}, {Double.POSITIVE_INFINITY, -4, 8.5}};
		double[][] b = {{Double.POSITIVE_INFINITY, 0, -3}, {Double.POSITIVE_INFINITY, -4, 8.5}};
		assertTrue(DataUtilities.equal(a,  b));
	}
	
	@Test
	public void test_equal_neginf() {
	double[][] a = {{Double.NEGATIVE_INFINITY, 0, -3}, {Double.NEGATIVE_INFINITY, -4, 8.5}};
	double[][] b = {{Double.NEGATIVE_INFINITY, 0, -3}, {Double.NEGATIVE_INFINITY, -4, 8.5}};
	assertTrue(DataUtilities.equal(a,  b));
	}

/////////////////////////////////////////
	@Test
	public void equalValidDoubleArrayProvided() {
		double[][] data = {{1,2,3,4,5}, {6,7,8,9,0}, 
				{1,3,5,7,9}, {2,4,6,8,0}, {1,4,7,0,3}};
		Number[][] actual = DataUtilities.createNumberArray2D(data);
		Number[][] expected = {{1.0,2.0,3.0,4.0,5.0}, {6.0,7.0,8.0,9.0,0.0}, 
				{1.0,3.0,5.0,7.0,9.0}, {2.0,4.0,6.0,8.0,0.0}, {1.0,4.0,7.0,0.0,3.0}};
		assertEquals("CreateNumberArray2D did not return the correct array of Numbers.",
		expected, actual);
	}
	
	@Test
	public void equalFirstArgNull() {
		double [][] a = null;
		double [][] b = null;
		boolean actual = DataUtilities.equal(a, b);
		assertEquals("DataUtilies.equal did not return true when arg1 and arg2 are null.", true, actual);
	}
	
	@Test
	public void equalSecArgNull() {
		double [][] a = {{1,2,3,4,5}, {6,7,8,9,0}, 
				{1,3,5,7,9}, {2,4,6,8,0}, {1,4,7,0,3}};
		double [][] b = null;
		boolean actual = DataUtilities.equal(a, b);
		assertEquals("DataUtilies.equal did not return false when arg1 is not null and arg2 is null.", false, actual);
	}
	
	@Test
	public void equalUnequalLength() {
		double [][] a = {{1,2,3,4,5}, {6,7,8,9,0}, 
				{1,3,5,7,9}, {2,4,6,8,0}, {1,4,7,0,3}};
		double [][] b = {{1,2,3,4,5}, {6,7,8,9,0}, 
				{1,3,5,7,9}, {2,4,6,8,0}};
		boolean actual = DataUtilities.equal(a, b);
		assertEquals("DataUtilies.equal did not return false when args have different lenegths.", false, actual);
	}
	
	@Test
	public void equalEqualLengthTrue() {
		double [][] a = {{1,2,3,4,5}, {6,7,8,9,0}, 
				{1,3,5,7,9}, {2,4,6,8,0}, {1,4,7,0,3}};
		double [][] b = {{1,2,3,4,5}, {6,7,8,9,0}, 
				{1,3,5,7,9}, {2,4,6,8,0}, {1,4,7,0,3}};
		boolean actual = DataUtilities.equal(a, b);
		assertEquals("DataUtilies.equal did not return true when args are equal.", true, actual);
	}
	
	@Test
	public void equalEqualLengthFalse() {
		double [][] a = {{1,2,3,4,5}, {6,7,8,9,1}, 
				{1,3,5,7,9}, {2,4,6,8,0}, {1,4,7,0,3}};
		double [][] b = {{1,2,3,4,5}, {6,7,8,9,0}, 
				{1,3,5,7,9}, {2,4,6,8,0}, {1,4,7,0,3}};
		boolean actual = DataUtilities.equal(a, b);
		assertEquals("DataUtilies.equal did not return false when args are equal in length but not in content.", false, actual);
	}
	
	@Test
	public void sourceNullElement() {
		double [][] src = {{1,2,3,4,5}, {}, 
				{1,3,5,7,9}, {2,4,6,8,0}, {1,4,7,0,3}};
		double [][] clone = DataUtilities.clone(src);
		double [][] expected = {{1,2,3,4,5}};
		assertEquals("DataUtilies.clone copied past null element.", expected, clone);
	}
	
	
	@Test
	public void nonNullSource() {
		double [][] src = {{1,2,3,4,5}, {6,7,8,9,1}, 
				{1,3,5,7,9}, {2,4,6,8,0}, {1,4,7,0,3}};
		double [][] clone = DataUtilities.clone(src);
		assertEquals("DataUtilies.clone did not copy valid src.", src, clone);
	}

	@Test
	public void invalidNullValueProvided() {
		boolean actual = false;
		boolean expected = true;
		try {
			double[][] data = null;
			Number[][] returned = DataUtilities.createNumberArray2D(data);
		}
		catch(Exception e) {
			actual = true;
		}
		assertEquals("Invalid data provided exception was not thrown.",
				expected, actual);
		
	}
	
	@Test
    public void test_calculatePositiveRowTotal() {
        Mockery mock = new Mockery();
        Values2D example = mock.mock(Values2D.class);
        mock.checking(new Expectations() {
            {
                one(example).getColumnCount();
                will(returnValue(5));
                
                one(example).getValue(0, 0);
                will(returnValue(1.0));
                
                one(example).getValue(0, 1);
                will(returnValue(0.5));
                
                one(example).getValue(0, 2);
                will(returnValue(0.5));
                
                one(example).getValue(0, 3);
                will(returnValue(2.5));
            }
        });
        double result = DataUtilities.calculateRowTotal(example,0);
        assertEquals("Result should be 4.5", result, 4.5, .000000001d);
    }
	
	@Test
    public void calculateNegativeRowTotal() {
        Mockery mock = new Mockery();
        Values2D example = mock.mock(Values2D.class);
        mock.checking(new Expectations() {
            {
                one(example).getColumnCount();
                will(returnValue(-5));
                
                one(example).getValue(0, 0);
                will(returnValue(1.0));
                
                one(example).getValue(0, 1);
                will(returnValue(0.5));
                
                one(example).getValue(0, 2);
                will(returnValue(0.5));
                
                one(example).getValue(0, 3);
                will(returnValue(2.5));
            }
        });
        double result = DataUtilities.calculateRowTotal(example,0);
        assertEquals("Result should be 4.5", result, 4.5, .000000001d);
    }
	
	
	@Test
    public void calculatePositiveRowTotalSpecCol() {
        Mockery mock = new Mockery();
        Values2D example = mock.mock(Values2D.class);
        mock.checking(new Expectations() {
            {
                one(example).getColumnCount();
                will(returnValue(5));
                
                one(example).getValue(0, 0);
                will(returnValue(1.0));
                
                one(example).getValue(0, 1);
                will(returnValue(0.5));
                
                one(example).getValue(0, 2);
                will(returnValue(0.5));
                
                one(example).getValue(0, 3);
                will(returnValue(2.5));
            }
        });
        int[] col = {2,3};
        double result = DataUtilities.calculateRowTotal(example,0,col);
        assertEquals("Result should be 4.5", result, 4.5, .000000001d);
    }
	
	@Test
    public void calculateNegativeRowTotalSpecCol() {
        Mockery mock = new Mockery();
        Values2D example = mock.mock(Values2D.class);
        mock.checking(new Expectations() {
            {
                one(example).getColumnCount();
                will(returnValue(-5));
                
                one(example).getValue(0, 0);
                will(returnValue(-1));
                
                one(example).getValue(0, 1);
                will(returnValue(-0.5));
                
                one(example).getValue(0, 2);
                will(returnValue(-0.5));
                
                one(example).getValue(0, 3);
                will(returnValue(-2.5));
            }
        });
        int[] col = {2,3};
        double result = DataUtilities.calculateRowTotal(example,0,col);
        assertEquals("Result should be -4.5", result, -4.5, .000000001d);
    }
	
	@Test
    public void calculatePositiveColTotal() {
        Mockery mock = new Mockery();
        Values2D example = mock.mock(Values2D.class);
        mock.checking(new Expectations() {
            {
                one(example).getColumnCount();
                will(returnValue(5));
                
                one(example).getValue(0, 0);
                will(returnValue(1.0));
                
                one(example).getValue(0, 1);
                will(returnValue(0.5));
                
                one(example).getValue(0, 2);
                will(returnValue(0.5));
                
                one(example).getValue(0, 3);
                will(returnValue(2.5));
            }
        });
        int[] col = {1};
        double result = DataUtilities.calculateColumnTotal(example,0);
        assertEquals("Result should be 4.5", result, 4.5, .000000001d);
    }
	
	@Test
    public void calculateNegativeColTotal() {
        Mockery mock = new Mockery();
        Values2D example = mock.mock(Values2D.class);
        mock.checking(new Expectations() {
            {
                one(example).getRowCount();
                will(returnValue(-5));
                
                one(example).getValue(0, 0);
                will(returnValue(1.0));
                
                one(example).getValue(0, 1);
                will(returnValue(0.5));
                
                one(example).getValue(0, 2);
                will(returnValue(0.5));
                
                one(example).getValue(0, 3);
                will(returnValue(2.5));
            }
        });
        int[] col = {1};
        double result = DataUtilities.calculateColumnTotal(example,0);
        assertEquals("Result should be 4.5", result, 4.5, .000000001d);
    }
	
	@Test
    public void calculatePositiveColTotalSpecRow() {
        Mockery mock = new Mockery();
        Values2D example = mock.mock(Values2D.class);
        mock.checking(new Expectations() {
            {
                one(example).getColumnCount();
                will(returnValue(5));
                
                one(example).getValue(0, 0);
                will(returnValue(1.0));
                
                one(example).getValue(0, 1);
                will(returnValue(0.5));
                
                one(example).getValue(0, 2);
                will(returnValue(0.5));
                
                one(example).getValue(0, 3);
                will(returnValue(2.5));
            }
        });
        int[] col = {1};
        double result = DataUtilities.calculateColumnTotal(example,0,col);
        assertEquals("Result should be 4.5", result, 4.5, .000000001d);
    }
	
	//no rows
	@Test(expected = IllegalArgumentException.class)
    public void test_noRows(){
        Mockery mock = new Mockery();
        Values2D example = mock.mock(Values2D.class);
        mock.checking(new Expectations() {
            {
                one(example).getColumnCount();
                will(returnValue(0));
                
            }
        });
    }
	
	@Test
	//getting total of negative numbers
    public void test_calculateNegativeRowTotal() {
        Mockery mock = new Mockery();
        Values2D example = mock.mock(Values2D.class);
        mock.checking(new Expectations() {
            {
                one(example).getColumnCount();
                will(returnValue(5));
                one(example).getValue(0, 0);
                will(returnValue(-1.0));
                one(example).getValue(0, 1);
                will(returnValue(0.5));
                one(example).getValue(0, 2);
                will(returnValue(-6.5));
                one(example).getValue(0, 3);
                will(returnValue(2.5));
            }
        });
        double result = DataUtilities.calculateRowTotal(example,0);
        assertEquals("Result should be -4.5", result, -4.5, .000000001d);
    }
	
	//calculateColumnTotal() tests
	@Test
	public void sumColumnZero() {
		assertEquals("Sum of column 0 should be 7.", 7, DataUtilities.calculateColumnTotal(mockValues, 0), 0.000000001d);
	}
	@Test
	public void sumColumnOne() {
		assertEquals("Sum of column 1 should be 14.", 14, DataUtilities.calculateColumnTotal(mockValues, 1), 0.000000001d);
	}
	@Test
	public void nullTable() {
		try {
			DataUtilities.calculateColumnTotal(null, 0);
		}
		catch(IllegalArgumentException e) {
			//works as intended if exception is thrown
		}
	}
	//@Test
	//public void nonExistantColumn() {
	//	DataUtilities.calculateColumnTotal(mockValues, 2);
	//}
	
	//createNumberArray() tests
	@Test
	public void arrayExists() {
		assertNotNull("Number array should exist.", DataUtilities.createNumberArray(doubleArray));
	}
	@Test
	public void nullArray() {
		try {
			double[] nullArray = null;
			DataUtilities.createNumberArray(nullArray);
		}
		catch(IllegalArgumentException e) {
			//works as intended if exception is thrown
		}
	}
	@Test
	public void correctValues() {
		Number[] numberArray = new Number[] {1.2,3.4,5.5,9.6};
		assertArrayEquals("Number array should have same values as original array.", numberArray, DataUtilities.createNumberArray(doubleArray));
	}
	
	///////////////////////////////////////////
	@Test
	
	public void cumulativePercentageNegCount() {
		Mockery mockingContext = new Mockery();
		KeyedValues exampleValue = mockingContext.mock(KeyedValues.class);
		mockingContext.checking(new Expectations() {
		{
			
			allowing(exampleValue).getKey(0);
			will(returnValue(0));
			
			allowing(exampleValue).getKeys();
			will(returnIterator(0,1,2));
			
			allowing(exampleValue).getKey(1);
			will(returnValue(1));
			
			allowing(exampleValue).getKey(2);
			will(returnValue(2));
			
			allowing(exampleValue).getItemCount();
			will(returnValue(-3));
			
			
			
			allowing(exampleValue).getValue(0);
			will(returnValue(5));
			
			allowing(exampleValue).getValue(1);
			will(returnValue(9));
			
			allowing(exampleValue).getValue(2);
			will(returnValue(2));
			
			
			
		}
	
	});
	
		Mockery mocking = new Mockery();
		KeyedValues exampleValueOutput = mocking.mock(KeyedValues.class);
		mocking.checking(new Expectations() {
		
		{
	
			one(exampleValueOutput).getItemCount();
			will(returnValue(-3));
			
			one(exampleValueOutput).getKeys();
			will(returnIterator(0,1,2));
			
			one(exampleValueOutput).getValue(0);
			will(returnValue(0.3125));
			
			one(exampleValueOutput).getValue(1);
			will(returnValue(0.875));
			
			one(exampleValueOutput).getValue(2);
			will(returnValue(1.0));
			
		}
	
	});
	
		KeyedValues actualOutput = DataUtilities.getCumulativePercentages(exampleValue);
		assertEquals(exampleValueOutput.getValue(0), actualOutput.getValue(0));
		assertEquals(exampleValueOutput.getValue(1), actualOutput.getValue(1));
		assertEquals(exampleValueOutput.getValue(2), actualOutput.getValue(2));
	
	}
	///////////////////////////////////////////
	@Test

	public void testGetCumulativePercentage() {
		Mockery mockingContext = new Mockery();
		KeyedValues exampleValue = mockingContext.mock(KeyedValues.class);
		mockingContext.checking(new Expectations() {
			{

				allowing(exampleValue).getKey(0);
				will(returnValue(0));

				allowing(exampleValue).getKeys();
				will(returnIterator(0,1,2));

				allowing(exampleValue).getKey(1);
				will(returnValue(1));

				allowing(exampleValue).getKey(2);
				will(returnValue(2));

				allowing(exampleValue).getItemCount();
				will(returnValue(3));



				allowing(exampleValue).getValue(0);
				will(returnValue(5));

				allowing(exampleValue).getValue(1);
				will(returnValue(9));

				allowing(exampleValue).getValue(2);
				will(returnValue(2));



			}

		});

		Mockery mocking = new Mockery();
		KeyedValues exampleValueOutput = mocking.mock(KeyedValues.class);
		mocking.checking(new Expectations() {

			{

				one(exampleValueOutput).getItemCount();
				will(returnValue(3));

				one(exampleValueOutput).getKeys();
				will(returnIterator(0,1,2));

				one(exampleValueOutput).getValue(0);
				will(returnValue(0.3125));

				one(exampleValueOutput).getValue(1);
				will(returnValue(0.875));

				one(exampleValueOutput).getValue(2);
				will(returnValue(1.0));

			}

		});

		KeyedValues actualOutput = DataUtilities.getCumulativePercentages(exampleValue);
		assertEquals(exampleValueOutput.getValue(0), actualOutput.getValue(0));
		assertEquals(exampleValueOutput.getValue(1), actualOutput.getValue(1));
		assertEquals(exampleValueOutput.getValue(2), actualOutput.getValue(2));

	}
	
	
	
	@After
	public void tearDown() throws Exception {
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
}
