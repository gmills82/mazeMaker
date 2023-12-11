package info.millscraft.maze;

import millscraft.mazeGenerator.Grid;
import millscraft.mazeGenerator.generator.*;
import millscraft.mazeGenerator.render.ImageRendererImpl;

import java.io.File;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Scanner scanner = new Scanner(System.in);
        System.out.println("What width maze would you like? (maze rows) ");
        //To fit on one page don't exceed 63
        int width = scanner.nextInt();

        System.out.println("What height maze would you like? (maze columns) ");
        //To fit on one page don't exceed 82
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
        System.out.println("Generating maze now...");
        Grid mazeGrid = new Grid(height, width);
        Grid preparedMaze = algorithm.prepareMaze(mazeGrid);

        ImageRendererImpl imageRenderer = new ImageRendererImpl();

        File mazeJpeg = imageRenderer.render(preparedMaze);
        System.out.println("Your maze is generated and located at: " + mazeJpeg.getAbsolutePath());

    }
}
