import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main {
	
	private static ArrayList<Profile> profiles = new ArrayList<Profile>();

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		profiles = (ArrayList<Profile>) deserData("profiles");
		
		Profile profile = new Profile();
		profile.setName(JOptionPane.showInputDialog(null, "Enter name"));
		profile.setSurname(JOptionPane.showInputDialog(null, "Enter surname"));
		profiles.add(profile);
		
		for(Profile el: profiles) {
			System.out.println(el.getName() + " " + el.getSurname());
		}
		
		serData("profiles", profiles);

	}

	private static void serData(String fileName, Object obj) {
		
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(obj);
			fileOut.close();
			out.close();
		} catch (FileNotFoundException exc) {
			System.out.println("File not found");
			System.exit(1);
		} catch (IOException exc) {
			System.out.println("Inpu/Output error");
			System.exit(2);
		}
		
	}

	private static Object deserData(String fileName) {
		
		Object retObject = null;
		
		try {
			FileInputStream fileIn = new FileInputStream(fileName + ".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			retObject = in.readObject();
			fileIn.close();
			in.close();
		} catch (FileNotFoundException exc) {
			System.out.println("File not found");
			System.exit(1);
		} catch (IOException exc) {
			System.out.println("Inpu/Output error");
			System.exit(2);
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found");
			System.exit(3);
		}
		
		return retObject;
	}

}
