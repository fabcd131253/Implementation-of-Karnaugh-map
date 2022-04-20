import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO{
	public static ArrayList<String> readfile(String filename){
		ArrayList<String> fileContent = new ArrayList<String>();  
		FileReader fr = null;
		BufferedReader br = null;
		try{
			String currentLine = null;
			br = new BufferedReader(new FileReader(filename));
			while ((currentLine = br.readLine()) != null){
				fileContent.add(currentLine);
			}
			 
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return fileContent;
	}
}