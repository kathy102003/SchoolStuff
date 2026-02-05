import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class MyComparator implements Comparator<Row2>{
    @Override
    public int compare(Row2 o1, Row2 o2) {
        return o1.getID() - o2.getID();
    }
}

public class A1_2 {
    public static void main(String[] args) throws FileNotFoundException {
        File myFile = new File("data1.txt");
        Scanner scnr = new Scanner(myFile);
        Table2 newTable = new Table2();
        while (scnr.hasNextLine()){
            String data = scnr.nextLine();
            newTable.addRow(data);
        }
        newTable.sortTableNaturalOrder();
        System.out.println("Natural order table ");
        newTable.printTable(0);
        System.out.println();

        MyComparator newComparator = new MyComparator();
        newTable.sortTableUsingID(newComparator);
        System.out.println("Custom order table ");
        newTable.printTable(0);
        System.out.println();

        System.out.println("12 rows table ");
        newTable.printTable(12);
        System.out.println();

        Table2 table2 = newTable.select("Lorem ipsum dolor sit amet");
        table2.printTable(0);
    }
}

class Table2{
    private ArrayList<Row2> myTableList;

    public Table2(){  myTableList = new ArrayList<>(); }

    public void addRow(String s){
        Row2 newRow = new Row2(numberOfRows(),s);
        myTableList.add(newRow);
    }

    public void sortTableNaturalOrder(){
        Collections.sort(myTableList);
    }

    public void sortTableUsingID(Comparator<Row2> comparator){
        myTableList.sort(comparator);
    }

    public int numberOfRows(){ return myTableList.size(); }


    public Table2 select(String s){
        Table2 returnTable = new Table2();
        for (int i = 0; i < numberOfRows(); i++){
            if (myTableList.get(i).getText().contains(s)) 
                returnTable.addRow(myTableList.get(i).getText());
        }
        return returnTable;
    }

    /*
    r is the number of rows to print. 
    If r is 0, the whole table is printed, otherwise the first r rows are printed. 
    */
    public void printTable(int r){
        if (r == 0) {
            if (numberOfRows() == 0) System.out.println("Empty table");
            else {
                for (Row2 row: myTableList){ 
                    System.out.println(row.toString()); 
                }
            }
        }

        if (r != 0){
            int maxPossibleRows = Math.min(r,numberOfRows());
            for (int i = 0; i < maxPossibleRows; i++){
                System.out.println(myTableList.get(i).toString());
            }
        }
    }
}

class Row2 implements Comparable<Row2>{
    private int id;
    private String text;

    public Row2(int id, String text){
        this.id = id;
        this.text = text;
    }
    public int getID(){return id;}
    public void setID(int id){this.id = id;}
    public String getText(){return text;}
    public void setText(String text){this.text = text;}
    public String toString(){ return "ID: " + this.id + " | " + "Text: " + this.text; }
    @Override
    public int compareTo(Row2 other) { 
        return this.text.compareTo(other.getText()); 
        // return this.id - other.getID();
    }
}

