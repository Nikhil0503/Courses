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
 * ValidPreReqInputFile name is passed through the command line as args[1]
 * Read from ValidPreReqInputFile with the format:
 * 1. 1 line containing the proposed advanced course
 * 2. 1 line containing the proposed prereq to the advanced course
 * 
 * Step 3:
 * ValidPreReqOutputFile name is passed through the command line as args[2]
 * Output to ValidPreReqOutputFile with the format:
 * 1. 1 line, containing either the word "YES" or "NO"
 */
public class ValidPrereq {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.ValidPrereq <adjacency list INput file> <valid prereq INput file> <valid prereq OUTput file>");
            return;
        }
	// WRITE YOUR CODE HERE
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
            //Read the course and it's prereq.
            //Use the course as the key to get that key's arraylist.
            //Add the course's prereq to the arraylist of prereqs for that course.
         //Set the input file to args[1].
         StdIn.setFile(args[1]);
         //Read strings twice(1 for course1 and another for course2)
         String course1 = StdIn.readString();
         String course2 = StdIn.readString();
         //Need another hashmap to store whether the course has been visited/marked or not. 
         HashMap<String, Boolean> isCourseVisited = new HashMap<>();
         //For each course in the hashmap that stores each course along with it's associated prereqs. 
             //Store the course in the hashmap that tells whether it has been marked or not, which is false in the beginning.
         for (String course : courses) {
             isCourseVisited.put(course, false);
         }
         //Also Would need a hashset to store the possible prereqs (direct and indirect) for course1. 
         HashSet<String> prerequis = new HashSet<>();
         //Create a prereqtraversal object by passing it's parameters.
         PrereqTraversal graphTraversal = new PrereqTraversal(isCourseVisited);
         graphTraversal.setCourseWithPreqs(courseWithPrereqs);
         //Call the isValidPrereq method for that prereqtraversal object.
            //That returns a String, so have a variable to reference to the String.
         String variable = graphTraversal.isValidPrereq(course1, course2, prerequis);
         //Set a StdOut.file to the output file for validpreq
         StdOut.setFile(args[2]);
         //Output that string to the Output File
         StdOut.print(variable);
    }
}