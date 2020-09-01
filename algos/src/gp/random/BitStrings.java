package gp.random;

class BitStrings {

	public static void main(String[] args) {
		String s1 = args[0], s2 = args[1];
		String result = "";
		int curr_bit=0;
		for (int i=0; i<5; ++i) {
			curr_bit = (s1.charAt(i)-'0') | (s2.charAt(i)-'0');
			result += (char)(curr_bit + '0');
		}
		System.out.println(result);
	}
}