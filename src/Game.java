public class Game {
    private int[][] matrix = new int[3][3];

    public Game() {
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                matrix[i][j]=0;
            }
        }
    }

    public void setByIndexes(int i, int j, int val){
        matrix[i][j] = val;
    }

    public boolean isEmpty(int i, int j){
        return matrix[i][j] == 0;
    }

    public int[] isDone(){
        for(int i=0; i<3; i++){
            if(matrix[i][0] == 1 && matrix[i][1] == 1 && matrix[i][2] == 1){
                return new int[]{i, 1, 1};
            }
            if(matrix[i][0] == 2 && matrix[i][1] == 2 && matrix[i][2] == 2){
                return new int[]{i, 1, 2};
            }
            if(matrix[0][i] == 1 && matrix[1][i] == 1 && matrix[2][i] == 1){
                return new int[]{i, 2, 1};
            }
            if(matrix[0][i] == 2 && matrix[1][i] == 2 && matrix[2][i] == 2){
                return new int[]{i, 2, 2};
            }
        }
        if(matrix[0][0] == 1 && matrix[1][1] == 1 && matrix[2][2] == 1){
            return new int[]{3, 1, 1};
        }
        if(matrix[0][0] == 2 && matrix[1][1] == 2 && matrix[2][2] == 2){
            return new int[]{3, 1, 2};
        }
        if(matrix[0][2] == 1 && matrix[1][1] == 1 && matrix[2][0] == 1){
            return new int[]{3, 2, 1};
        }
        if(matrix[0][2] == 2 && matrix[1][1] == 2 && matrix[2][0] == 2){
            return new int[]{3, 2, 2};
        }
        return new int[]{0, 0};
    }
}
