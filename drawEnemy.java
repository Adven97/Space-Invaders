package sample;

public class drawEnemy {
    int map[][];
    int ypos;
    int yVel;

    public drawEnemy(int row, int col) {
        map = new int[row][col];

        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[0].length; j++)
                map[i][j] = 1;
        yVel=2;
    }

    public  void setBrickValue(int value, int rows, int cols){

        map[rows][cols]=value;
    }
    public void movee(){
            ypos += yVel;
        }
}
