import java.util.*;

public class petrick{
    //check if an original minterm can match a reduced minterm.
    public static ArrayList<String> match(String minTerm, ArrayList<String> a/*reduced*/){
        ArrayList<String> output = new ArrayList<>();
        for(int i = 0; i < a.size(); i++){
            boolean temp = true;
            for(int j = 0; j < minTerm.length() && temp == true; j++){
                if(minTerm.charAt(j) == a.get(i).charAt(j) || a.get(i).charAt(j) == '-'){
                    temp = true;
                }
                else{
                    temp = false;
                }
            }
            if(temp == true){
                output.add(a.get(i));
            }
        }
        return output;
    }
    
    public static boolean checkPI(String minTerm, ArrayList<String> a/*reduced*/){
        boolean temp = false;
        for(int i = 0; i < a.size() && temp == false; i++){
            int j = 0;
            while(j < minTerm.length()){
                System.out.println("CHECKPOINT: minterm / a = " + minTerm.charAt(j) + " / " + a.get(i).charAt(j) );
                System.out.println("CHECKPOINT: i / j = " + i + " / " + j);

                if(minTerm.charAt(j) == a.get(i).charAt(j) || a.get(i).charAt(j) == '-'){
                    temp = true;
                }
                else{
                    temp = false;
                }
                j++;
            }
        }
        System.out.println("CHECKPOINT: checkPI = " + temp);

        return temp;
    }

    public static boolean isEPI(ArrayList<String> a){
        if(a.size() == 1){
            return true;
        }
        else{
            return false;
        }
    }

    public static ArrayList<String> reduceEPI(ArrayList<String> a){
        int i = 0;
        int j = 1;
        while(i < a.size() - 1){
            while(j < a.size()){
                if(a.get(i) == a.get(j)){
                    a.remove(j);
                }
                else{
                    j++;
                }
            }
            i++;
            j = i + 1;
        }
        return a;
    }

    public static ArrayList<String> combine(ArrayList<String> a, ArrayList<String> b){
        ArrayList<String> output = new ArrayList<>();

        System.out.println("CHECKPOINT: combine_a and b= " + a + " / " + b);
        

        for(int i = 0; i < a.size(); i++){
            for(int j = 0; j < b.size(); j++){
                output.add(a.get(i) + b.get(j));
            }
        }

        return output;
    }

    public static ArrayList<String> split(String input, int variables){
        ArrayList<String> output = new ArrayList<>();

        //System.out.println("CHECKPOINT: split input = " + input);
        for(int i = 0; i < input.length(); i+= variables){
            if(output.contains(input.substring(i, i + variables))){}
            else{
                output.add(input.substring(i, i + variables));
            }
        }
        return output;
    }

    public static int count(ArrayList<String> input){
        int c;
        c = input.size();
        return c;
    }
}
