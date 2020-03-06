package customCollection;

import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



class MyCustomCollection<E> extends AbstractList<E> {
	public static final Logger LOGGER1=LogManager.getLogger(MyCustomCollection.class);
     Object[] customList;
    int sizeOfList=10;
    int elementsFilled=0;
   public  MyCustomCollection() {
        customList = new Object[10];
    }


    public E get(int index) {
        @SuppressWarnings("unchecked")
		E e = (E)customList[index];
        LOGGER1.info("get request accepted successfully");
		return e;
    }

    public E set(int index, Object element) {
        @SuppressWarnings("unchecked")
		E oldValue = (E)customList[index];
        customList[index] = element;
        LOGGER1.info("set request accepted successfully");
        return oldValue;
    }

    public int size() {
    	LOGGER1.info("size request accepted successfully");
        return customList.length;
    }
    public boolean add(Object element){

        for(int i=0;i<sizeOfList;i++){
            if(customList[i]==null){
                elementsFilled=i;
                break;
            }
           elementsFilled=i+1;

        }
        if(elementsFilled == sizeOfList){
           Object[] customListCopy = new Object[sizeOfList];
            System.arraycopy(customList,0,customListCopy,0,sizeOfList);
            sizeOfList = sizeOfList+10;
            customList = new Object[sizeOfList];
            System.arraycopy(customListCopy,0,customList,0,customListCopy.length);
            
            customList[elementsFilled] = element;
        }
        customList[elementsFilled] = element;
        
        return true;

    }
    @SuppressWarnings("unchecked")
	public E remove(int index){
         
         E returnElement;
         LOGGER1.info("remove request accepted successfully");
         if(elementsFilled>index){
         returnElement = (E)customList[index];
         for(int i=index;i<elementsFilled;i++)
            customList[i] = customList[i+1];
        elementsFilled=elementsFilled-1;
        return(returnElement);
          }
        else
            return null;

    }
    public void print(){
    	LOGGER1.info("print request accepted successfully");
        for(int i=0;i<=elementsFilled;i++)
            LOGGER1.info(customList[i]+" ");
    }

}
class CustomCollectionDemo{
	public static final Logger LOGGER2=LogManager.getLogger(MyCustomCollection.class);
    public static void main(String[] args){
    	Scanner sc=new Scanner(System.in);
        MyCustomCollection<Integer> collectionList = new MyCustomCollection<Integer>();
        for(int i=100;i<115;i++){
            collectionList.add(i);
        }
        collectionList.print();
        LOGGER2.info("enter an index to be removed from collection:");
        int a=sc.nextInt();
        LOGGER2.info("object removed  :"+collectionList.remove(a));
        LOGGER2.info("enter an index to be accessed from collection:");
        int b=sc.nextInt();
        LOGGER2.info("object accessed :"+collectionList.get(b));
        collectionList.print();
        sc.close();
        
    }
}