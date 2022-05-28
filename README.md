# CSCourseChecker
A graph data structure that has a list of courses for the CS major in Rutgers 
that each of the course's prereqs are mapped to the course if the course has 
any prereqs. Using the DFS traversal algorithm, a user can check what courses
can take next based on the courses the user has completed. Also, if the user
wants to take a CS course, then they would need to complete the required indirect
and direct prerequisites of the CS course they wanna take to take that course 
either in a scheduled plan per semester or in a list of courses.

Based on the given input files, the user can edit those input files to 
produce a given output based on the method they wanna perform. This assignment
is in Java, and I used the following built-in collections to program this
assignment:
  1. HashMap that takes in a course as a key and an ArrayList of the course's
  prereqs as the value corresponding to the key.
  2. TreeMap to display the courses the user needs to take in order to take a course
  that the user wants to take by displaying the scheduled plan of
  required courses in a semester format.