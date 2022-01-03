import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

import model.Record;

public class RatingStudents {
	
	static ArrayList<Record> data;
	static Scanner sc;
	
	public static void main(String[] args) {
		addData();
		showAllData();
		Scanner sc = new Scanner(System.in);
		int choice = 1;
		while(choice == 1 || choice == 2)
		{
			System.out.println("1.Enter Student Name \n 2.Enter Subject Name \n 3.Exit \n Enter Choice");
			choice = sc.nextInt();
			sc.nextLine();
			if(choice == 1)
			{
				System.out.println("Enter Student Name : ");
				String name = sc.nextLine();
				getStudentRecord(name);
			}
			else if(choice == 2)
			{
				System.out.println("Enter Subject Name : ");
				String name = sc.nextLine();
				getSubjectRecord(name);
			}
			else
				break;
			
		}
		}
	
	private static void getSubjectRecord(String name)
	{
		ArrayList<Integer> ind = searchforSubject(name);
		Set<String> stu = searchforStudent(ind);
		System.out.println(ind);
		System.out.println(stu);
		System.out.println(name + " ");
		System.out.println("Student Name -- Test -- Quiz -- Project -- Lab -- Overall");
		for(String s:stu)
		{
			ArrayList<Float> t = new ArrayList<Float>();
			ArrayList<Float> q = new ArrayList<Float>();
			ArrayList<Float> p = new ArrayList<Float>();
			ArrayList<Float> l = new ArrayList<Float>();
			for(int i:ind)
			{
				if(data.get(i).getStudentName().equals(s))
				{
					String ac = data.get(i).getAssignmentCategory();
					if(ac.startsWith("test"))
						t.add(data.get(i).getPoints());
					else if(ac.startsWith("lab"))
						l.add(data.get(i).getPoints());
					else if(ac.startsWith("pro"))
						p.add(data.get(i).getPoints());
					else
						q.add(data.get(i).getPoints());
				}
			}
			float ts = 0,qs = 0,ps = 0,ls = 0;
			for(float x:t)
				ts = ts + x;
			for(float x:q)
				qs = qs + x;
			for(float x:p)
				ps = ps + x;
			for(float x:l)
				ls = ls + x;
			ts = (float) ((ts/t.size())*0.4);
			ls = (float) ((ls/l.size())*0.2);
			ps = (float) ((ps/p.size())*0.3);
			qs = (float) ((qs/q.size())*0.1);
			ts = (float) (Math.round(ts * 100.0) / 100.0);
			qs = (float) (Math.round(qs * 100.0) / 100.0);
			ls = (float) (Math.round(ls * 100.0) / 100.0);
			ps = (float) (Math.round(ps * 100.0) / 100.0);
			float ov = ts+ls+ps+qs;
			System.out.print(s + "--");
			System.out.print(ts + "--");
			System.out.print(qs + "--");
			System.out.print(ps + "--");
			System.out.print(ls + "--");
			System.out.print(ov);
			System.out.println();
		
			}
	}
	
	private static void getStudentRecord(String name)
	{
		ArrayList<Integer> ind = searchStudent(name);
		Set<String> sub = searchSubject(ind);
		for(String s:sub)
		{
			ArrayList<Float> t = new ArrayList<Float>();
			ArrayList<Float> q = new ArrayList<Float>();
			ArrayList<Float> p = new ArrayList<Float>();
			ArrayList<Float> l = new ArrayList<Float>();
			for(int i:ind)
			{
				if(data.get(i).getSubjectName().equals(s))
				{
					String ac = data.get(i).getAssignmentCategory();
					if(ac.startsWith("test"))
						t.add(data.get(i).getPoints());
					else if(ac.startsWith("lab"))
						l.add(data.get(i).getPoints());
					else if(ac.startsWith("pro"))
						p.add(data.get(i).getPoints());
					else
						q.add(data.get(i).getPoints());
				}
			}
			float ts = 0,qs = 0,ps = 0,ls = 0;
			for(float x:t)
				ts = ts + x;
			for(float x:q)
				qs = qs + x;
			for(float x:p)
				ps = ps + x;
			for(float x:l)
				ls = ls + x;
			ts = (float) ((ts/t.size())*0.4);
			ls = (float) ((ls/l.size())*0.1);
			ps = (float) ((ps/p.size())*0.3);
			qs = (float) ((qs/q.size())*0.2);
			ts = (float) (Math.round(ts * 100.0) / 100.0);
			qs = (float) (Math.round(qs * 100.0) / 100.0);
			ls = (float) (Math.round(ls * 100.0) / 100.0);
			ps = (float) (Math.round(ps * 100.0) / 100.0);
			float ov = ts+ls+ps+qs;
			System.out.print(s + "--");
			System.out.print(ts + "--");
			System.out.print(qs + "--");
			System.out.print(ps + "--");
			System.out.print(ls + "--");
			System.out.print(ov);
			System.out.println();
		
			}
	}
	
