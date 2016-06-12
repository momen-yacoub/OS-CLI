package terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class CLI {
	public static void main(String[] args) {
		Commands c = new Commands();
		
		String command;
		Scanner reader = new Scanner(System.in);
		while(true) {
			command = reader.nextLine();
			if(command.equals("exit"))break;
			command += ";";
			
			String[] listOfCommands = command.split(";");
			for(int i = 0 ; i < listOfCommands.length ; ++i) {
				String []arg = listOfCommands[i].split(" ");
				
				if(arg[0].equals("clear")) {
					for(int j = 0 ; j < 20 ; ++j)
						System.out.println();
				}
				else if(arg[0].equals("--help")) {
					System.out.println("The list of commands are:");
					System.out.println("cd");
					System.out.println("Takes a directroy where you want to go to.");
					
					System.out.println();
					
					System.out.println("ls");
					System.out.println("Prints the current directory you are in.");
					
					System.out.println();
					
					System.out.println("clear");
					System.out.println("Clars the terminal.");
					
					System.out.println();
					
					System.out.println("rm");
					System.out.println("Removes a file.\nAdd function -r to remove a folder.");
					
					System.out.println();
					
					System.out.println("rmdir");
					System.out.println("Remove a directory.");
					
					System.out.println();
					
					System.out.println("mv");
					System.out.println("Moves a file to a path.\nadd function -r to move a directory recursivly.");
					
					System.out.println();
					
					System.out.println("mkdir");
					System.out.println("Make a directory in a given path.");
					
					System.out.println();
					
					System.out.println("cat");
					System.out.println("Shows the contents of a file.");
					
					System.out.println();
					
					System.out.println("cp");
					System.out.println("Copys a file to a directory.\nAdd funciton -r to copy a direcotyr recursivly.");
					
					System.out.println();
					
					System.out.println("more");
					System.out.println("Shows the contents of a file page by page.");
					
					System.out.println();
					
					System.out.println("less");
					System.out.println("Shows the contents of a file line by line.");
					
					System.out.println();
					
					System.out.println("greb");
					System.out.println("Shows all the lines where a word exists in a file.");
					
					System.out.println();
					
					System.out.println("find");
					System.out.println("Search for a file inside a directory, and print the path to the files found with this name.");
					
					System.out.println();
					
					System.out.println("date");
					System.out.println("Prints the current date.");
				}
				else if(arg[0].equals("cd")) {
					if(arg.length != 2) {
						System.out.println("Wrong number of arguments, type cd --args for help.");
						continue;
					}
					if(arg[1].equals("--args")) {
						System.out.println("cd takes 1 argument the directory you want to go to.\nOr .. to get back to the previous directory.");
						continue;
					}
					c.cd(arg[1]);
				}
				else if(arg[0].equals("ls")) {
					if(arg.length != 1 && !arg[1].equals("--args")) {
						System.out.println("Wrong number of arguments, type ls --args for help.");
						continue;
					}
					if(arg.length != 1) {
						System.out.println("ls takes no arguments.");
						continue;
					}
					c.ls();
				}
				else if(arg[0].equals("pwd")) {
					if(arg.length != 1 && !arg[1].equals("--args")) {
						System.out.println("Wrong number of arguments, type pwd --args for help.");
						continue;
					}
					if(arg.length != 1) {
						System.out.println("pwd takes no arguments.");
						continue;
					}
					System.out.println(c.getDirectory());
				}
				else if(arg[0].equals("mkdir")) {
					if(arg.length != 2) {
						System.out.println("Wrong number of arguments, type mkdir --args for help.");
						continue;
					}
					if(arg[1].equals("--args")) {
						System.out.println("mkdir takes one argument, the path where you want to create the directory.");
						continue;
					}
					c.mkdir(arg[1]);
				}
				else if(arg[0].equals("rmdir")) {
					if(arg.length != 2) {
						System.out.println("Wrong number of arguments, type rmdir --args for help.");
						continue;
					}
					if(arg[1].equals("--args")) {
						System.out.println("rmdir takes two argument, the path to the directory you want to remove.");
						continue;
					}
					c.rmdir(arg[1]);
				}
				else if(arg[0].equals("rm")) {
					if(arg.length == 1 || arg.length > 3) {
						System.out.println("Wrong number of arguments, type rm --args for help.");
						continue;
					}
					if(arg[1].equals("--args")) {
						System.out.println("rm takes one argument, the path to the file you want to remove.\nor -r and a path to a directory");
						continue;
					}
					if(arg.length == 3) {
						if(!arg[1].equals("-r")) {
							System.out.println("Wrong type of arguments, type rm --args for help.");
							continue;
						}
						c.rmdir(arg[2]);
						continue;
					}
					c.rm(arg[1]);
				}
				else if(arg[0].equals("cat")) {
					if(arg.length != 2) {
						System.out.println("Wrong number of arguments, type cat --args for help.");
						continue;
					}
					if(arg[1].equals("--args")) {
						System.out.println("cat takes one argument, the path to the file you want to view.");
						continue;
					}
					c.cat(arg[1]);
				}
				else if(arg[0].equals("mv")) {
					if(arg.length == 1 || arg.length > 4) {
						System.out.println("Wrong number of arguments, type mv --args for help.");
						continue;
					}
					if(arg.length == 2) {
						if(!arg[1].equals("--args")) {
							System.out.println("Wrong number of arguments, type mv --args for help.");
							continue;
						}
						System.out.println("mv takes two arguments source path and distination path, add -r before source path to move directories.");
						continue;
					}
					if(arg.length == 4) {
						if(!arg[1].equals("-r")) {
							System.out.println("Wrong type of arguments, type mv --args for help.");
							continue;
						}
						c.mvr(arg[2], arg[3]);
						continue;
					}
					c.mv(arg[1], arg[2]);
				}
				else if(arg[0].equals("more")) {
					if(arg.length != 2) {
						System.out.println("Wrong number of arguments, type more --args for help.");
						continue;
					}
					if(arg[1].equals("--args")) {
						System.out.println("more takes one argument, the path to the file.");
						continue;
					}
					c.more(arg[1]);
				}
				else if(arg[0].equals("less")) {
					if(arg.length != 2) {
						System.out.println("Wrong number of arguments, type less --args for help.");
						continue;
					}
					if(arg[1].equals("--args")) {
						System.out.println("less takes one argument, the path to the file.");
						continue;
					}
					c.less(arg[1]);
				}
				else if(arg[0].equals("greb")) {
					if(arg.length == 1 || arg.length > 3) {
						System.out.println("Wrong number of arguments, type greb --args for help.");
						continue;
					}
					if(arg[1].equals("--args")) {
						System.out.println("greb takes two argument, the word and the path to the file.");
						continue;
					}
					c.greb(arg[1], arg[2]);
				}
				else if(arg[0].equals("cp")) {
					if(arg.length == 1 || arg.length > 4) {
						System.out.println("Wrong number of arguments, type cp --args for help.");
						continue;
					}
					if(arg[1].equals("--args")) {
						System.out.println("cp takes two arguments the source file and destination directory.\nor you can use cp -r [source directory] [destination director] to cp directories recursivly.");
						continue;
					}
					if(arg[1].equals("-r")) {
						if(arg.length != 4) {
							System.out.println("Wrong number of arguments, type cp --args for help.");
							continue;
						}
						c.cpr(arg[2] , arg[3]);
						continue;
					}
					c.cp(arg[1], arg[2]);
				}
				else if(arg[0].equals("find")) {
					if(arg.length == 1 || arg.length > 3) {
						System.out.println("Wrong number of arguments, type find --args for help.");
						continue;
					}
					if(arg[1] == "--args") {
						System.out.println("find takes two arguments the file name and the file path.");
						continue;
					}
					c.find(arg[1], arg[2]);
				}
				else if(arg[0].equals("date")){
					if(arg.length > 2) {
						System.out.println("Wrong number of arguments, type date --args for help.");
						continue;
					}
					if(arg.length == 2) {
						if(!arg[1].equals("--args")) {
							System.out.println("Wrong type of arguments, type date --args for help.");
							continue;
						}
						System.out.println("date takes no arguments.");
						continue;
					}
					Date time = new Date() ;
					System.out.println(time.toString());
				}
				else {
					System.err.println("That is not a terminal Command");
				}

			}
			
		}
		
	}

}