import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;

public class MarkovModel {

    private static final int ASCII = 128;
    private String text;
    private int order;
    protected HashMap<String, HashMap<Character, Integer>> symbolTable = new HashMap<String, HashMap<Character, Integer>>();

    // creates a Markov model of order k for the specified text
    public MarkovModel(String text, int k){
        this.text = text;
        this.order = k;
    }

    // returns the order k of this Markov model
    public int order(){
        return order;
    }


    // returns a string representation of the Markov model (as described below)
    public String toString(){
        String out = "";
        for (String key : symbolTable.keySet()){
            out = out + key + ": ";
            for (char subkey : symbolTable.get(key).keySet()){
                out = out + " " + subkey + " " + symbolTable.get(key).get(subkey);
            }
            out = out + "\n";
        }
        return out;
    }

    // returns the number of times the specified kgram appears in the text
    public int freq(String kgram){
        int freq = 0;
        int len  = kgram.length();
        if (len != order) { throw new IllegalArgumentException("kgram not length of order k"); }
        // symbolTable.put(kgram, );
        Queue<String> storage = new LinkedList<String>();
        for (int i = 0; i < text.length();){
            kgram = kgram.substring(i, i + len) + kgram;
            storage.add(kgram.substring(i, i + len));
            i = i + len;
            if (i >= len) {i = i % len;}
        }
        while (!storage.isEmpty()){
            String current = storage.remove();
            if (current.equals(kgram)) freq++;
        }
        return freq;
    }

    // returns the number of times the character c follows the specified
    // kgram in the text
    public int freq(String kgram, char c){
        int freq = 0;
        int len  = kgram.length();
        if (len != order) { throw new IllegalArgumentException("kgram not length of order k"); }
        for (int i = 0; i < text.length();){
            String current = text.substring(i, i + len);
            if (i + 1 != text.length() && text.charAt(i + len) == c) { freq++; }
            i = i + len;
            if (i >= text.length()) { i = i % len; } 
        }
        return freq;
    }

    // returns a random character that follows the specified kgram in the text,
    // chosen with weight proportional to the number of times that character
    // follows the specified kgram in the text
    public char random(String kgram){
        return 'k';
    }

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
