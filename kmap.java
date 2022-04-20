import java.util.*;
import java.io.*;

public class kmap{

    public static String readFile(int index){
        ArrayList<String> inputSource = FileIO.readfile("input.txt");
        //.get(0) is the variable row; .get(1) is the minterm row; .get(2) is the dontcare row.
        inputSource.set(0, inputSource.get(0).replace("v ", ""));
        inputSource.set(1, inputSource.get(1).replace("m ", ""));
        inputSource.set(2, inputSource.get(2).replace("d ", ""));

        //split and change them into integers.
        //for(int i = 0; i < inputSource.size(); i++)
        return inputSource.get(index);
    }

    public static String[] split(String stringInput){
        String[] output = new String[stringInput.length()];
        output = stringInput.split(",");
        return output;
    }

    public static int[] strToInt(String[] stringArrayInput) {

        int[] output = new int[stringArrayInput.length];
        try{    
            for(int i = 0; i < stringArrayInput.length; i++){
                output[i] = Integer.parseInt(stringArrayInput[i]);
            }   
        }
        catch(NumberFormatException e){}

        return output;
    }

    public static int[] init(int index /*choose the row*/){
        
        int[] output = strToInt(split(readFile(index)));
        return output;
    }

    public static void fileInit(File outputFile) throws IOException{
        if(outputFile.createNewFile()){}
        else{
            FileWriter initializer = new FileWriter("output.txt");
            initializer.write("");
            initializer.close();
        }
    }

