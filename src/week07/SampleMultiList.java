package week07;

import java.util.ArrayList;

public class SampleMultiList<E extends Comparable<E>> {
	MyArrayList3<Group> groupList;

	public SampleMultiList() {
		groupList = new MyArrayList3<>(3);
	}
	
	public static void main(String[] args) {
		
		SampleMultiList<StudentInfo> myList = new SampleMultiList<>();
		myList.add("영화동아리", new StudentInfo(131, "Kim"));
		myList.add("미술동아리", new StudentInfo(432, "Hwang"));
		myList.add("락앤롤동아리", new StudentInfo(222, "Cho"));
		myList.add("회화동아리", new StudentInfo(123, "Lee"));
		myList.add("영화동아리", new StudentInfo(321, "Park"));
		myList.add("미술동아리", new StudentInfo(133, "Song"));
		myList.add("회화동아리", new StudentInfo(333, "Choi"));
		myList.add("영화동아리", new StudentInfo(233, "Jung"));
		System.out.println(myList.groupList);
		myList.showLists();
		
		myList.sort();
		System.out.println(myList.getGroup("영화동아리"));

		//myList.removeGroup("영화동아리");
		myList.showLists();
		
	}
	
	private class Group implements Comparable<Group> {
		String groupName;
		MyLinkedList<E> list;// 동아리 회원들의 명단이 있는 list(LinkedList로 만들어져있음)

		public Group(String groupName) {
			this.groupName = groupName;
			list = new MyLinkedList<>();
		}

		@Override
		public int compareTo(Group that) {
			return (this.groupName.compareTo(that.groupName));
		}

		public boolean equals(Group that) {
			return (this.groupName.equals(that.groupName));
		}

		public String toString() {
			String retVal = groupName;
			if (list != null)
				retVal = retVal + " => " + list.toString() + "\n";
			return retVal;
		}
	}
	
	static class StudentInfo implements Comparable<StudentInfo> {
		int id;
		String name;

		public StudentInfo(int idNumber, String nameString) {
			id = idNumber;
			name = nameString;
		}

		// 기본타입(int, float, double등을 compare대상으로 하려면 직접 정의하야한다.
		// 기본타입은 compareTo메소드지원안하기 때
		@Override
		public int compareTo(StudentInfo that) {
			return (this.id > that.id) ? 1 : (this.id < that.id) ? -1 : 0;
		}

		public String toString() {
			return name + "[" + id + "]";
		}
	}

	// <--학생 탈퇴하는 경우-->
	// 인덱스로 삭제
	public int removeStudentInfo(int index) {
		return 0;
	}

//	// 학번으로 삭제
//	public int removeStudentInfo(int  idNumber) {
//
//	}
	// 학생 이름으로 삭제
	public String removeStudentInfo(String name) {
		return null;
	}

	// <--group삭제 되는 경우-->
	public void removeGroup(String GroupName) {
		groupList.remove(getGroup(GroupName));
		
	}

	public void add(String g, E entity) {
		System.out.println("Add : " + g + ", " + entity.toString());

		int index = indexOf(g);
		// 인덱스가 이미 있다는 것은 이미 있는 그룹에 회원을 추가하는 것임.
		if (index >= 0)
			groupList.get(index).list.addFirst(entity);
		// 인덱스가 -1이면 새로운 그룹이 추가되는 것임.
		else {
			groupList.addLast(new Group(g));
//			System.out.println(groupList.toString());
//			System.out.println(groupList.get(groupList.sizeOf() - 1).list);
			groupList.get(groupList.sizeOf() - 1).list.addFirst(entity);
		}
	}

	// groupName으로 index를 찾아서 반환해준다.
	private int indexOf(String g) {
		for (int i = 0; i < groupList.sizeOf(); i++) {
			if (groupList.get(i).groupName == g)
				return i;
		}
		return -1;
	}

	public void showLists() {
		System.out.println("\n< Lists of All Groups >");
		for (int i = 0; i < groupList.sizeOf(); i++) {
			System.out.println(groupList.get(i).toString());
		}
	}

	public void sort() {
		groupList.sort();
	}
	
	public Group getGroup(String groupName) {
		Group group = null;
		for(int i=0; i<groupList.numOfData; i++) {
			if(groupList.get(i).groupName.equals(groupName)) {
				group = groupList.get(i);
				break;
			}
		}
		return group;
	}
}


