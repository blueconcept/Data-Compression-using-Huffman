/*
 * Code by Dr. Marriot
 */

public class Code {
	char c;
	String bits;
	public Code(char cha, String b){
		c = cha;
		bits = b;
	}
	
	public String toString(){
		return "("+c+", "+bits+")";
	}
}