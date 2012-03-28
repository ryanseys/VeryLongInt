package sysc2100;

import java.util.ArrayList;

/**
 * This class file is the solution to Assignment 2 in SYSC 2100 (Winter 2012)
 * It was created and submitted by Ryan Seys (100817604).
 * 
 * This class represents a very long integer number made up of
 * an array of individual Integers. Some functions that can be performed is addition
 * and subtraction. Addition and subtraction methods are used to implement
 * the solving of a specified fibonacci value, as well as the factorial of a given number. 
 * @author Ryan Seys - 100817604
 * @version 1.0
 */
public class VeryLongInt {
	
	// Instance Variables
	
	protected ArrayList<Integer> digits;
	
	// Constructors
	
	/**
	 * Initializes this VeryLongInt object from a given String object.
	 * 
	 * @param s - the given String object.
	 * 
	 * @throws NullPointerException - if s is null.
	 * 
	 */
	public VeryLongInt (String s) {//System.out.println("String passed: " + s);
			digits = new ArrayList<Integer>();
			String temp = "";
			for(int i = 0; i < s.length(); i++) {
				// I'm sure there is a more efficient way of doing this.
				 temp = s.substring(i, i+1);
				//uses the regular expression comparison of a digit between 0 and 9
				if(temp.matches("\\d")) { 
					digits.add(Integer.parseInt(temp));
				}
			}
			this.strip_zeros();
	}
	
	/**
	 * The VeryLongInt is initialized and contains the int converted into
	 * a VeryLongInt.
	 * 
	 * @param n an int which will be converted into a VeryLongInt. n >= 0.
	 */
	public VeryLongInt(int n) {
		this(Integer.toString(n));
	}
	
	// Methods
	
	/**
	 * The calling object contains the nth Fibonacci number.
	 * 
	 * @param n an int which represents which Fibonacci number 
	 * the VeryLongInt will contain. n > 0.
	 */
	@SuppressWarnings("unchecked")
	public void fibonacci(int n) {
		VeryLongInt prev = new VeryLongInt(0);
		VeryLongInt curr = new VeryLongInt(1);
		VeryLongInt temp = new VeryLongInt(0);
		if(n < 0) {
			System.out.println("Fibonacci cannot accept a negative number. Operation aborted.");
		}
		else if(n <= 2) {
			this.digits = (ArrayList<Integer>) curr.digits.clone();
		}
		else {
			for(int i = 1; i < n; i++) {
				temp.digits = (ArrayList<Integer>) curr.digits.clone();
				curr.add(prev);
				prev.digits = (ArrayList<Integer>) temp.digits.clone();
			}
			this.digits = (ArrayList<Integer>) curr.digits.clone();
		}
	}
	
	/**
	 * The calling object contains n!.
	 * 
	 * @param n an int which represents which factorial the 
	 * 		VeryLongInt will contain. n >= 0.
	 */
	//this is just some message so the compiler doesn't 
	//complain about the unchecked casting while cloning
	@SuppressWarnings("unchecked")	
	public void factorial(int n) {
		VeryLongInt temp = new VeryLongInt(1);
		//a factorial is simply a bunch of multiplications.
		//could also be solved recursively but an iterative approach
		//		is taken here
		for(int i = 1; i < n+1; i++) {
			temp.multiply(new VeryLongInt(i));
		}
		//THIS COULD CAUSE PROBLEMS IF NOT CLONED!
		this.digits = (ArrayList<Integer>) temp.digits.clone();
	}
	
	/**
	 * Returns a String representation of this VeryLongInt object. 
	 * 
	 * @return a String representation of this VeryLongInt object in the form
	 * '[' followed by the digits, separated by commas and single spaces, followed by ']'
	 * 
	 */
	public String toString() {
		// Fortunately the ArrayList representation is exactly as wanted.
		// so just print that.
		return digits.toString();
	}
	
