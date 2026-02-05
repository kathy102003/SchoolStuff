import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class A1 {
    public static void main(String[] args) throws FileNotFoundException {
        File myFile = new File("data2.txt");
        Scanner scnr = new Scanner(myFile);
        Table newTable = new Table();
        while (scnr.hasNextLine()){
            String data = scnr.nextLine();
            newTable.addRow(data);
        }
        newTable.printTable(0);
        newTable.printTable(12);

        Table table2 = newTable.select("Lorem ipsum dolor sit amet");
        table2.printTable(0);
    }
}

class Table implements Comparator<Row>{
    private ArrayList<Row> myTableList;

    public Table(){  myTableList = new ArrayList<>(); }

    public void addRow(String s){
        Row newRow = new Row(0,s);
        if (numberOfRows() == 0) {myTableList.add(0,newRow);}
        else {
            int index = numberOfRows()-1;
            Row rowToCompare = myTableList.get(index);

            while (index >= 0 && compare(newRow, rowToCompare) < 0) {
                rowToCompare.setID(index+1);
                index--;
                if (index >= 0) rowToCompare = myTableList.get(index);
            }
            myTableList.add(index+1,newRow);
        }

        for (int id = 0; id < numberOfRows(); id++){
            myTableList.get(id).setID(id);
        }
    }

    public int numberOfRows(){ return myTableList.size(); }
   
    @Override
    public int compare(Row o1, Row o2) { return o1.compareTo(o2); }

    public Table select(String s){
        Table returnTable = new Table();
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
                for (Row row: myTableList){ 
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

class Row implements Comparable<Row>{
    private int id;
    private String text;

    public Row(int id, String text){
        this.id = id;
        this.text = text;
    }

    public int getID(){return id;}
    public void setID(int id){this.id = id;}

    public String getText(){return text;}
    public void setText(String text){this.text = text;}

    public String toString(){ return "ID: " + this.id + " | " + "Text: " + this.text; }

    @Override
    public int compareTo(Row other) { return this.text.compareTo(other.getText()); }
}