    public static void printer(int[] minterms, int[] dontcares,int variables, qmc qmc, 
    ArrayList<String> PI, ArrayList<String> EPI, ArrayList<String> minimum) throws IOException{

        for(int i = 0; i < minterms.length; i++){
            System.out.println("CHECKPOINT: minterm = " + minterms[i]);
        }
        System.out.println("CHECKPOINT: ");
        for(int i = 0; i < dontcares.length; i++){
            System.out.println("CHECKPOINT: dontcare = " +dontcares[i]);
        }
        System.out.println("CHECKPOINT: ");

        ArrayList<String> PI_Letter = new ArrayList<>();
        ArrayList<String> EPI_Letter = new ArrayList<>();
        ArrayList<String> minimum_Letter = new ArrayList<>();

        for(int i = 0; i < PI.size(); i++){
            PI_Letter.add(qmc.getValue(PI.get(i)));
            if(i == PI.size() - 1){}
            else{
                PI_Letter.add(",");
            }
        }
        for(int i = 0; i < EPI.size(); i++){
            EPI_Letter.add(qmc.getValue(EPI.get(i)));
            if(i == EPI.size() - 1){}
            else{
                EPI_Letter.add(",");
            }
        }
        for(int i = 0; i < minimum.size(); i++){
            minimum_Letter.add(qmc.getValue(minimum.get(i)));
            if(i == minimum.size() - 1){}
            else{
                minimum_Letter.add("+");
            }
        }

        File outputFile = new File("output.txt");
        fileInit(outputFile);
        FileWriter outputWriter = new FileWriter("output.txt");

        String row0 = "| 0 | 0 | 0 | 0 |";
        String row1 = "| 0 | 0 | 0 | 0 |";
        String row2 = "| 0 | 0 | 0 | 0 |";
        String row3 = "| 0 | 0 | 0 | 0 |";
        String row0_2bit = "| 0 | 0 |";
        String row1_2bit = "| 0 | 0 |";
        if(variables == 2){
            outputWriter.write("  \\ A|       |" + "\n");
            outputWriter.write(" B \\ |  0   1|" + "\n");
            outputWriter.write("-----+---+---+" + "\n");
            for(int i = 0; i < minterms.length; i++){
                if(minterms[i] == 0 || minterms[i] == 2){
                    row0_2bit = replacer(row0_2bit, minterms[i], "m", 2);
                }
            }
            for(int i = 0; i < dontcares.length; i++){
                if(dontcares[i] == 0 || dontcares[i] == 2){
                    row0_2bit = replacer(row0_2bit, dontcares[i], "d", 2);
                }
            }
            outputWriter.write("    0" + row0_2bit + "\n");
            outputWriter.write("-----+---+---+" + "\n");
            for(int i = 0; i < minterms.length; i++){
                if(minterms[i] == 1 || minterms[i] == 3){
                    row1_2bit = replacer(row1_2bit, minterms[i], "m", 2);
                }
            }
            for(int i = 0; i < dontcares.length; i++){
                if(dontcares[i] == 1 || dontcares[i] == 3){
                    row1_2bit = replacer(row1_2bit, dontcares[i], "d", 2);
                }
            }
            outputWriter.write("    1" + row1_2bit + "\n");
            outputWriter.write("-----+---+---+" + "\n");
        }
        else if(variables == 3){
            outputWriter.write("  \\AB|               |" + "\n");
            outputWriter.write(" C \\ | 00  01  11  10|" + "\n");
            outputWriter.write("-----+---+---+---+---+" + "\n");
            for(int i = 0; i < minterms.length; i++){
                if(minterms[i] == 0 || minterms[i] == 2 || minterms[i] == 6 || minterms[i] == 4){
                    row0 = replacer(row0, minterms[i], "m", 3);
                }
            }
            for(int i = 0; i < dontcares.length; i++){
                if(dontcares[i] == 0 || dontcares[i] == 2 || dontcares[i] == 6 || dontcares[i] == 4){
                    row0 = replacer(row0, dontcares[i], "d", 3);
                }
            }
            outputWriter.write("    0" + row0 + "\n");
            outputWriter.write("-----+---+---+---+---+" + "\n");
            for(int i = 0; i < minterms.length; i++){
                if(minterms[i] == 1 || minterms[i] == 3 || minterms[i] == 7 || minterms[i] == 5){
                    row1 = replacer(row1, minterms[i], "m", 3);
                }
            }
            for(int i = 0; i < dontcares.length; i++){
                if(dontcares[i] == 1 || dontcares[i] == 3 || dontcares[i] == 7 || dontcares[i] == 5){
                    row1 = replacer(row1, dontcares[i], "d", 3);
                }
            }
            outputWriter.write("    1" + row1 + "\n");
            outputWriter.write("-----+---+---+---+---+" + "\n");

        }
        else if(variables == 4){
            outputWriter.write("  \\AB|               |" + "\n");
            outputWriter.write("CD \\ | 00  01  11  10|" + "\n");
            outputWriter.write("-----+---+---+---+---+" + "\n");
            for(int i = 0; i < minterms.length; i++){
                if(minterms[i] == 0 || minterms[i] == 4 || minterms[i] == 12 || minterms[i] == 8){
                    row0 = replacer(row0, minterms[i], "m", 4);
                }
            }
            for(int i = 0; i < dontcares.length; i++){
                if(dontcares[i] == 0 || dontcares[i] == 4 || dontcares[i] == 12 || dontcares[i] == 8){
                    row0 = replacer(row0, dontcares[i], "d", 4);
                }
            }
            outputWriter.write("   00" + row0 + "\n");
            outputWriter.write("-----+---+---+---+---+" + "\n");
            for(int i = 0; i < minterms.length; i++){
                if(minterms[i] == 1 || minterms[i] == 5 || minterms[i] == 13 || minterms[i] == 9){
                    row1 = replacer(row1, minterms[i], "m", 4);
                }
            }
            for(int i = 0; i < dontcares.length; i++){
                if(dontcares[i] == 1 || dontcares[i] == 5 || dontcares[i] == 13 || dontcares[i] == 9){
                    row1 = replacer(row1, dontcares[i], "d", 4);
                }
            }
            outputWriter.write("   01" + row1 + "\n");
            outputWriter.write("-----+---+---+---+---+" + "\n");
            for(int i = 0; i < minterms.length; i++){
                if(minterms[i] == 3 || minterms[i] == 7 || minterms[i] == 15 || minterms[i] == 11){
                    row2 = replacer(row2, minterms[i], "m", 4);
                }
            }
            for(int i = 0; i < dontcares.length; i++){
                if(dontcares[i] == 3 || dontcares[i] == 7 || dontcares[i] == 15 || dontcares[i] == 11){
                    row2 = replacer(row2, dontcares[i], "d", 4);
                }
            }
            outputWriter.write("   11" + row2 + "\n");
            outputWriter.write("-----+---+---+---+---+" + "\n");
            for(int i = 0; i < minterms.length; i++){
                if(minterms[i] == 2 || minterms[i] == 6 || minterms[i] == 14 || minterms[i] == 10){
                    row3 = replacer(row3, minterms[i], "m", 4);
                }
            }
            for(int i = 0; i < dontcares.length; i++){
                if(dontcares[i] == 2 || dontcares[i] == 6 || dontcares[i] == 14 || dontcares[i] == 10){
                    row3 = replacer(row3, dontcares[i], "d", 4);
                }
            }
            outputWriter.write("   10" + row3 + "\n");
            outputWriter.write("-----+---+---+---+---+" + "\n");
        }
        outputWriter.write("\n");

        outputWriter.write("Prime implicant:");
        for(int i = 0; i < PI_Letter.size(); i++){
            outputWriter.write(PI_Letter.get(i));
        }
        outputWriter.write("\n");

        outputWriter.write("Essential prime implicant:");
        for(int i = 0; i < EPI_Letter.size(); i++){
            outputWriter.write(EPI_Letter.get(i));
        }
        outputWriter.write("\n");

        if(variables == 2){
            outputWriter.write("F(A,B)=");
            for(int i = 0; i < minimum_Letter.size(); i++){
                outputWriter.write(minimum_Letter.get(i));
            }
        }
        else if(variables == 3){
            outputWriter.write("F(A,B,C)=");
            for(int i = 0; i < minimum_Letter.size(); i++){
                outputWriter.write(minimum_Letter.get(i));
            }
        }
        else if(variables == 4){
            outputWriter.write("F(A,B,C,D)=");
            for(int i = 0; i < minimum_Letter.size(); i++){
                outputWriter.write(minimum_Letter.get(i));
            }
        }
        outputWriter.write("\n");
        outputWriter.close();
    }
    public static String replacer(String inputRow, int index, String type, int bit){
        StringBuffer replacer = new StringBuffer();
        replacer.append(inputRow);
        String typeBuffer = "0";
        int columnBuffer = 0;

        if(type.equals("m")){
            typeBuffer = "1";
        }
        else if(type.equals("d")){
            typeBuffer = "x";
        }

        if(bit == 2){
            if(index == 0 || index == 1){
                columnBuffer = 2;
            }
            else if(index == 2 || index == 3){
                columnBuffer = 6;
            }
        }
        else if(bit == 3){
            if(index == 0 || index == 1){
                columnBuffer = 2;
            }
            else if(index == 2 || index == 3){
                columnBuffer = 6;
            }
            else if(index == 6 || index == 7){
                columnBuffer = 10;
            }
            else if(index == 4 || index == 5){
                columnBuffer = 14;
            }
        }
        else if(bit == 4){
            if(index == 0 || index == 1 || index == 3 || index == 2){
                columnBuffer = 2;
            }
            else if(index == 4 || index == 5 || index == 7 || index == 6){
                columnBuffer = 6;
            }
            else if(index == 12 || index == 13 || index == 15 || index == 14){
                columnBuffer = 10;
            }
            else if(index == 8 || index == 9 || index == 11 || index == 10){
                columnBuffer = 14;
            }
        }

        replacer.replace(columnBuffer, columnBuffer + 1, typeBuffer);
        String output = replacer.toString();
        System.out.println("CHECKPOINT: " + output + " " + type + " " + index);
        return output;
    }
    

