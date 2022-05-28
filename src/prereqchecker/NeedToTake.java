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
 * NeedToTakeInputFile name is passed through the command line as args[1]
 * Read from NeedToTakeInputFile with the format:
 * 1. One line, containing a course ID
 * 2. c (int): Number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * NeedToTakeOutputFile name is passed through the command line as args[2]
 * Output to NeedToTakeOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class NeedToTake {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java NeedToTake <adjacency list INput file> <need to take INput file> <need to take OUTput file>");
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
     //Set the output file to args[2].
     StdOut.setFile(args[2]);
     //Read the targetCourse
     String targetCourse = StdIn.readString();
     //Read the number of courses.
     int numOfPrereqs = StdIn.readInt();
     //Create an ArrayList of prereqs to add the completed courses to the list of coompleted
     //courses to start out with. 
     ArrayList<String> prereqsCompleted = new ArrayList<>();
     //For each course/prereq you have completed.
     for (int i = 0; i < numOfPrereqs; i++) {
         //Read the course.
         //Add it to the list of prereqs completed. 
         String prereqCompleted = StdIn.readString();
         prereqsCompleted.add(prereqCompleted);
        }
    //Create a PrereqTraversal Object(Also have another hashmap that tells whether the course has been visited or not).
    HashMap<String, Boolean> isCourseVisited = new HashMap<>();
    for (String course : courses) {
        isCourseVisited.put(course, false);
    }
    PrereqTraversal haveToTake = new PrereqTraversal(isCourseVisited);
    //Set the object's graph to the graph you have created.
    haveToTake.setCourseWithPreqs(courseWithPrereqs);
    String[] coursesCompleted = new String[prereqsCompleted.size()];
        for (int i = 0; i < coursesCompleted.length; i++) {
            coursesCompleted[i] = prereqsCompleted.get(i);
        }
        //Call DFS to find EVERY course that you have completed (basically include courses with direct and indirect prerequisites).
        for (String courseCompleted : coursesCompleted) {
            haveToTake.dfsTraversal(courseCompleted, prereqsCompleted);
        }
        //Have another data structure that stores the courses you need to take to take the target course.
        HashSet<String> coursesToTake = new HashSet<>();
        //Call the recursive needToTake Method. 
        haveToTake.needToTake(targetCourse, coursesToTake);
            //Inputs: Target Course, course to take, and courses completed
            //Returns void.
            //Visit that course.
            //Now check each of the course's prereqs.
                //If the course's prereqs have not been completed, keep searching recursively on the prereqs that have NOT been marked yet.
                    //Get the course's prereqs first.
                    //Add one of it's prereqs to the courses you need to take.
                    //Visit one of the course's prereqs. 
                //Else, return back to the previous call (Base case: All of it's prereqs have been completed.)
        //Print out all of the courses you need to take in order to take the target course. 
        for (String requiredCourse : coursesToTake) {
            StdOut.println(requiredCourse);
        }
    }
}