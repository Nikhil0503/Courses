package prereqchecker;
import java.util.*;

public class PrereqTraversal {
    private HashMap<String, Boolean> isCourseVisited;
    private HashMap<String, ArrayList<String>> courseWithPreqs;

    public PrereqTraversal(HashMap<String, Boolean> isCourseVisited){
        this.isCourseVisited = isCourseVisited;
    }

    public HashMap<String, Boolean> getIsCourseVisited() {
        return isCourseVisited;
    }

    public HashMap<String, ArrayList<String>> getCourseWithPreqs() {
        return courseWithPreqs;
    }

    public void setIsCourseVisited(HashMap<String, Boolean> isCourseVisited) {
        this.isCourseVisited = isCourseVisited;
    }

    public void setCourseWithPreqs(HashMap<String, ArrayList<String>> courseWithPreqs) {
        this.courseWithPreqs = courseWithPreqs;
    }

    //Create an isValidPrereq method that takes in course 1 and course 2 and returns a String.
    public String isValidPrereq(String course1, String course2, HashSet<String> prerequis){
        dfsTraversal(course2, prerequis);
        String yesOrNo = "";
        if(prerequis.contains(course1)){
            yesOrNo = "NO";
        }else{
            yesOrNo = "YES";
        }
        return yesOrNo;
    }
        //Call the DFS method on course2, which returns void. 
            //For the DFS helper method I create (parameter is the course).
                //Mark the course because I have visited the course. 
                //For each of the course's adjacent vertices/prereqs
                    //If the prereqs has not been visited.
                        //Add that prereq to the list of possible prereqs for course1. 
                        //Call the method on it's prereq now. 
        //After the recursive method:
            //If the hashset contains course1, which may be a valid prereq.
                //Return NO.
            //Else
                //Return YES.
    public void dfsTraversal(String course1, HashSet<String> prerequis){
        Boolean visited = true;
        isCourseVisited.put(course1, visited);
        if(courseWithPreqs.get(course1) != null){
        for (String prerequisite : courseWithPreqs.get(course1)) {
            if(Boolean.FALSE.equals(isCourseVisited.get(prerequisite))){
                prerequis.add(prerequisite);
                dfsTraversal(prerequisite, prerequis);
            }
        }
      }
    }

    public void dfsTraversal(String course, ArrayList<String> prerequis){
        Boolean visited = true;
        isCourseVisited.put(course, visited);
            for (String prerequisite : courseWithPreqs.get(course)) {
                if(Boolean.FALSE.equals(isCourseVisited.get(prerequisite))){
                    prerequis.add(prerequisite);
                    dfsTraversal(prerequisite, prerequis);
                }
            }
    }

    public void eligiblityDFS(String course, List<String> prereqsCompleted){
        Boolean visited = true;
        isCourseVisited.replace(course, visited);
          for(String nextCourse: courseWithPreqs.get(course)){
            if(Boolean.FALSE.equals(isCourseVisited.get(nextCourse))){
                prereqsCompleted.add(nextCourse);
                eligiblityDFS(nextCourse, prereqsCompleted);
            }
        }

     //Inputs: Target Course, course to take
    }
    public void needToTake(String targetCourse, HashSet<String> coursesToTake){
        //Returns void.
        //Visit that course.
        Boolean visited = true;
        isCourseVisited.replace(targetCourse, visited);
        if(courseWithPreqs.get(targetCourse) != null){
          for (String prerequisite : courseWithPreqs.get(targetCourse)){
            if(Boolean.FALSE.equals(isCourseVisited.get(prerequisite))){
                coursesToTake.add(prerequisite);
                needToTake(prerequisite, coursesToTake);
            }
                //Get the course's prereqs first.
                //Add one of it's prereqs to the courses you need to take.
                //Visit one of the course's prereqs. 
        }
     }
    }
}