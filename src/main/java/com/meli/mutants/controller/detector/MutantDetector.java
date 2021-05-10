package com.meli.mutants.controller.detector;


import com.meli.mutants.config.MutantProperties;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MutantDetector implements Detector {

    @Autowired
    @Getter
    private MutantProperties mutantsProperties;

    @Override
    public boolean isMutant(char[][] dnaMatrix) {
        int match = 0;
        int size = dnaMatrix.length ;


        boolean[][] horizontalVisited = new boolean[size][size] ;
        boolean[][] verticalVisited = new boolean[size][size];
        boolean[][] diagonalDownVisited = new boolean[size][size];
        boolean[][] diagonalUpVisited = new boolean[size][size];


        for (int row = 0 ; row <size ;row++){
            for(int column=0; column<size ;column++){

                if (isValidSearch(column ,size) )
                    match += checkHorizontal(dnaMatrix ,horizontalVisited ,row,column ) ? 1:0;

                if (isValidSearch(row ,size) )
                    match += checkVertical(dnaMatrix,verticalVisited ,row,column ) ? 1:0;

                if (isValidSearch(row ,size)  && isValidSearch(column ,size) )
                    match += checkDiagonalDown(dnaMatrix,diagonalDownVisited ,row,column ) ? 1:0;

                if (isValidSearchUp(row) && isValidSearch(column ,size) )
                    match += checkDiagonalUp(dnaMatrix,diagonalUpVisited ,row,column ) ? 1:0;

                if (match > getMutantsProperties().getMinSequence() ){
                    return true;
                }
            }
        }
        return false ;
    }


    private boolean checkDiagonalDown(char[][] dnaMatrix, boolean[][] diagonalDownVisited, int currentRow, int currentColumn){
        char currentChar = dnaMatrix[currentRow][currentColumn];

        if (diagonalDownVisited[currentRow][currentColumn] )
            return false;

        for (  int row = currentRow+1 , column = currentColumn+1 ; row < currentRow+mutantsProperties.getRequiredLenght() ; row++ , column++){
            if (dnaMatrix[row][column] != currentChar) {
                return false;
            }else{
                diagonalDownVisited[row][column] = true;
            }
        }
        System.out.println("diagonalDownVisited "+currentRow+"-"+currentColumn);
        return true;
    }

    private boolean checkDiagonalUp(char[][] dnaMatrix, boolean[][] diagonalUpVisited, int currentRow, int currentColumn){
        char currentChar = dnaMatrix[currentRow][currentColumn];

        if ( diagonalUpVisited[currentRow][currentColumn])
            return false;

        for (int row = currentRow-1 ,column = currentColumn+1 ; row > currentRow-mutantsProperties.getRequiredLenght() ; row-- , column++){
            if ( dnaMatrix[row][column] != currentChar) {
                return false;
            }else{
                diagonalUpVisited[row][column]=true;
            }
        }
        System.out.println("checkDiagonalUp "+currentRow+"-"+currentColumn);
        return true;
    }

    private boolean checkVertical(char[][] dnaMatrix, boolean[][] verticalVisited, int currentRow, int currentColumn){
        char currentChar = dnaMatrix[currentRow][currentColumn];

        if(verticalVisited[currentRow][currentColumn])
            return false;

        verticalVisited[currentRow][currentColumn]=true;

        for (int row = currentRow+1 ; row < currentRow+mutantsProperties.getRequiredLenght() ; row++){
            if (  dnaMatrix[row][currentColumn]!= currentChar){
                return false ;
            }else{
                verticalVisited[row][currentColumn] = true;
            }
        }
        System.out.println("checkVertical "+currentRow+"-"+currentColumn);

        return true;
    }



    private boolean checkHorizontal(char[][] dnaMatrix, boolean[][] horizontalVisited, int currentRow, int currentColumn){
        char currentChar = dnaMatrix[currentRow][currentColumn];

        if (horizontalVisited[currentRow][currentColumn])
            return false ;

        horizontalVisited[currentRow][currentColumn]=true;

        for (int column = currentColumn+1 ; column < currentColumn+mutantsProperties.getRequiredLenght() ; column++){
            if (dnaMatrix[currentRow][column]!= currentChar){
                return false ;
            }else{
                horizontalVisited[currentRow][column] = true;
            }
        }
        System.out.println("horizontal "+currentRow+"-"+currentColumn);

        return true;
    }


    private boolean isValidSearch(int value , int size){
       return value + mutantsProperties.getRequiredLenght() <= size ;
    }

    private boolean isValidSearchUp(int value ){
        return value+1 - mutantsProperties.getRequiredLenght() >= 0 ;
    }
}
