import java.util.ArrayList;
import java.util.List;


public class Test {

	/*
	 * This is a testing main class to test the classes and methods with smaller input sizes
	 */
	public static void main(String[] args) {
		List<Character> character = new ArrayList<Character>();
		character.add('A');
		character.add('B');
		character.add('C');
		character.add('C');
		character.add('D');
		character.add('E');
		character.add('F');
		character.add('G');
		character.add('G');
		character.add('G');
		
		FrequencyCount count = new FrequencyCount(character);
		CodingTree ct = new CodingTree(character);
		ct.printThis();
	}

}
