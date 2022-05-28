package prereqchecker;
import java.util.*;

/**
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * AdjListOutputFile name is passed through the command line as args[1]
 * Output to AdjListOutputFile with the format:
 * 1. c lines, each starting with a different course ID, then 
 *    listing all of that course's prerequisites (space separated)
 */
public class AdjList {
    public static void main(String[] args) {

        if ( args.length < 2 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.AdjList <adjacency list INput file> <adjacency list OUTput file>");
            return;
        }

	// WRITE YOUR CODE HERE
        //Set the input file to adjlist.in.
        StdIn.setFile(args[0]);
        //Create a new HashMap, which has a key of courseID and a value of a list of courses' prereqs.
            //In which the course has an edge or edges to it's prerequisite courses.
        //Create an arraylist of keys just in case we can use that to access the courseId's list of Prereqs when printing them out.
            HashMap<String, ArrayList<String>> courseWithPrereqs = new HashMap<>();
            ArrayList<String> courses = new ArrayList<>();
        //Read the number of courses that are in the input file.
        int numOfCourses = StdIn.readInt();
        //For each course.
        for (int i = 0; i < numOfCourses; i++) {
            String courseID = StdIn.readString();
            courses.add(i,courseID);
            ArrayList<String> coursePrereqs = new ArrayList<>();
            courseWithPrereqs.put(courseID, coursePrereqs);
        }
            //Read the courseID.
            //Add that courseID to the arraylist. 
            //Create an empty arraylist.
            //Add the courseID and the empty arraylist in the hashmap as a key-value pair.
        //Read the number of course connections.
        StdIn.readLine();
        int numOfCourseConnections = StdIn.readInt();
        //For each course connection.
        for (int i = 0; i < numOfCourseConnections; i++) {
            String course = StdIn.readString();
            String prereq = StdIn.readString();
            courseWithPrereqs.get(course).add(prereq);
        }
        
        StdOut.setFile(args[1]);
            //Read the course and it's prereq.
            //Use the course as the key to get that key's arraylist.
            //Add the course's prereq to the arraylist of prereqs for that course.
            //Put the arraylist back into the hashmap just in case(Try it out without).
        //Set an output file to adjlist.out, which would create the new file if it does not exist in the directory. 
        //For each of the courses.
        for(int i = 0; i < courses.size(); i++){
            ArrayList<String> prerequisites = courseWithPrereqs.get(courses.get(i));
            StdOut.print(courses.get(i) + " ");
            for (String prerequisite: prerequisites) {
                StdOut.print(prerequisite + " ");
            }
            if(i == courses.size()-1) break;
            StdOut.println();
        }
            //Get the courses' arraylist of prereqs.
            //Print out the course first.
            //For each of the prereqs in the arraylist.
                //PRINT it out, not println with a space in between each course.
                //Maybe once you reach the last prereq in the arraylist, you can simply print without the space. 
                //Else print with space. 
            //Once you reach the last line, then end the process. 
            //Print newline afterwards
    }
}