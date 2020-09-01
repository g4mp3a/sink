
public class CamelCaseValidator {
  
  private boolean validate(String S, String[] words) {
    
    String token = "";
    Set<String> dictionary = new HashSet<>(Arrays.asList(words));
    for (int i=0; i<S.length(); i++) {
      char letter = S.charAt(i);
      if (Character.isUpperCase(letter)) {
        if (!dictionary.contains(token)) return false;
        token = "";
      }
      token += Character.toString(letter);
    }
    if (!dictionary.contains(token)) return false;
    return true;
  }
  
  public static void main (String args[]) {
    
  }
}