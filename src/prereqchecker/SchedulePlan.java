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
 * SchedulePlanInputFile name is passed through the command line as args[1]
 * Read from SchedulePlanInputFile with the format:
 * 1. One line containing a course ID
 * 2. c (int): number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * SchedulePlanOutputFile name is passed through the command line as args[2]
 * Output to SchedulePlanOutputFile with the format:
 * 1. One line containing an int c, the number of semesters required to take the course
 * 2. c lines, each with space separated course ID's
 */
public class SchedulePlan {
    public static void main(String[] args) {
        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.SchedulePlan <adjacency list INput file> <schedule plan INput file> <schedule plan OUTput file>");
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
    //Set the input and output file.
    StdIn.setFile(args[1]);
    StdOut.setFile(args[2]);
    //Read the target course.
    String targetCourse = StdIn.readString();
    //Read the number of courses you have completed.
    int numOfCoursesCompleted = StdIn.readInt();
    //Create a data structure that has the list of courses you have completed. 
    ArrayList<String> prereqsCompleted = new ArrayList<>();
   //For each course/prereq you have completed.
   for (int i = 0; i < numOfCoursesCompleted; i++) {
    //Read the course.
    //Add it to the list of prereqs completed. 
    String prereqCompleted = StdIn.readString();
    prereqsCompleted.add(prereqCompleted);
   }
    //Create a data structure that tells whether the course has been visited in the graph or not.
    HashMap<String, Boolean> isCourseVisited = new HashMap<>();
        for (String course : courses) {
            isCourseVisited.put(course, false);
        }
    //Create an object -> Pass in that isVisited data structure.
    PrereqTraversal scheduleTraversal = new PrereqTraversal(isCourseVisited);
    scheduleTraversal.setCourseWithPreqs(courseWithPrereqs);
    //Call the dfs method for each course you have completed to get all of the courses you have completed.
    String[] coursesCompleted = new String[prereqsCompleted.size()];
        for (int i = 0; i < coursesCompleted.length; i++) {
            coursesCompleted[i] = prereqsCompleted.get(i);
        }
        //Call DFS to find EVERY course that you have completed (basically include courses with direct and indirect prerequisites).
        for (String courseCompleted : coursesCompleted) {
            scheduleTraversal.eligiblityDFS(courseCompleted, prereqsCompleted);
        }
    //Create another data structure that consist of the courses that you need to take in order to take the target course.
        //Call dfs on the target course.
    HashSet<String> needToTake = new HashSet<>();
    scheduleTraversal.needToTake(targetCourse, needToTake);
    //Create another data structure that takes in a numbered semester as a key and a list of courses for that semester
    //as the value.
    TreeMap<Integer, ArrayList<String>> coursesTakenPerSemester = new TreeMap<>();
    //Starting with semester 1.
    int semesterNum = 1;
    //While you have not completed the list of courses you need to take in order to take the target course.
    while(!needToTake.isEmpty()){
        //Create an arraylist of possible courses you can take for that semester.
        ArrayList<String> listOfCoursesForSemester = new ArrayList<>();
        //For each course in the graph. 
        //Similar logic to eligible, but instead add the courses you are eligible to take next in the arraylist.
        for (String courseYouAreTryingToTakeNext : courseWithPrereqs.keySet()) {
            if(courseWithPrereqs.get(courseYouAreTryingToTakeNext).isEmpty() && !prereqsCompleted.contains(courseYouAreTryingToTakeNext)){
                listOfCoursesForSemester.add(courseYouAreTryingToTakeNext);
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
                if(eligibleOrNot == true) listOfCoursesForSemester.add(courseYouAreTryingToTakeNext);
            }
        }
        String[] listOfCoursesForSem = new String[listOfCoursesForSemester.size()];
            for (int i = 0; i < listOfCoursesForSem.length; i++) {
                listOfCoursesForSem[i] = listOfCoursesForSemester.get(i);
            }
            //For each course you are eligible to take for that semester, add it to the list of courses completed. 
                for (String possibleCourseForSem : listOfCoursesForSem) {
                    //If and only if these are the courses you need to take in order to take the target course. 
                    if(needToTake.contains(possibleCourseForSem)){
                        prereqsCompleted.add(possibleCourseForSem);
                        //If the course you are eligible to take for that semester is one of the courses you need to take for that semester.
                            //Remove it from the list of courses that you need to take.
                        needToTake.remove(possibleCourseForSem);
                    }else{
                        //Else, remove it from the list of courses you take for that semester.
                        listOfCoursesForSemester.remove(possibleCourseForSem);
                    }
                }
        //Add the semester and the list of courses for that semester in the treemap.
        coursesTakenPerSemester.put(semesterNum, listOfCoursesForSemester);
        //Move on to next semester.   
        semesterNum++;
    }
    //Print out the num of semesters-1 in a new line.
    StdOut.println(semesterNum-1);
    //For each semester
    for (Integer semester : coursesTakenPerSemester.keySet()) {
        ArrayList<String> coursesForThatSemester = coursesTakenPerSemester.get(semester);
        for (String course : coursesForThatSemester) {
            //Print out the courses you need to take in order to take the target course.
            StdOut.print(course + " ");
        }
        StdOut.println();
    }
    }
}