    public static void main(String[] args) throws IOException{


        int variableNumbers = init(0)[0];
        if(variableNumbers > 4){
            System.out.println("The max variable number is 4!");  
            return;
        }

        qmc qmc = new qmc(variableNumbers);

        ArrayList<String> mintermExpansion = new ArrayList<>();
        ArrayList<String> realMinterms = new ArrayList<>(); //realMinTerms doesn't consist of don't cares.
        
        int m, d;
        //adding minterms to minterm arraylist.
        for(int i = 0; i < init(1).length; i++){
            m = init(1)[i];
            realMinterms.add(qmc.fillUp(qmc.decimalToBinary(m)));
            mintermExpansion.add(qmc.fillUp(qmc.decimalToBinary(m)));
        }
        //adding dontcares to minterm arraylist. (in sorting and reducing phase, we see dontcares as minterms.)
        for(int i = 0; i < init(2).length; i++){
            d = init(2)[i];
            mintermExpansion.add(qmc.fillUp(qmc.decimalToBinary(d)));
        }

        Collections.sort(mintermExpansion);
        Collections.sort(realMinterms);

        do{
            mintermExpansion = qmc.reduce(mintermExpansion);
            Collections.sort(mintermExpansion);
        }
        while(!qmc.isArrayEqual(mintermExpansion, qmc.reduce(mintermExpansion)));

        //petrick starts here

        ArrayList<String> EPI = new ArrayList<>(); //essential prime implicants
        ArrayList<ArrayList<String> > POS = new ArrayList<>(); //product-of-sums

        for(int i = 0; i < realMinterms.size(); i++){
            ArrayList<String> pn = new ArrayList<>();
            pn = petrick.match(realMinterms.get(i), mintermExpansion);
            if(petrick.isEPI(pn)){
                EPI.add(pn.get(0));
            }
            POS.add(pn);
        }

        ArrayList<String> SOP = new ArrayList<>(); //sum-of-products
        while(POS.size() > 1){
            POS.add(petrick.combine(POS.get(0), POS.get(1)));
            POS.remove(0);
            POS.remove(0);
        }
        SOP = POS.get(0);

        System.out.println("CHECKPOINT: SOP = " + SOP);

        ArrayList<ArrayList<String> > mintermExpansionFinal = new ArrayList<>();
        int counter = petrick.count(petrick.split(SOP.get(0), variableNumbers));
        for(int i = 0; i < SOP.size() - 1; i++){
            int a = petrick.count(petrick.split(SOP.get(i), variableNumbers));
            if(a < counter){
                counter = a;
            }
        }
        for(int i = 0; i < SOP.size(); i++){
            int a = petrick.count(petrick.split(SOP.get(i), variableNumbers));
            if(a == counter){
                mintermExpansionFinal.add(petrick.split(SOP.get(i), variableNumbers));
            }
        }

        System.out.println("CHECKPOINT: mintermfinal = " + mintermExpansionFinal);

        EPI = petrick.reduceEPI(EPI);
        ArrayList<String> PI = mintermExpansionFinal.get(0); //prime implicants
        ArrayList<String> minimumMinterm = new ArrayList<>(); //minimum minterm expansion

        ArrayList<String> temp = new ArrayList<>();
        System.out.println("CHECKPOINT: temp = " + temp);
        System.out.println("CHECKPOINT: epi = " + EPI);
        for(int i = 0; i < realMinterms.size(); i++){
            if(petrick.checkPI(realMinterms.get(i), EPI)){
                System.out.println("CHECKPOINT: a");
                temp.add(realMinterms.get(i));
                System.out.println("CHECKPOINT: temp = " + temp);
            }
        }
        //if all EPIs consist of all indexs, then the minimum minterm is the sop of EPIs.
        System.out.println("CHECKPOINT: realminterm = " + realMinterms);
        if(qmc.isArrayEqual(temp, realMinterms)){
            minimumMinterm = EPI;
        }
        else{
            minimumMinterm = PI;
        }

        Collections.sort(PI);
        Collections.sort(EPI);
        Collections.sort(minimumMinterm);
        printer(init(1), init(2), variableNumbers, qmc, PI, EPI, minimumMinterm);
    }
}