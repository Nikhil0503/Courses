package prereqchecker;

import java.lang.Boolean;
import java.util.*;

/**
 * 
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
 * EligibleInputFile name is passed through the command line as args[1]
 * Read from EligibleInputFile with the format:
 * 1. c (int): Number of courses
 * 2. c lines, each with 1 course ID
 * 
 * Step 3:
 * EligibleOutputFile name is passed through the command line as args[2]
 * Output to EligibleOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class Eligible {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.Eligible <adjacency list INput file> <eligible INput file> <eligible OUTput file>");
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
         //Create a HashMap that has a key course and a value of true/false that tells whether you are eligible to take the course or not.
         HashMap<String, Boolean> eligibleToTakeCourse = new HashMap<>(); 
        //Create a PrereqTraversal Object(Also have another hashmap that tells whether the course has been visited or not).
        HashMap<String, Boolean> isCourseVisited = new HashMap<>();
        for (String course : courses) {
            isCourseVisited.put(course, false);
        }
        PrereqTraversal eligibilityTraversal = new PrereqTraversal(isCourseVisited);
        //Set the object's graph to the graph you have created.
        eligibilityTraversal.setCourseWithPreqs(courseWithPrereqs);
        String[] coursesCompleted = new String[prereqsCompleted.size()];
        for (int i = 0; i < coursesCompleted.length; i++) {
            coursesCompleted[i] = prereqsCompleted.get(i);
        }

        ArrayList<String> eligibleCoursesToTakeNext = new ArrayList<>();
        for (String courseCompleted : coursesCompleted) {
            eligibilityTraversal.eligiblityDFS(courseCompleted, prereqsCompleted);
        }
         //For each course in the graph:
                //As long as that course has not been completed/taken yet. 
                    //Have a variable that tells whether you are eligible to take that course and for now, it's true.
                    //Go through the list of prereqs(For each prereq for that course).
                        //If the list of prereqs contains the prereq you are on, skip and move on.
                        //If you find a prereq that has been not completed yet
                            //Not eligible to take that course.
                    //Tell whether you are eligible to take the course or not.
        for (String courseYouAreTryingToTakeNext: courseWithPrereqs.keySet()) {
            if(courseWithPrereqs.get(courseYouAreTryingToTakeNext).isEmpty() && !prereqsCompleted.contains(courseYouAreTryingToTakeNext)){
                eligibleToTakeCourse.put(courseYouAreTryingToTakeNext, true);
                continue;
            }

            ArrayList<String> listOfPrerequisites = courseWithPrereqs.get(courseYouAreTryingToTakeNext);
            if(!prereqsCompleted.contains(courseYouAreTryingToTakeNext)){
                Boolean eligibleOrNot = null;
                for (String prereq: listOfPrerequisites){
                    if(!prereqsCompleted.contains(prereq)){
                        eligibleOrNot = false;
                        break;
                    }else{
                        eligibleOrNot = true;
                    }
                }
                eligibleToTakeCourse.put(courseYouAreTryingToTakeNext, eligibleOrNot);
                //System.out.println(courseYouAreTryingToTakeNext + " Eligible: " + eligibleToTakeCourse.get(courseYouAreTryingToTakeNext));
            }
        }
            //For each of the hashmap's eligibility keys
        for(String possibleCourse : eligibleToTakeCourse.keySet()){
            //If you are eligible to take that course.
            if(Boolean.TRUE.equals(eligibleToTakeCourse.get(possibleCourse))){
                 //Print that course in a new line to the output file. 
                StdOut.println(possibleCourse);
            }
        }
     }
}