	private static HashSet<String> searchSubject(ArrayList<Integer> student)
	{
		Set<String> temp  = new HashSet<String>();
		for(Integer i : student)
		{
			temp.add(data.get(i).getSubjectName());
		}
		return (HashSet<String>) temp;
	}
	
	private static ArrayList<Integer> searchStudent(String studentName) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		int i = 0;
		for(Record rec : data)
		{
			if(rec.getStudentName().equals(studentName))
				temp.add(i);
			i++;
		}
		return temp;
	}

	private static void showAllData() {
		int i = 1;
		for(Record rec : data)
		{
			System.out.println("Record Number "+i);
			System.out.println(rec.getStudentName());
			System.out.println(rec.getSubjectName());
			System.out.println(rec.getAssignmentCategory());
			System.out.println(rec.getDateOfSubmission());
			System.out.println(rec.getPoints());
			System.out.println("----------------------");
			i+=1;
		}
	}

	private static void addData() {
		data = new ArrayList<Record>();
		data.add(createRecord("Ananth", "Electro Fields", "test_1", "21-Jul-16", 100.0f));
		data.add(createRecord("Bhagath", "Electro Fields", "test_1", "21-Jul-16", 78.0f));
		data.add(createRecord("Chaya", "Electro Fields", "test_1", "21-Jul-16", 68.0f));
		data.add(createRecord("Esharath", "Electro Fields", "test_1", "21-Jul-16", 87.0f));
		data.add(createRecord("Bhagath", "Electro Fields", "quiz_1", "22-Jul-16", 20));
		data.add(createRecord("Chaya", "Electro Fields", "lab_1", "23-Jul-16", 10));
		data.add(createRecord("Ananth", "Electro Fields", "project_1", "24-Jul-16", 100));
		data.add(createRecord("Davanth", "Electro Fields", "project_1", "24-Jul-16", 100));
		data.add(createRecord("Bhagath", "Electro Fields", "quiz_2", "25-Jul-16", 50));
		data.add(createRecord("Ananth", "Electro Fields", "quiz_1", "26-Jul-16", 100));
		data.add(createRecord("Bhagath", "Electro Fields", "lab_1", "27-Jul-16", 10));
		data.add(createRecord("Chaya", "Electro Fields", "project_1", "28-Jul-16", 100));
		data.add(createRecord("Bhagath", "Electro Fields", "project_1", "28-Jul-16", 100));
		data.add(createRecord("Ananth", "Computing Techniques", "test_1", "29-Jul-16", 86));
		data.add(createRecord("Ananth", "Electro Fields", "quiz_2", "29-Jul-16", 100));
		data.add(createRecord("Bhagath", "Computing Techniques", "project_1", "30-Jul-16", 100));
		data.add(createRecord("Ananth", "Electro Fields", "lab_1", "30-Jul-16", 100));
		data.add(createRecord("Chaya", "Computing Techniques", "quiz_1", "31-Jul-16", 20));
		data.add(createRecord("Ananth", "Electro Fields", "test_2", "1-Aug-16", 100));
		data.add(createRecord("Chaya", "Electro Fields", "test_2", "1-Aug-16", 92));
	}
	
	private static Record createRecord(String studentName, String subjectName, String ac, String dos, float points )
	{
		Record temp = new Record();
		temp.setStudentName(studentName);
		temp.setAssignmentCategory(ac);
		temp.setDateOfSubmission(dos);
		temp.setPoints(points);
		temp.setSubjectName(subjectName);
		return temp;
	}
	
	private static HashSet<String> searchforStudent(ArrayList<Integer> subject)
	{
		Set<String> temp  = new HashSet<String>();
		for(Integer i : subject)
		{
			temp.add(data.get(i).getStudentName());
		}
		return (HashSet<String>) temp;
	}
	
	private static ArrayList<Integer> searchforSubject(String subjectName) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		int i = 0;
		for(Record rec : data)
		{
			if(rec.getSubjectName().equals(subjectName))
				temp.add(i);
			i++;
		}
		return temp;
	}
}
