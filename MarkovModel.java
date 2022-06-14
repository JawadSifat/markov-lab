import java.util.HashMap;
import java.util.Queue;

public class MarkovModel {

    private static final int ASCII = 128;
    private String text;
    private int order;
    private HashMap<String, HashMap<char, Integer>> symbolTable = new HashMap<String, HashMap<char, Integer>>();

    // creates a Markov model of order k for the specified text
    public MarkovModel(String text, int k){
        this.text = text;
        this.order = k;
    }

    // returns the order k of this Markov model
    public int order(){
        return order;
    }


    private static void printCharInt(HashMap<char, Integer> map){
        for (char key : map.keySet()){
            StdOut.printf("%c %d", key, map.get(key));
        }
    }   

    // returns a string representation of the Markov model (as described below)
    public String toString(){
        for (String key : symbolTable.keySet()){
            StdOut.printf("%5s: ", key);
            printCharInt(symbolTable.get(key)); 
            StdOut.println();            
        }
    }

    // returns the number of times the specified kgram appears in the text
    public int freq(String kgram){
        int len = kgram.length();
        if (len != order) { throw new IllegalArgumentException("kgram not length of order k"); }
        symbolTable.put(kgram);
        Queue<String> storage = new Queue<String>();
        for (int i = 0; i < text.length; i++){
            storage.add(String.substring(i, i + len));
        }
        

    }

    // returns the number of times the character c follows the specified
    // kgram in the text
    public int freq(String kgram, char c)

    // returns a random character that follows the specified kgram in the text,
    // chosen with weight proportional to the number of times that character
    // follows the specified kgram in the text
    public char random(String kgram)

    // tests this class by directly calling all instance methods
    public static void main(String[] args) {
        String text1 = "banana";
        MarkovModel model1 = new MarkovModel(text1, 2);
        StdOut.println("freq(\"an\", 'a')    = " + model1.freq("an", 'a'));
        StdOut.println("freq(\"na\", 'b')    = " + model1.freq("na", 'b'));
        StdOut.println("freq(\"na\", 'a')    = " + model1.freq("na", 'a'));
        StdOut.println("freq(\"na\")         = " + model1.freq("na"));
        StdOut.println();

        String text3 = "one fish two fish red fish blue fish";
        MarkovModel model3 = new MarkovModel(text3, 4);
        StdOut.println("freq(\"ish \", 'r') = " + model3.freq("ish ", 'r'));
        StdOut.println("freq(\"ish \", 'x') = " + model3.freq("ish ", 'x'));
        StdOut.println("freq(\"ish \")      = " + model3.freq("ish "));
        StdOut.println("freq(\"tuna\")      = " + model3.freq("tuna"));
    }
}
