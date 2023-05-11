import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class TreeReversal {

    public static void main (String[] args) throws Exception{
    	
    	// Paths.get creates a path object representing the current directory
        Path currentPath = Paths.get(System.getProperty("user.dir")); // User.dir gets the CWD of the running program
        
        // Calls our listDir method to begin traversal and gives an initial depth of 0, and our starting directory
        listDir(currentPath, 0); // All the "0" (depth) does here is indent the output. It does not actually send you deeper into the file system.
    }

    public static void listDir(Path path, int depth) throws Exception { // "depth" is simply giving the illusion of depth by printing spaces.
        
    	// Here we are using the BFA class to get the attributes of the file type.
    	BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
        
        // If file found is a directory, open, and repeat until we reach the end.
	    if(attr.isDirectory()){
	    	
	    	// List all of the directories/files in the CWD
	        DirectoryStream<Path> DirPaths = Files.newDirectoryStream(path);
	        
	        // This prints the system
	        System.out.println(spacesForDepth(depth) + " > " + path.getFileName());
	
	        for(Path tempPath: DirPaths){ // For each item in the CWD ..
	        	if (!tempPath.getFileName().toString().startsWith(".")) { // This line removes items like .classpath, .gitignore etc.
	        		listDir(tempPath, depth + 1); // Recursively search files, indenting further each time.
	        	}
	        }
	        
	    } else { // If it isn't a directory, print the file name
	        System.out.println(spacesForDepth(depth) + " - " + path.getFileName());
	    }
    }

    public static String spacesForDepth(int depth){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < depth; i++){
            builder.append("   ");
        }
        return builder.toString();
    }

}
