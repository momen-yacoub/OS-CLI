package terminal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class Commands {
	String directory;

	public Commands() {
		directory = "/";
	}

	public void ls() {
		File folder = new File(directory);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; ++i) {
			System.out.println(listOfFiles[i].getName());
		}
	}

	public String getDirectory() {
		return directory;
	}

	public void cd(String path) {
		if (path.equals("..")) {
			if (directory.equals("/"))
				return;
			for (int i = directory.length() - 2; i >= 0; --i) {
				if (directory.charAt(i) == '/') {
					directory = directory.substring(0, i + 1);
					return;
				}
			}
			return;
		}
		if (path.length() > 2 && path.charAt(0) == '.' && path.charAt(1) == '/') {
			path = directory + path.substring(2);
		} else if (path.charAt(0) != '/') {
			path = directory + path;
		}
		if (!(new File(path).exists()) || !(new File(path).isDirectory())) {
			System.out.println("Error Path Does not exist!");
			return;
		}

		directory = path + '/';
		String temp = "/";
		for (int i = 1; i < directory.length(); ++i) {
			if (!(directory.charAt(i) == directory.charAt(i - 1) && directory
					.charAt(i) == '/')) {
				temp += directory.charAt(i);
			}
		}
		directory = temp;
	}

	public void mkdir(String path) {
		if (path.length() > 2 && path.charAt(0) == '.' && path.charAt(1) == '/') {
			path = directory + path.substring(2);
		} else if (path.charAt(0) != '/') {
			path = directory + path;
		}
		boolean success = (new File(path)).mkdirs();
		if (!success) {
			System.out.println("Failed to make directory");
		}
	}

	public void delete_directory(File file) {
		if (file.isDirectory()) {
			String files[] = file.list();
			if (files.length == 0) {
				file.delete();
			} else {

				for (String temp : files) {
					File go = new File(file, temp);
					delete_directory(go);
				}
				file.delete();
			}
		} else {
			file.delete();
		}

	}

	public void rmdir(String path) {
		if (path.length() > 2 && path.charAt(0) == '.' && path.charAt(1) == '/') {
			path = directory + path.substring(2);
		} else if (path.charAt(0) != '/') {
			path = directory + path;
		}
		if (!(new File(path).exists())) {
			System.out.println("Path doesn't exist");
			return;
		}
		if (new File(path).isDirectory()) {
			File currentFile = new File(path);
			delete_directory(currentFile);
		} else {
			System.out.println("Not Directory");
		}
	}

	public void rm(String path) {
		if (path.length() > 2 && path.charAt(0) == '.' && path.charAt(1) == '/') {
			path = directory + path.substring(2);
		} else if (path.charAt(0) != '/') {
			path = directory + path;
		}
		if (!(new File(path).exists())) {
			System.out.println("Path doesn't exist");
			return;
		}
		if (new File(path).isFile()) {
			File currentFile = new File(path);
			boolean s = currentFile.delete();
		} else {
			System.out.println("Not a File");
		}
	}

	public void cat(String path) {
		if (path.length() > 2 && path.charAt(0) == '.' && path.charAt(1) == '/') {
			path = directory + path.substring(2);
		} else if (path.charAt(0) != '/') {
			path = directory + path;
		}
		if (!(new File(path).exists())) {
			System.out.println("Path doesn't exist");
			return;
		}
		if (!(new File(path).isFile())) {
			System.out.println("Not a file");
			return;
		}
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line;

			try {
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}
			} catch (IOException ex) {
				System.out.println("Failed");
				return;
			}
		} catch (FileNotFoundException ex) {
			System.out.println("File Not Found!");
			return;
		}

	}

	public void mv(String path1, String path2) {
		if (path1.length() > 2 && path1.charAt(0) == '.'
				&& path1.charAt(1) == '/') {
			path1 = directory + path1.substring(2);
		} else if (path1.charAt(0) != '/') {
			path1 = directory + path1;
		}
		if (path2.length() > 2 && path2.charAt(0) == '.'
				&& path2.charAt(1) == '/') {
			path2 = directory + path2.substring(2);
		} else if (path2.charAt(0) != '/') {
			path2 = directory + path2;
		}
		if (!(new File(path1).isFile())) {
			System.out.println("Error Not a File");
			return;
		}
		if (!(new File(path2).exists())) {
			System.out.println("Error Not a File");
			return;
		}
		File afile = new File(path1);
		for (int i = path1.length() - 2; i >= 0; --i) {
			if (path1.charAt(i) == '/') {
				path1 = path1.substring(i);
				break;
			}
		}
		if (afile.renameTo(new File(path2 + path1))) {
			System.out.println("File is moved successfully!");
		} else {
			System.out.println("Failed to move the file!");
		}
	}

	public void mvr(String path1, String path2) {
		if (path1.length() > 2 && path1.charAt(0) == '.'
				&& path1.charAt(1) == '/') {
			path1 = directory + path1.substring(2);
		} else if (path1.charAt(0) != '/') {
			path1 = directory + path1;
		}
		if (path2.length() > 2 && path2.charAt(0) == '.'
				&& path2.charAt(1) == '/') {
			path2 = directory + path2.substring(2);
		} else if (path2.charAt(0) != '/') {
			path2 = directory + path2;
		}
		if (!(new File(path1).isDirectory())) {
			System.out.println("Error Not a File");
			return;
		}
		if (!(new File(path2).exists())) {
			System.out.println("Error Not a File");
			return;
		}
		File afile = new File(path1);
		for (int i = path1.length() - 2; i >= 0; --i) {
			if (path1.charAt(i) == '/') {
				path1 = path1.substring(i);
				break;
			}
		}
		if (afile.renameTo(new File(path2 + path1))) {
			System.out.println("File is moved successfully!");
		} else {
			System.out.println("Failed to move the file!");
		}
	}

	public void more(String path) {
		if (path.length() > 2 && path.charAt(0) == '.' && path.charAt(1) == '/') {
			path = directory + path.substring(2);
		} else if (path.charAt(0) != '/') {
			path = directory + path;
		}
		if (!(new File(path).exists())) {
			System.out.println("Path doesn't exist");
			return;
		}

		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line;
			int ctr = 0;
			try {
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
					++ctr;
					if (ctr % 10 == 0)
						if(System.in.read() == 'q')return;
				}
			} catch (IOException ex) {
				System.out.println("Failed");
				return;
			}
		} catch (FileNotFoundException ex) {
			System.out.println("File Not Found!");
			return;
		}
	}

	public void less(String path) {
		if (path.length() > 2 && path.charAt(0) == '.' && path.charAt(1) == '/') {
			path = directory + path.substring(2);
		} else if (path.charAt(0) != '/') {
			path = directory + path;
		}
		if (!(new File(path).exists())) {
			System.out.println("Path doesn't exist");
			return;
		}

		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line;
			int ctr = 0;
			try {
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
					if (ctr > 10)
						if(System.in.read() == 'q')return;
					++ctr;
				}
			} catch (IOException ex) {
				System.out.println("Failed");
				return;
			}
		} catch (FileNotFoundException ex) {
			System.out.println("File Not Found!");
			return;
		}
	}

	public void greb(String word, String path) {
		if (path.length() > 2 && path.charAt(0) == '.' && path.charAt(1) == '/') {
			path = directory + path.substring(2);
		} else if (path.charAt(0) != '/') {
			path = directory + path;
		}
		if (!(new File(path).isFile())) {
			System.out.println("File doesn't exist");
			return;
		}
		File file = new File(path);
		Scanner cin = null;
		try {
			cin = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (cin.hasNext()) {
			String line = cin.nextLine();
			for (int i = 0; i <= line.length() - word.length(); i++) {
				boolean f = true;
				for (int j = i, k = 0; k < word.length() && f; k++, j++) {
					if (line.charAt(j) != word.charAt(k)) {
						f = false;
					}
				}
				if (f) {
					System.out.println(line);
					break;
				}
			}
		}
	}
	
	public void doTheCopy(String path1 , String path2) {
		if(new File(path1).isFile()) {
			cpFiles(path1 , path2);
		}
		else {
			String[] list = new File(path1).list();
			mkdir(path2);
			for(int i = 0 ; i < list.length ; ++i) {
				String c1 = "/" , c2 = "/";
				if(path1.charAt(path1.length() - 1) == '/')c1 = "";
				if(path2.charAt(path2.length() - 1) == '/')c2 = "";
				doTheCopy(path1 + c1 + list[i] , path2 + c2 + list[i]);
			}
		}
	}
	
	public void cpFiles(String path1 , String path2) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path1));
            String line;
            String text = "";
            try {
                while((line = reader.readLine()) != null) {
                	text += line + "\n";
                }
                FileOutputStream out;
                try {
                    out = new FileOutputStream(path2);
                    for(int c: text.toCharArray()){
                        out.write((char)c);
                    }
                }
                catch (Exception e) {
                    System.out.println("An Error Occurred while saving data!");
                }
            }
            catch (IOException ex) {
                System.out.println("An Error Occurred!");
           }
        }
        catch (FileNotFoundException ex) {}
	}
	public void cp(String path1 , String path2) {
		if (path1.length() > 2 && path1.charAt(0) == '.'
				&& path1.charAt(1) == '/') {
			path1 = directory + path1.substring(2);
		} else if (path1.charAt(0) != '/') {
			path1 = directory + path1;
		}
		if (path2.length() > 2 && path2.charAt(0) == '.'
				&& path2.charAt(1) == '/') {
			path2 = directory + path2.substring(2);
		} else if (path2.charAt(0) != '/') {
			path2 = directory + path2;
		}
		
		if(!(new File(path1).exists())) {
			System.out.println("Source path dosn't exist");
			return;
		}
		if(!(new File(path2).exists())) {
			System.out.println("Destinatino path dosn't exist");
			return;
		}
		
		if(!(new File(path1)).isFile()) {
			System.out.println("Source not a file");
			return;
		}
		if(!(new File(path2).isDirectory())) {
			System.out.println("Destination is not a directory");
			return;
		}
		
		String name = "";
		int shift = 0;
		if((path1.charAt(path1.length() - 1) == '/'))shift = 1;
		for(int i = path1.length() - shift - 1 ; i >= 0 ; --i) {
			if(path1.charAt(i) == '/')break;
			name += path1.charAt(i);
		}
		String temp = "";
		for(int i = name.length() - 1 ; i >= 0 ; --i)
			temp += name.charAt(i);
		
		path2 += "/" + temp;
		doTheCopy(path1 , path2);
	}
	public void cpr(String path1 , String path2) {
		if (path1.length() > 2 && path1.charAt(0) == '.'
				&& path1.charAt(1) == '/') {
			path1 = directory + path1.substring(2);
		} else if (path1.charAt(0) != '/') {
			path1 = directory + path1;
		}
		if (path2.length() > 2 && path2.charAt(0) == '.'
				&& path2.charAt(1) == '/') {
			path2 = directory + path2.substring(2);
		} else if (path2.charAt(0) != '/') {
			path2 = directory + path2;
		}
		
		if(!(new File(path1).exists())) {
			System.out.println("Source path dosn't exist");
			return;
		}
		if(!(new File(path2).exists())) {
			System.out.println("Destinatino path dosn't exist");
			return;
		}
		
		if(!(new File(path1)).isDirectory()) {
			System.out.println("Source not a Directory");
			return;
		}
		if(!(new File(path2).isDirectory())) {
			System.out.println("Destination is not a directory");
			return;
		}
		
		String name = "";
		int shift = 0;
		if((path1.charAt(path1.length() - 1) == '/'))shift = 1;
		for(int i = path1.length() - shift - 1 ; i >= 0 ; --i) {
			if(path1.charAt(i) == '/')break;
			name += path1.charAt(i);
		}
		String temp = "";
		for(int i = name.length() - 1 ; i >= 0 ; --i)
			temp += name.charAt(i);
		
		path2 += "/" + temp;
		doTheCopy(path1 , path2);
	}
	
	public void rfind(String fi , String path) {
		System.out.println(path);
		File folder = new File(path);
		String []names = folder.list();
		for(int i = 0 ; i < names.length ; ++i) {
			if(new File(path + "/" + names[i]).isFile() && names[i] == fi) {
				System.out.println(path + "/" + names[i]);
			}
			else if(new File(path + "/" + names[i]).isDirectory()) {
				rfind(fi , path + "/" + names[i]);
			}
		}
	}
	public void find(String fi , String path){
		if (path.length() > 2 && path.charAt(0) == '.' && path.charAt(1) == '/') {
			path = directory + path.substring(2);
		} else if (path.charAt(0) != '/') {
			path = directory + path;
		}
		
		System.out.println(path);
		
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		for (int j = 0; j < listOfFiles.length; ++j) {
			if(new File(path + "/" + listOfFiles[j].getName()).isDirectory()){
				find(fi , path + "/" + listOfFiles[j].getName());
			}
			else if(listOfFiles[j].getName() == fi){
				System.out.println(path);
			}
		}
	}
}