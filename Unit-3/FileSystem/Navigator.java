import java.util.Scanner;

/**
 * Handles interactive navigation of the file system This class reads commands from standard input,
 * interprets them, and invokes operations on the current directory node.
 */
public class Navigator {
    private final FileSystemTree fileSystem;
    private FolderNode currentDirectory;
    private boolean shouldExit;

    /**
     * Constructs a navigator for a given file system tree. The starting location is the root
     * directory.
     */
    public Navigator(FileSystemTree fst) {
        this.fileSystem = fst;
        this.currentDirectory = fst.getRoot();
    }

    /**
     * Starts a command loop that repeatedly reads a line of input, interprets it as a command with
     * arguments, and executes it until a request to terminate is received.
     */
    public void run() {
        shouldExit = false;
        Scanner kb = new Scanner(System.in);

        while (!shouldExit) {
            // Prompt can be customized to show current path if desired.
            System.out.print(currentDirectory + " ~ ");
            String line = kb.nextLine();
            processUserInputString(line);
        }

        kb.close();
    }

    /**
     * Changes the current directory based on a single path argument. Behavior should mirror typical
     * Unix shells: - "." refers to the current directory (no change). - ".." moves to the parent
     * directory (if one exists). - Paths starting with "/" are interpreted from the root directory.
     * - Other paths are interpreted relative to the current directory.
     */
    private void cd(String[] args) {
        if (args[0].equals(".")) {
            return;
        } else if (args[0].equals("..")) {
            if (currentDirectory.getParent() != null) {
                currentDirectory = currentDirectory.getParent();
                return;
            } else {
                System.out.println("Parent directory not found");
            }
        } else {
            if (args[0].charAt(0) == '/') {
                currentDirectory = fileSystem.getRoot();
            }
            String[] path = args[0].split("/");
            for (String directory : path) {
                if (directory == "") {
                    continue;
                }
                try {
                    FolderNode newDirectory =
                            (FolderNode) currentDirectory.getChildByName(directory);
                    if (newDirectory != null) {
                        currentDirectory = newDirectory;
                    } else {
                        System.out.println("Directory not found: " + directory);
                    }
                } catch (Exception e) {
                }

            }
        }
    }

    /**
     * Lists all items contained directly in the current directory. Output formatting can mirror
     * typical file system listings.
     */
    private void ls(String[] args) {
        if (args.length == 0) {
            for (FileSystemNode child : currentDirectory.getChildren()) {
                System.out.println(child.getName());
            }
        }
    }

    /**
     * Creates a new directory inside the current directory using the provided name.
     */
    private void mkdir(String[] args) {
        currentDirectory.addFolder(args[0]);
    }

    /**
     * Creates a new file inside the current directory with a given name and size.
     */
    private void touch(String[] args) {
        if (args.length < 2) {
            System.out.println("Size must be specified");
        }
        try {
            currentDirectory.addFile(args[0], Integer.parseInt(args[1]));
        } catch (Exception e) {
        }
    }

    /**
     * Searches the current directory and its descendants for nodes with a given name and prints
     * their paths.
     */
    private void find(String[] args) {
        if (!currentDirectory.containsNameRecursive(args[0])) {
            System.out.println("Directory not found");
        }
    }

    /**
     * Prints the absolute path of the current directory, from the root to this node.
     */
    private void pwd(String[] args) {
        System.out.println(currentDirectory.toString());
    }

    /**
     * Displays the contents of the current directory as a tree, optionally respecting flags or
     * depth limits if provided by the arguments.
     */
    private void tree(String[] args) {
        if (args.length == 0) {
            printChildrenRecursively(currentDirectory, -1);
        } else {
            try {
                Integer.parseInt(args[0]);
                System.out.println(currentDirectory.getName());
                printChildrenRecursively(currentDirectory, Integer.parseInt(args[0]));
            } catch (Exception e) {
                System.out.println("Size must be specified");
            }
        }
    }

    private void printChildrenRecursively(FileSystemNode node, int limit) {
        if (limit != -1 && node.getDepth() > limit) {
            return;
        }
        if (node.isFolder()) {
            FolderNode folder = (FolderNode) node;
            if (folder.getChildren().size() == 0) {
                for (int i = 1; i <= node.getDepth(); i++) {
                    System.out.print("-");
                }
                System.out.println(node.getName());
                return;
            }
            for (FileSystemNode child : folder.getChildren()) {
                printChildrenRecursively(child, limit);
            }
        } else {
            for (int i = 1; i <= node.getDepth(); i++) {
                System.out.print("-");
            }
            System.out.println(node.getName());
            return;
        }
    }

    /**
     * Prints how many nodes (files and folders) exist in the current directory and all of its
     * subdirectories.
     */
    private void count(String[] args) {
        System.out.println(currentDirectory.getTotalNodeCount());
    }

    /**
     * Prints the total size of all files reachable from the current directory.
     */
    private void size(String[] args) {
        System.out.println(currentDirectory.getSize());
    }

    /**
     * Prints the depth of the current directory, defined as the number of edges from the root
     * directory down to this directory.
     */
    private void depth(String[] args) {
        System.out.println(currentDirectory.getDepth());
    }

    /**
     * Prints the height of the current directory, defined as the longest downward distance from
     * this directory to any file or subdirectory beneath it. An empty directory has value 0.
     */
    private void height(String[] args) {
        System.out.println(currentDirectory.getHeight());
    }

    /**
     * Signals that the interactive loop should terminate after the current command.
     */
    private void quit(String[] args) {
        shouldExit = true;
    }

    /**
     * Interprets a line of user input by splitting it into a command and arguments, then forwarding
     * control to the appropriate helper method.
     *
     * Example inputs and how they are interpreted: "ls" -> command: "ls" args: []
     *
     * "mkdir docs" -> command: "mkdir" args: ["docs"]
     *
     * "touch notes.txt 100" -> command: "touch" args: ["notes.txt", "100"]
     *
     * "cd .." -> command: "cd" args: [".."]
     */
    public void processUserInputString(String line) {
        if (line == null || line.trim().isEmpty()) {
            return;
        }

        String[] parts = line.trim().split("\\s+");
        String command = parts[0];
        String[] args = new String[parts.length - 1];
        System.arraycopy(parts, 1, args, 0, args.length);

        switch (command) {
            case "cd":
                cd(args);
                break;
            case "ls":
                ls(args);
                break;
            case "mkdir":
                mkdir(args);
                break;
            case "touch":
                touch(args);
                break;
            case "find":
                find(args);
                break;
            case "pwd":
                pwd(args);
                break;
            case "tree":
                tree(args);
                break;
            case "count":
                count(args);
                break;
            case "size":
                size(args);
                break;
            case "depth":
                depth(args);
                break;
            case "height":
                height(args);
                break;
            case "quit":
                quit(args);
                break;
            default:
                // Unknown commands can be reported back to the user.
                System.out.println("Unrecognized command: " + command);
        }
    }
}
