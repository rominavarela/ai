package Commons;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.JFileChooser;

public class IO {
	/**
	 * @param file
	 * @return File content in separate lines.
	 */
	public static ArrayList<String> read(File file) {
		ArrayList<String> lines = new ArrayList<String>();
		
		if (file.exists()) 
			try
			{
				BufferedReader reader= new BufferedReader(new FileReader(file));
				
				while (reader.ready())
				{
					String line= reader.readLine();
					if(!line.isEmpty())
						lines.add(line);
				}
				
				reader.close();
			}
			catch(Exception ex)
			{
				System.out.println("ERROR\n" + ex.getMessage());
			}
		
		return lines;
	}
	
	/**
	 * Write lines into file.
	 * @param file
	 * @param lines
	 */
	public static void write(File file, ArrayList<String> lines) {
		try
		{
			if (!file.exists())
				file.createNewFile();
			
			BufferedWriter writer= new BufferedWriter(new FileWriter(file));
			for (String line : lines)
				writer.write(line + "\n");
			writer.close();
			
			System.out.println("content written into: " + file.getAbsolutePath());
		}
		catch(Exception ex)
		{
			System.out.println("ERROR\n" + ex.getMessage());
		}
	}
	
	/**
	 * @param name
	 * @return Cross-Platform relative path file.
	 */
	public static File getFile(String name) {
		File f = new File(name);
		if (System.getProperty("os.name").toLowerCase().contains("win"))
			return f;
		if (f.exists())
			return f;
		else {
			File ROOT = new File(IO.class.getResource("/").getFile());
			f = new File(ROOT.getAbsolutePath().replace("%20", " ") + "/"
					+ name);
			return f;
		}
	}
	
	/**
	 * Displays emergent window with file chooser.
	 * @param dialogTitle
	 * @return Selected File or null
	 */
	public static String chooseDirection(String dialogTitle) {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle(dialogTitle);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			return chooser.getSelectedFile().getAbsolutePath().toString();
		return null;
	}
}