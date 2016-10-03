/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conways_Game_Of_Life;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Krunal
 */
public class Conways_Game_Of_Life_Random_Grid {

    /**
     * @param args the command line arguments
     */
    int initial_state[][];
    int updated_state[][];
    int row;
    int col;
    
    public void init(int initial_state[][], int row, int col)
    {
        this.initial_state = null;
        this.initial_state = new int[row][col];
        this.initial_state = initial_state;
        this.updated_state = null;
        this.updated_state = new int[row][col];
        
        this.row = row;
        this.col = col;
        //initializing new memory everytime otherwise due to memory allocations - it is messing up the output
        //Comment out bottom 2 lines and view change in output
              
      
    }
    
    
    private void NextState() {
        
        int count;
        int temp;
        
        for(int i=0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                count = 0;
                temp = initial_state[i][j];
                
                
                /* Could have done below portion in better way - can just do count++ by checking upper if > 0 and lower < array length - 1 - same for left and right */

                
                if(i > 0 && j > 0 && i < initial_state.length-1 && j < initial_state[0].length-1)   //mid elements
                    count =  initial_state[i-1][j-1] + initial_state[i-1][j] + initial_state[i-1][j+1] + initial_state[i][j-1] + initial_state[i][j+1] + initial_state[i+1][j-1] + initial_state[i+1][j] + initial_state[i+1][j+1];
                else if(i==0 && j == 0) //first element first row
                    count =  initial_state[i][j+1] + initial_state[i+1][j] + initial_state[i+1][j+1]; 
                else if(i==0 && j > 0 && j < initial_state[0].length-1) //first row elements
                    count = initial_state[i][j-1] + initial_state[i][j+1] + initial_state[i+1][j-1] +initial_state[i+1][j] + initial_state[i+1][j+1];
                else if(j==0 && i > 0 && i < initial_state.length-1)    //first column elements
                    count = initial_state[i-1][j] + initial_state[i-1][j+1] + initial_state[i][j+1] + initial_state[i+1][j] + initial_state[i+1][j+1];
                else if(i == initial_state.length-1 && j == 0)  //last row first element
                    count =  initial_state[i][j+1] + initial_state[i-1][j] + initial_state[i-1][j+1];
                else if(i == initial_state.length-1 && j > 0 && j < initial_state[0].length-1) //last row elements
                    count = initial_state[i-1][j-1] +initial_state[i-1][j] + initial_state[i-1][j+1] + initial_state[i][j-1] + initial_state[i][j+1];
                else if(i == initial_state.length-1 && j == initial_state.length-1) //last row last element
                    count =  initial_state[i-1][j-1] + initial_state[i-1][j] + initial_state[i][j-1]; 
                else if(i==0 && j == initial_state.length-1)    //first row last element
                    count = initial_state[i-1][j-1] +initial_state[i-1][j] + initial_state[i-1][j+1] + initial_state[i][j-1] + initial_state[i][j+1];
                else if(i > 0 && i < initial_state.length-1 && j == initial_state[0].length-1) //last col               
                    count = initial_state[i-1][j-1] + initial_state[i-1][j] + initial_state[i][j-1] + initial_state[i+1][j-1] + initial_state[i+1][j];
               
                switch(temp)    //Cases for live and dead cell
                {
                    
                    case 0:
                        
                        if(count == 3)  //Condition 4 - Any dead cell with exactly three live neighbors becomes a live cell.
                            updated_state[i][j] = 1;
                        else
                            updated_state[i][j] = 0;
                        break;
                        
                    case 1:
                        if(count < 2 || count > 3)      //Condition 1 and 2
                                updated_state[i][j] = 0;   
                        else                            //Condition 3
                            updated_state[i][j] = 1; 
                            
                        
                        break;
                    
                }
                
            }
        }
        
        
        System.out.println("-------------------- Updated State ----------------------------");
        for(int l=0; l < row; l++)
        {
            for(int m = 0; m < col; m++)
            {
                System.out.print(updated_state[l][m]+" ");
            }
            System.out.println();
        }
        
        init(updated_state, row, col);
        
        
        
    }
    
    
    
    
    
    
    public static void main(String[] args) {
        
        Scanner s = new Scanner(System.in);
        
        
        
        /* Way 1:- Below is the user input in 1's and 0's with spaces as we are allowed to select any data model
            0 0 0 0 0 0 1 0
            1 1 1 0 0 0 1 0
            0 0 0 0 0 0 1 0
            0 0 0 0 0 0 0 0
            0 0 0 1 1 0 0 0
            0 0 0 1 1 0 0 0
        */
        
        
        
        
        
                            
        int check;
        done:try
        {
            System.out.println("Enter grid dimensions row n column:-");

            int row = s.nextInt();
            int col = s.nextInt();
        
            int [][]initial_state = new int[row][col];  
        
            System.out.println("Enter initial state of "+row+"*"+col+" grid of cells [\"1\" -> Live Cells and \"0\" -> Dead Cells] with spaces in between");
            for(int i=0; i < row; i++)
                for(int j = 0; j < col; j++)
                {
                    check = s.nextInt();
                    if(check == 0 || check == 1) 
                        initial_state[i][j] = check;  //Storing in an array
                    else                    
                        throw new InputMismatchException();
                    
                }
                    
            
            Conways_Game_Of_Life_Random_Grid cgof = new Conways_Game_Of_Life_Random_Grid();
            cgof.init(initial_state, row, col);   
            
            int input;
            do  //Alternate to countinue running is implementing thread rather then waiting for user input.
            {
                cgof.NextState();
                System.out.println("Hit among the below options:- \n1. To view updated state \n2. To exit");
                input = s.nextInt();
                
                if(input != 1 && input != 2) 
                    throw new InputMismatchException();
                                        
                        
               
                
                

            }while(input == 1);

            System.out.println("Thanks for playing");
        }
        catch(InputMismatchException e)
        {
            System.out.println("Invalid Input "+e);
            
        }
        catch(ArrayIndexOutOfBoundsException a)
        {
            System.out.println(a);
        } 
        finally
        {
            
        }
        
       
        // TODO code application logic here
    }
  
}
