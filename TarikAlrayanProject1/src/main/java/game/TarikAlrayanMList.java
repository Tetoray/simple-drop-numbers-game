
package game;

/** 
* @file Project1
* @description game to deal with liked list
* @assignment 1. 
* @date  31/03/2022 
* @author yazar TARIK ALRAYAN tarik.alrayan@fsm.stu.edu.tr
*/

public class TarikAlrayanMList<T> {

    private final int numRows;
    private final int numCols;
    TarikAlrayanNode<T> head;
    private TarikAlrayanNode<T> tail;

    //constractor takes the number of column and rows of the game grid
    public TarikAlrayanMList(int rows, int cols) {
        numRows = rows;
        numCols = cols;
    }

    //this method adding the nodes to the list then print them
    public void addNode(int column, T value) {

            if (CheckAddNode(value, column)) {
                printGrid();
                merge(column);

            } 
            else {
                System.out.println("error while adding the node");
            }
    }

    //this method check the adding operation and if evrey thing correct it will added the node to the list
    private boolean CheckAddNode(T value, int column) {
        if (column > numCols) {
            System.out.println(" Error the column index you entered is undifiend");
        }
        int row = 0;
        TarikAlrayanNode temp = head;
        TarikAlrayanNode down = temp;
        TarikAlrayanNode prev = temp;
        while (temp != null) {
            if (temp.getColumnIndex() == column) {
                row++;
                prev = down;
                down = temp;
            }
            temp = temp.getNext();
        }

        if (row >= numRows) {
            System.out.println("Eror While Adding Nodes To List !! Out Of Index The Game Is Over ;)");
            return false;
        } else {
            TarikAlrayanNode newNode = new TarikAlrayanNode(value, column);
            addLast(newNode);
            if (row != 0) {
                down.setUp(newNode);
            }

            return true;
        }
    }

    
    // this method return the grid as a string to use it in the qui 
    public String getGridString() {
        //using string bilders so ican add all row by row 
        //the game drop numbers start from down to up that why i neede to start from down to up in for loop
        //in this loop i go around the list and check if the current column  repetition number is the same of the row number that mean in this colum there is elements to row number 
        //if it equal im gonna save the value using prev then i print the values of it if it not equal print ___ present null cell 
        StringBuilder sb = new StringBuilder();
        TarikAlrayanNode temp = head;
        TarikAlrayanNode prev = temp;
        for (int i = numRows - 1; i >= 0; i--) {
            for (int j = 0; j < numCols; j++) {
                temp = head;
                int rep = -1;
                while (temp != null) {
                    if (temp.getColumnIndex() == j) {
                        rep++;
                        if (rep == i) {
                            prev = temp;
                            break;
                        }
                    }
                    prev = temp;
                    temp = temp.getNext();
                }
                if (rep == i) {
                    if ((int) prev.getValue() > 99) {
                        sb.append("|").append(prev.getValue()).append("|");
                    } else if ((int) prev.getValue() > 9) {
                        sb.append("|_").append(prev.getValue()).append("|");
                    } else {
                        sb.append("|__").append(prev.getValue()).append("|");
                    }

                } else {
                    sb.append("|___|");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // this methode used to print the grid to the consele in main class 
    private void printGrid() {
        TarikAlrayanNode temp = head;
        TarikAlrayanNode prev = temp;
        //the game drop numbers start from down to up that why i neede to start from down to up in for loop
        //in this loop i go around the list and check if the current column  repetition number is the same of the row number that mean in this colum there is elements to row number 
        //if it equal im gonna save the value using prev then i print the values of it if it not equal print ___ present null cell 
        for (int i = numRows - 1; i >= 0; i--) {
            for (int j = 0; j < numCols; j++) {
                temp = head;
                int rep = -1;
                while (temp != null) {
                    if (temp.getColumnIndex() == j) {
                        rep++;
                        if (rep == i) {
                            prev = temp;
                            break;
                        }

                    }
                    prev = temp;
                    temp = temp.getNext();

                }
                if (rep == i) {
                    if ((int) prev.getValue() > 99) {
                        System.out.print("|"+prev.getValue()+"|");
                    } else if ((int) prev.getValue() > 9) {
                        System.out.print("|_"+prev.getValue()+"|");
                    } else {
                        System.out.print("|__"+prev.getValue()+"|");
                    }

                } else {
                    System.out.print("|___|");
                }
            }
            System.out.println("");
        }
        System.out.println("----------------------------------------------");
    }

    //this method controls the merge operation 
    private boolean merge(int column) {
        boolean stat = false;
        TarikAlrayanNode temp;
        TarikAlrayanNode last;
        TarikAlrayanNode prev;
        int rep;
        int tempRep;

        //it will continue until there is no numbers to merge 
        while (stat == false) {
            rep = -1;
            tempRep = -1;
            temp = head;
            last = null;
            prev = null;

            //first im gonna take the repetition number for the column it must check
            while (temp != null) {
                if (temp.getColumnIndex() == column) {
                    rep++;
                }
                temp = temp.getNext();
            }

            //rest the temp to the head and repetition to temp
            temp = head;
            tempRep = rep;
            rep = -1;
            
            //i need to take last two node in this column to compare them so im going to go throw the repetition agin until ifound the last and the previous node 
            while (temp != null) {
                if (temp.getColumnIndex() == column) {
                    rep++;
                    if (rep == tempRep - 1) {
                        prev = temp;
                    } else if (rep == tempRep) {
                        last = temp;

                    }

                }

                temp = temp.getNext();
            }

            if (prev == null || last == null) {
                return true;

            }

            //if the last two nodes value equal its going to delete the top one and the previous node will equal valuex2 then print
            if (prev.getValue() == last.getValue()) {
                System.out.println("in column " + column + " merge " + prev.getValue() + "->" + last.getValue());
                prev.setValue((Integer) last.getValue() * 2);
                delete(last);
                printGrid();
            } else {
                stat = true;
            }

        }
        return stat;
    }

    //this method delete node from the list
    private void delete(TarikAlrayanNode last) {
        TarikAlrayanNode current = head;
        TarikAlrayanNode previous = null;
        //search through the list
        while (current != null) {
            //if the target has been found at the current position
            if (current == last) {
                if (previous == null) { // start of the list
                    head = current.getNext();
                } else {
                    previous.setNext(current.getNext());
                }
            }
            previous = current;
            current = current.getNext();
        }

    }

      
    //add last method adding the mode to the last of the list
    public void addLast(TarikAlrayanNode newNode) {
        if (head == null) {
            head = newNode;
        } else {
            TarikAlrayanNode temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(newNode);
        }

    }

}