	/**
	 * Increments this VeryLongInt object by a specified VeryLongInt object.
	 * 
	 * @param otherVeryLongInt - the specified VeryLongInt object to be added to
	 * 		this VeryLongInt object.
	 * @throws NullPointerException - if otherVeryLongInt is null.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void add(VeryLongInt otherVeryLongInt) {
			int carry = 0; //the value to store the carry value if the addition is > 9.
			int result = 0; // the result of the addition
			//array used to store the temporary intermediate values
			ArrayList<Integer> temp = new ArrayList<Integer>();
				//go through each value in the VeryLongInt objects.
				for(int i = 0; i <= Math.max(otherVeryLongInt.length(), this.length()); i++) {
					result = this.least(i) + otherVeryLongInt.least(i) + carry;
					if(result > 9) {		//check result for overflow
						result=result%10; //get the least significant digit. (we know the value can't exceed 2 digits).
						carry = 1; //carry always 1 because we know the result is between 10 and 18. (9+1 to 9+9)
					}
					else carry = 0;
					//add this value to the temporary array.
					temp.add(0,Integer.valueOf(result));
				}
				this.digits = (ArrayList<Integer>) temp.clone(); //replace the digits with the temporary ones.
				this.strip_zeros();
	}
	
	/**
	 * Multiplies this VeryLongInt object by a specified VeryLongInt object.
	 * 
	 * @param otherVeryLongInt - the specified VeryLongInt object to be multiplied
	 * to this VeryLongInt object.
	 * 
	 * @throws NullPointerException - if otherVeryLongInt is null.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void multiply(VeryLongInt otherVeryLongInt) {
		//initialize some VeryLongInts, so we can add them together
		// for the multiplication steps.
		VeryLongInt temp1 = new VeryLongInt(0);
		VeryLongInt temp2 = new VeryLongInt(0);
		//clear their arrays or else we will have an extra zero
		//this is because we can't initialize a VeryLongInt with no value
		temp1.digits.clear();
		temp2.digits.clear();
		int carry = 0;
		int result = 0;
		
		for(int i = 0; i <= otherVeryLongInt.length(); i++) {
			for(int j = 0; j <= this.length(); j++) {
				//complete the multiplication (don't forget to add the carry)
				result = otherVeryLongInt.least(i)*this.least(j) + carry;
				
				//split up the values into new carry and result
				if(result > 9) {
					carry=result/10;
					result=result%10;
				}
				else carry = 0;
				temp1.digits.add(0, result);	//add to the start of the array
			}
			//add it to a secondary temporary array 
			temp2.add(temp1);
			temp1.digits.clear();
			
			// add an appropriate number of zeros to the next line
			// (like traditional paper multiplication)
			for(int k = 0; k < i+1; k++) {
				temp1.digits.add(Integer.valueOf(0));
			}
		}
		//clone these digits and reference the VeryLongInt to them.
		this.digits = (ArrayList<Integer>) temp2.digits.clone();
	}
	
	/**
	 * Returns how many digits there are in the VeryLongInt
	 * 
	 * @return An int of many digits there are in the VeryLongInt.
	 */
	public int length() {
		return digits.size();
	}
	
	/**
	 * Returns the ith least significant digit in digits if i is a non-negative int less than
	 * digits.size(). Otherwise, returns 0.
	 * 
	 * @param i - the number of positions from the rightmost digit in digits to the digit sought.
	 * @return the ith least significant digit in digits, or 0 if there is no such digit.
	 * @throws IndexOutOfBoundsException - if i is negative.
	 */
	protected int least(int i) {
		if(i >= this.length()) {
			return 0;
		}
		else return digits.get(digits.size()-(i+1));
	}
	
	/**
	 * Remove the leading zeros off the VeryLongInt.
	 * 
	 * Leaves one 0 if the value of the VeryLongInt is zero.
	 */
	private void strip_zeros() {
		int val = 0;
		while(val == 0) {
			val = this.digits.get(0);	//get the same MSB everytime until no more zeros
			if(val == 0) {
				if (this.length() > 1) {
					this.digits.remove(0);	//remove it if its zero.
				}
				else {
					val = 1;	//stop deleting zeros because a non-zero was found.
				}
			}
		}
	}
}
