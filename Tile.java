import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Tile extends StackPane {
    private int row,col;
    private int count;
    private boolean isMine;
    private boolean isFlag;
    private boolean isOpen;
    private final int tileSize = 40;
    private Rectangle border = new Rectangle(tileSize - 2, tileSize - 2);
    private Text text = new Text();



    ///Constructor   **************************
    public Tile(int rowSet, int colSet){
        row = rowSet;
        col = colSet;

        border.setStroke(Color.LIGHTGRAY);
        border.setFill(Color.DARKGREY);
        text.setFont(Font.font(18));
        text.setVisible(false);

        getChildren().addAll(border, text);

        setTranslateX(row * tileSize);
        setTranslateY(col * tileSize);

    }



//// OPEN TILE
    public boolean openTile (){
        if (isMine()){
            text.setText( "B");
            text.setVisible(true);
            text.setFill(Color.WHITE);
            border.setFill(Color.BLACK);

            return true;
        }
        else{
            border.setFill(null);
            if(getCount()>0) {
                text.setText("" + getCount());
                text.setVisible(true);
            }
            else{
                border.setFill(Color.WHITESMOKE);
            }

        }
        setOpen(true);
        return false;

    }

/// Flagged  Tile
    public void flagTile (){
        if(!isFlag) {
            text.setText( "F");
            text.setFill(Color.WHITE);
            setFlag(true);
            text.setVisible(true);
            border.setFill(Color.DARKRED);
        }
        else{
            text.setText( "");
            setFlag(false);
            text.setVisible(false);
            border.setFill(Color.DARKGREY);
        }

    }


//Getters and Setters ***************************
    public boolean isFlag() { return isFlag; }
    public void setFlag(boolean flag) { isFlag = flag; }
    ////

    public boolean isOpen() { return isOpen; }
    public void setOpen(boolean op) { isOpen = op; }
    ///

    public boolean isMine() { return isMine; }
    public void setMine(boolean mine) { isMine = mine; }
    ////

    public int getRow() { return row; }
    public void setRow(int row) { this.row = row; }
    /////

    public int getCol() { return col; }
    public void setCol(int col) { this.col = col; }
    ////

    public int getCount() { return count; }
    public void setCount() { this.count++; }
}
