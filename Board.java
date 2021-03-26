import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Board extends Application  {

    private final int width = 600;
    private final int height = 600;
    private final int tileSize = 40;
    private final int xTiles = width / tileSize;
    private final int yTiles = height / tileSize;
    private Tile[][] board;

    //Getters and setters

    //////Constructor******************
    public Parent createBord() {
        board = new Tile[xTiles][yTiles];
        Pane root = new Pane();
        root.setPrefSize(width, height);

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                Tile t = new Tile(row, col);
                t.setOnMouseClicked(event ->
                {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        t.openTile();
                        openZeroMine(t);
                        gameOver(t.openTile());
                    } else if (event.getButton() == MouseButton.SECONDARY) {
                        t.flagTile();
                    }
                });

                root.getChildren().add(t);//add to GUI
                board[row][col] = t;//add to array
            }
        }

        generateMines();
        countNeighbouringMines();
        return root;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createBord());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    //generate 10 mines***************
    public void generateMines() {
        int counter = 0;
        while (counter < 20) {
            int mineRow = (int) (Math.random() * board.length);
            int mineCol = (int) (Math.random() * board[0].length);
            while ((board[mineRow][mineCol].isMine())) {
                mineRow = (int) (Math.random() * board.length);
                mineCol = (int) (Math.random() * board[0].length);
            }
            System.out.println("mine");
            board[mineRow][mineCol].setMine(true);
            counter++;

        }

    }

    //Check for Neighbouring Mines and update count *****************
    public void countNeighbouringMines() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col].isMine()) {
                    for (int r = row - 1; r < row + 2; r++) {
                        for (int c = col - 1; c < col + 2; c++) {
                            try {
                                board[r][c].setCount();
                            } catch (Exception e) {/*Hello World*/}
                        }
                    }
                }
            }
        }
    }

    //////OPENING A NO MINE  ******
    public void openZeroMine(Tile t) {
        ArrayList<Tile> zeroToOpen = new ArrayList<>();
        zeroToOpen.add(t);
        while (zeroToOpen.size() > 0) {
            int row = zeroToOpen.get(0).getRow();
            int col = zeroToOpen.get(0).getCol();
            zeroToOpen.remove(0);
            for (int r = row - 1; r < row + 2; r++) {
                for (int c = col - 1; c < col + 2; c++) {
                    try {

                        if (!board[r][c].isOpen() && board[r][c].getCount() == 0) {
                            zeroToOpen.add(board[r][c]);
                            board[r][c].openTile();
                        }
                    } catch (Exception e) {/*Hello World*/}
                }
            }
        }
    }

    //Game Over ************************
    public void gameOver(boolean end) {
        if(end ) {
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[0].length; col++) {
                    board[row][col].openTile();
                }
            }
        }

    }
}

