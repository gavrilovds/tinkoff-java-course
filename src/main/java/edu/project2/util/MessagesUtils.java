package edu.project2.util;

public final class MessagesUtils {

    public static final String HELLO_MESSAGE =
        """
            Welcome to MazeApp!
            Here you can watch how app generates mazes and makes solutions for them.
            Well, let's get started!""";
    public static final String INPUT_MAZE_SIZE_MESSAGE =
        """
            \nTo begin, enter the height and width of the maze in one line.
            The length and width must be odd.
            Minimum value - 11, maximum value - 39.
            Example: 21 33""";
    public static final String CHOOSE_GENERATOR_MESSAGE =
        """
            Now you need to choose the generator that will be used to create the maze.
            Choose number of the generator you want:
            1 - DepthFirst maze generator
            2 - Prim maze generator
            """;
    public static final String CHOOSE_SOLVER_MESSAGE =
        """
            The last step is to choose an algorithm that will be used to search for a path from one point to another.
            Choose the algorithm that you like:
            1 - Breadth first search solver
            2 - Depth first search solver
            """;
    public static final String INPUT_COORDINATES_MESSAGE =
        """
            This is your maze!
            Enter the coordinates of the initial cell and the final one, between which you need to find a path.
            Example: 1 1 3 4
            (1, 1) - start coordinates, (3, 4) - end coordinates
            Input:
            """;
    public static final String END_MESSAGE = """
            This is solution for your maze!
            Thanks for using this application :)
        """;

    private MessagesUtils() {
    }
}
