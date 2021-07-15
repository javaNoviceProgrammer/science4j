package movie_timeline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FullCourse {
	
	String courseName ;
	ArrayList<Lecture> lectures ;
	TimeStampSpecifier specifier ;
	
	int totalSecondsElapsed = 0 ;
	
	public FullCourse(String name, TimeStampSpecifier specifier) {
		this.courseName = name ;
		this.specifier = specifier ;
		this.lectures = new ArrayList<>() ;
	}
	
	public void setName(String courseName) {
		this.courseName = courseName ;
	}
	
	public FullCourse addLecture(Lecture lecture) {
		// set the start time of the lecture
		lecture.setOffset(totalSecondsElapsed) ;
		// set lecture format specifier
		lecture.setFormatSpecifier(specifier) ;
		// update the elapsed time
		totalSecondsElapsed += lecture.getTotalSeconds() ;
		// add to the lectures list
		lectures.add(lecture) ;
		return this ;
	}
	
	/* 
	 * get total seconds of each lecture, add it to the elapsed time,
	 * then convert it to double digit time stamp
	 * 
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(lectures.size()*10) ;
		String desc = "COURSE: %s".formatted(courseName) ;
		sb.append(desc).append("\n") ;
		sb.append("*".repeat(desc.length())).append("\n") ;
		for (Lecture lecture : lectures) {
			sb.append(lecture).append("\n") ;
		}
		return sb.toString() ;
	}
	
	public void saveToFile(String fileName) {
		try(FileWriter writer = new FileWriter(new File(fileName))) {
			writer.write(toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static FullCourse parseTextFile(String fileName, TimeStampSpecifier inputFormat, TimeStampSpecifier outputFormat) {
		FullCourse course = new FullCourse("course", outputFormat) ;
		String line ;
		try {
			Scanner scanner = new Scanner(new File(fileName)) ;
			while(scanner.hasNextLine()) {
				line = scanner.nextLine() ;
				Lecture lecture = Lecture.parseString(line, inputFormat) ;
				course.addLecture(lecture) ;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return course ;
	}

}
