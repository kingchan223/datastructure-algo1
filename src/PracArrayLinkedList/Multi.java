package PracArrayLinkedList;

public class Multi<T extends Comparable<T>> {

	MyArrayList<Group> groupList;

	public Multi() {
		groupList = new MyArrayList<Group>(3);
	}

	public static void main(String[] args) {

		Multi<Student> allGroup = new Multi<Student>();
		allGroup.addGroup("영화가 좋다");
		allGroup.addGroup("발로차사커");
		allGroup.addGroup("MJ락앤롤");
		allGroup.addGroup("바오하오밥");

		allGroup.addMember("영화가 좋다", 60172373, "이찬영");
		allGroup.addMember("발로차사커", 60149090, "김영숙");
		allGroup.addMember("발로차사커", 60210023, "화기");
		allGroup.addMember("MJ락앤롤", 60196335, "장미애");
		allGroup.addMember("MJ락앤롤", 601434533, "정일범");
		allGroup.addMember("MJ락앤롤", 60213434, "최성운");
		allGroup.addMember("바오하오밥", 601854543, "금동엽");
		allGroup.removeMember("MJ락앤롤", 60213434);
		allGroup.showAllGroup();
		allGroup.sort();
		allGroup.sortMember();
		System.out.println();
		System.out.println("After sorting");
		allGroup.showAllGroup();
	}

	public void addMember(String groupName, int stdId, String stdName) {
		Group group = null;
		for (int i = 0; i < groupList.sizeOf(); i++) {
			if (groupList.get(i).groupName.equals(groupName)) {
				group = groupList.get(i);
				break;
			}
		}
		group.stuList.addFirst(new Student(stdName, stdId));
		System.out.println("'" + group.groupName + "'" + " 동아리에 회원 " + stdName + "(" + stdId + ")가 참여하였습니다.");
		System.out.println();

	}

	public void addGroup(String g) {
		groupList.addLast(new Group(g));// groupList는 Group만 받기로 정해짐
		System.out.println("'" + groupList.get(groupList.sizeOf() - 1).groupName + "'" + " 동아리가 동아리list에 추가되었습니다.");
		System.out.println();
	}

	public void removeGroup(String groupName){
		for(int i=0; i<groupList.sizeOf(); i++) {
			if(groupList.get(i).groupName.equals(groupName)) {
				groupList.remove(i);
				break;
			}
		}
	}
	
	public void removeMember(String groupName, int stuId){
		Group group = null;
		for(int i=0; i<groupList.sizeOf(); i++){
			if(groupList.get(i).groupName.equals(groupName)) {
				group = groupList.get(i);
			}
		}
		for(int i=0; i<group.stuList.numOfNode; i++){
			if(group.stuList.get(i).stuId==stuId) {
				group.stuList.remove(i);
			}
		}
	}
	
	public void showAllGroup() {
		for(int i=0; i<groupList.sizeOf();i++) {
			Group group = groupList.get(i);
			System.out.println("<"+group.groupName+">");
			for(int j=0; j<group.stuList.numOfNode;j++) {
				System.out.println(group.stuList.get(j).name);
			}
		}
	}
	
	public void sort() {
		groupList.sort();
	}
	
	public void sortMember() {
		for(int i=0; i<groupList.arrSize();i++){
			groupList.get(i).stuList.sort();
		}
	}

	class Group implements Comparable<Group>{
		String groupName;
		MyLinkedList<Student> stuList;

		public Group(String groupName) {
			this.groupName = groupName;
			this.stuList = new MyLinkedList<>();
		}

		@Override
		public int compareTo(Group that) {
			return this.groupName.compareTo(that.groupName);
		}

		public boolean equals(Group that) {
			return this.groupName.equals(that.groupName);
		}
	}

	static class Student implements Comparable<Student> {
		String name;
		int stuId;

		public Student(String name, int stdId) {
			this.name = name;
			this.stuId = stdId;
		}

		@Override
		public int compareTo(Student Comstudent) {
			if (this.stuId > Comstudent.stuId)
				return 1;
			else
				return -1;
		}

		public String toString() {
			return this.name + "(" + this.stuId + ")";
		}
	}
}
