package PracArrayLinkedList;

public class Multi2 <T extends Comparable<T>>{
	
	MyArrayList<Store> storeList;
	
	public Multi2(){
		storeList = new MyArrayList<Store>(5);
	}
	
	public static void main(String[] args) {
		Multi2<Product> list  = new Multi2<>();
		list.add("나미야 잡화점", new Product("색종이"));
		list.add("개나리 문방구", new Product("유희왕"));
		list.add("나미야 잡화점", new Product("딱풀"));
		list.add("나미야 잡화점", new Product("학종이"));
		list.add("개나리 문방구", new Product("뿌셔뿌셔"));
		list.add("개나리 문방구", new Product("컴퍼스"));
		list.showAll();
	}
	
	private class Store implements Comparable<Store>{
		String storeName;
		MyLinkedList<T> pList = new MyLinkedList<T>();
		public Store(String storeName) {
			this.storeName = storeName;
		}
		@Override
		public int compareTo(Store that) {
			
			return this.storeName.compareTo(that.storeName);
		}
		
		public boolean equals(Store that) {
			return this.storeName.equals(that.storeName);
		}
		
		public String toString() {
			String res  = this.storeName +" : ";
			if(pList.sizeOf()==0) {
				return res + "x 상품없음 x";
			}
			res += pList.toString();
			
			return res;	
		}
	}
	
	static class Product implements Comparable<Product>{
		String pName;
		static int num = 0;
		int pId;
		public Product( String pName) {
			num += 1;
			this.pId  = num;
			this.pName = pName;
		}
		@Override
		public int compareTo(Product that) {
			return this.pName.compareTo(that.pName);
		}

		public boolean equals(Product that){
			return (this.pId == that.pId ) ? true : false; 
		}
		
		public String toString(){
			return pName+"["+pId+"]";
		}
	}
	
	public int indexOf(String s) {
		for(int i=0; i< storeList.sizeOf(); i++) {
			if(storeList.get(i).storeName==s)
				return i;
		}
		return -1;
	}
	
	public void addFirst() {
		
	}
	
	public void addLast() {
		
	}
	
	public Store getStore(String sName) {
		Store store = null;
		for(int i=0; i< storeList.sizeOf(); i++) {
			if(storeList.get(i).storeName.equals(sName)) {
				store = storeList.get(i);
				break;
			}
		}
		return store;
	}
	
	public void sort() {
		storeList.sort();
	}
	
	public void showAll() {
		for(int i=0; i<storeList.sizeOf(); i++) {
			System.out.println(storeList.get(i));
		}
	}
	
	public void add(String s, T p) {
		int index = indexOf(s);
		if(index < 0) {
			Store newStore = new Store(s);
			newStore.pList.addFirst(p);
			storeList.addLast(newStore);
		}else {
			storeList.get(index).pList.addFirst(p);			
		}
		
	}
}
