import java.util.LinkedList;
import java.util.List;

class Box {
    private int postRow, postCol, nMinesArround;
    private boolean mine, open;
    
    public Box(int postRow, int postCol) {
        this.postRow = postRow;
        this.postCol = postCol;
    }

    public int getPostRow() {
        return postRow;
    }

    public void setPostRow(int postRow) {
        this.postRow = postRow;
    }

    public int getPostCol() {
        return postCol;
    }

    public void setPostCol(int postCol) {
        this.postCol = postCol;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public int getnMinesArround() {
        return nMinesArround;
    }

    public void setnMinesArround(int nMinesArround) {
        this.nMinesArround = nMinesArround;
    }

    public void incrementNMinesArround() {
        this.nMinesArround++;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

}

public class Board {
    Box[][] box;
    int nRows, nCols, nBoxes, nOpenBoxes, nMines;

    public Board(int nRows, int nCols, int nMines) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.nMines = nMines;
    }
    private List<Box> getBoxesArround(int postRow, int postCol) {
        List<Box> listaCasillas = new LinkedList<>();
        for (int i = 0; i < 8; i++) {
            int row = postRow, col = postCol;
            switch (i) {
                case 0:  //Arriba
                    row --;
                    break;
                case 1:  // Arriba derecha
                    row --;
                    col++;
                    break;
                case 2:  // Derecha
                    col++;
                    break;
                case 3: // Derecha abajo
                    col++;
                    row++;
                    break;
                case 4: // Abajo
                    row++;
                    break;
                case 5:   // Abajo izquierda
                    row++;
                    col--;
                    break;
                case 6:  // Izquierda
                    col--;
                    break;
                case 7:  // Izquierda arriba
                    row--;
                    col--;
                    break;
            }
            if(row >= 0 && row < this.box.length && col >= 0 && col < this.box[0].length) {
                listaCasillas.add(this.box[row][col]);
            }
        }
        return listaCasillas;
    }

    public void updateNumMinesArround() {
        for(int i = 0; i <box.length; i++) for (int j = 0; j < box[i].length; j++) {
            if (box[i][j].isMine()) {
                List<Box> boxesArround = getBoxesArround(i, j);
                boxesArround.forEach(casilla -> casilla.incrementNMinesArround());
            }
        }
    }

    public void generateMines() {
        int minesGenerated = 0;
        while(minesGenerated != nMines) {
            int row = (int) (Math.random() * box.length);
            int col = (int) (Math.random() * box[0].length);
            if (!box[row][col].isMine()) {
                box[row][col].setMine(true);
                minesGenerated++;
            }
        }
        updateNumMinesArround();
    }

    public void turnOnBoxes() {
        box = new Box[nRows][nCols];
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                box[i][j] = new Box(i, j);
                box[i][j].setPostRow(i);
                box[i][j].setPostCol(j);
            }
        }
        generateMines();
    }

    List<Box> getMinesBoxes() {
        List<Box> casillasConMinas = new LinkedList<>();
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box[i].length; j++) {
                if(box[i][j].isMine()) {
                    casillasConMinas.add(box[i][j]);
                }
            }
        }
        return casillasConMinas;
    }

}