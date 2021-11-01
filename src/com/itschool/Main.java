package com.itschool;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      final int N = 3;
      boolean isFirstPlayerTurn = false, hasVictory = false;
      int currentTurn = 1;
      char[][] field = new char[N][N];
      Random random = new Random();

      for (int i = 0; i < N; i++) {
         Arrays.fill(field[i], ' ');
      }

      drawField(N, field);

      int row, col;
      do {
         if (!isFirstPlayerTurn) {
            System.out.println("\nComputer's turn: ");
            do {
               row = random.nextInt(N);
               col = random.nextInt(N);
            } while (field[row][col] != ' ');
         } else {
            do {
               System.out.println("\nHuman's turn: ");
               row = scanner.nextInt();
               col = scanner.nextInt();
            } while (row < 0 || row >= N || col < 0 || col >= N || field[row][col] != ' ');
         }
         field[row][col] = (isFirstPlayerTurn ? '✗' : '○');
         drawField(N, field);
         hasVictory = checkVictory(N, field);
         if (hasVictory) {
            break;
         }
         isFirstPlayerTurn = !isFirstPlayerTurn;
         currentTurn++;
      } while (currentTurn < 10);

/*
      if (hasVictory == false)
         System.out.println("Draw!");
      else
         System.out.println("Winner is: " + (isFirstPlayerTurn ? 'X' : '○'));
*/
      System.out.println((hasVictory == false ? "Draw!" : "Winner is: " + (isFirstPlayerTurn ? '✗' : '○')));
   }

   private static boolean checkVictory(int N, char[][] field) {
      boolean hasWinner = false;
      // check win by rows
      for (int i = 0; i < N; i++) {
         if (field[i][0] != ' ' && field[i][0] == field[i][1] && field[i][0] == field[i][2]) {
            hasWinner = true;
            break;
         }
      }
      // check win by columns
      if (!hasWinner) {
         for (int i = 0; i < N; i++) {
            if (field[0][i] != ' ' && field[0][i] == field[1][i] && field[0][i] == field[2][i]) {
               hasWinner = true;
               break;
            }
         }
      }
      // check win by main diagonal
      if (!hasWinner) {
         if (field[0][0] != ' ' && field[0][0] == field[1][1] && field[0][0] == field[2][2]) {
            hasWinner = true;
         }
      }
      // check win by back diagonal
      if (!hasWinner) {
         if (field[0][2] != ' ' && field[0][2] == field[1][1] && field[0][2] == field[2][0]) {
            hasWinner = true;
         }
      }

      return hasWinner;
   }

   private static void drawField(int N, char[][] field) {
      for (int row = 0; row < N; row++) {
         for (int col = 0; col < N; col++) {
            System.out.print(field[row][col] + (col < 2 ? " | " : ""));
         }
         if (row < N - 1)
            System.out.println(System.lineSeparator() + "----------");
      }
      System.out.println();
   }
}
