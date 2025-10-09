package testing.demo.computation;

public class Calculator {
	
	public int add(int value1, int value2) {
		return value1 + value2;
	}
	
	public int divide(int value1, int value2) {
		if( value2 == 0)
			throw new IllegalArgumentException("Dinomanator value cannot be zero.");
		
		return value1 / value2;
	}
 
}
