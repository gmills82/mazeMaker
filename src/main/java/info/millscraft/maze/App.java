package info.millscraft.maze;

import millscraft.mazeGenerator.Direction;
import millscraft.mazeGenerator.Grid;
import millscraft.mazeGenerator.generator.*;
import millscraft.mazeGenerator.render.ImageRendererImpl;

import java.awt.*;
import java.io.File;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many columns should your maze have? ");
        int width = scanner.nextInt();

        System.out.println("How many rows should your maze have? ");
        int height = scanner.nextInt();

        System.out.println("Your maze will be " + width + " by " + height + ".");
        System.out.println("Which algorithm would you like to use to generate the maze?");
        System.out.println("\t 1 - AldousBroder");
        System.out.println("\t 2 - BinaryTree");
        System.out.println("\t 3 - HuntAndKill (default)");
        System.out.println("\t 4 - Sidewinder");
        System.out.println("\t 5 - Wilson");

        int algorithmNumber = scanner.nextInt();
        GeneratorAlgorithm algorithm;
        switch (algorithmNumber) {
            case 1:
                algorithm = new AldousBroder();
                break;
            case 2:
                algorithm = new BinaryTree();
                break;
            case 4:
                algorithm = new Sidewinder();
                break;
            case 5:
                algorithm = new Wilson();
                break;
            default:
                algorithm = new HuntAndKill();
                break;
        }

        System.out.println("You have selected the algorithm " + algorithm.getClass().getSimpleName() + ".");

        System.out.println("Which side would you like the start of the maze on?");
        System.out.println("\t 1 - North (default)");
        System.out.println("\t 2 - East");
        System.out.println("\t 3 - South");
        System.out.println("\t 4 - West");
        int startSide = scanner.nextInt();
        Direction startDirection = Direction.NORTH;
        for (Direction direction : Direction.values()) {
            if (direction.getDirectionNumber() == startSide) {
                startDirection = direction;
            }
        }

        System.out.println("Which side would you like the end of the maze on?");
        System.out.println("\t 1 - North");
        System.out.println("\t 2 - East");
        System.out.println("\t 3 - South (default)");
        System.out.println("\t 4 - West");
        int endSide = scanner.nextInt();
        Direction endDirection = Direction.SOUTH;
        for (Direction direction : Direction.values()) {
            if (direction.getDirectionNumber() == endSide) {
                endDirection = direction;
            }
        }

        System.out.println("Generating maze now...");
        Grid mazeGrid = new Grid(height, width);
        Grid preparedMaze = algorithm.prepareMaze(mazeGrid);

        System.out.println("Designing maze... ");
        System.out.println("How thick would you like your maze cells to be? ");
        int cellSize = scanner.nextInt();
        System.out.println("How thick would you like each wall?");
        int wallThickness = scanner.nextInt();

        ImageRendererImpl imageRenderer = new ImageRendererImpl(cellSize, cellSize * 2, wallThickness, Color.DARK_GRAY, new Color(17, 242, 152));

        File mazeJpeg = imageRenderer.render(preparedMaze, startDirection, endDirection);
        System.out.println("Your maze is generated and located at: " + mazeJpeg.getAbsolutePath());

    }
}
