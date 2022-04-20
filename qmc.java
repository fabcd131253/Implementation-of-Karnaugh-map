import java.util.*;

public class qmc{
    
    int variables;
    String dontCares = "";

    qmc(int variableInput){

        this.variables = variableInput;
        for(int i = 0; i < variableInput; i++){
            dontCares.concat("-");
        }
    }

    public ArrayList<String> getLetters(){
        ArrayList<String> v = new ArrayList<>();
        String[] letters = {"a", "b", "c", "d"};
        for(int i = 0; i < this.variables; i++){
            v.add(letters[i]);
        }
        return v;
    }

    public String decimalToBinary(int n){

        if(n == 0){
            return ("");
        }
        else if(n % 2 == 0){
            return decimalToBinary(n / 2) + "0";
        }
        else{
            return decimalToBinary(n / 2) + "1";
        }
    }

    public String fillUp(String binaryNum){

        int max = this.variables - binaryNum.length();
        for(int i = 0; i < max; i++){
            binaryNum = "0" + binaryNum;
        }
        return binaryNum;
    }

    public boolean isGreyCode(String a, String b){

        int c = 0;
        for(int i = 0; i < a.length(); i++){
            if(a.charAt(i) != b.charAt(i)){
                c++;
            }
        }
        return c == 1;
    }

    public String replaceComplements(String a, String b){

        String temp  = "";
        for(int i = 0; i < a.length(); i++){
            if(a.charAt(i) != b.charAt(i)){
                temp = temp + "-";
            }
            else{
                temp = temp + a.charAt(i);
            }
        }
        return temp;
    }

    public boolean isInArray(ArrayList<String> a, String b){

        for(int i = 0; i < a.size(); i++){
            if(a.get(i).equals(b)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> reduce(ArrayList<String> minTerms){

        ArrayList<String> newMinTerms = new ArrayList<>();
        int max = minTerms.size();
        int[] checker = new int[max];

        for(int i = 0; i < max; i++){
            for(int j = 1; j < max; j++){
                if(isGreyCode(minTerms.get(i), minTerms.get(j))){

                    checker[i] = 1;
                    checker[j] = 1;
                    if(!isInArray(newMinTerms, replaceComplements(minTerms.get(i), minTerms.get(j)))){
                        newMinTerms.add(replaceComplements(minTerms.get(i), minTerms.get(j)));
                    }
                }
            }
        }

        for(int i = 0; i < max; i++){
            if(checker[i] != 1 && !isInArray(newMinTerms, minTerms.get(i))){
                newMinTerms.add(minTerms.get(i));
            }
        }

        checker = null;
        return newMinTerms;
    }
    
    public String getValue(String a){
        String temp = "";
        ArrayList<String> varLetters = this.getLetters();
        if(a == this.dontCares){
            return "1";
        }
        else if(a.equals("0000")){
            return ("There is no index.");
        }

        for(int i = 0; i < a.length(); i++){
            if(a.charAt(i) != '-'){
                if(a.charAt(i) == '0'){
                    temp = temp + varLetters.get(i) + "\'";
                }
                else{
                    temp = temp + varLetters.get(i);
                }
            }
        }
        return temp;
    }

    public boolean isArrayEqual(ArrayList<String> a, ArrayList<String> b){
        if(a.size() != b.size()){
            return false;
        }
        Collections.sort(a);
        Collections.sort(b);
        for(int i = 0; i < a.size(); i++){
            if(a.get(i) != b.get(i)){
                return false;
            }
        }
        return true;
    }
}