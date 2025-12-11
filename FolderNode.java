import java.util.ArrayList;
import java.util.List;

/**
 * Represents a directory in the file system tree.
 * A directory can contain other directories and files as children.
 */
public class FolderNode extends FileSystemNode {

    private List<FileSystemNode> children;

    public FolderNode(FolderNode parent, String name) {
        super(parent, name);
        this.children = new ArrayList<>();
    }

    @Override
    public boolean isFolder() {
        return true;
    }

    /**
     * Returns a list view of the children contained directly inside this directory.
     * Modifying the returned list is not required to be supported.
     */
    public List<FileSystemNode> getChildren() {
        return children;
    }

    /**
     * Searches the children of this directory for a node whose name matches the
     * input.
     * Only direct children are considered, not deeper descendants.
     */
    public FileSystemNode getChildByName(String childName) {
        for (FileSystemNode fileSystemNode : children) {
            if (fileSystemNode.getName().equals(childName)) {
                return fileSystemNode;
            }
        }
        return null;
    }

    /**
     * Creates a new file directly inside this directory with the given name and
     * size.
     * If a child with the same name already exists, no file is created and false is
     * returned.
     * Otherwise the new file is added and true is returned.
     */
    public boolean addFile(String fileName, int size) {
        if (getChildByName(fileName) == null) {
            children.add(new FileNode(fileName, this, size));
            return true;
        }
        return false;
    }

    /**
     * Creates a new subdirectory directly inside this directory with the given
     * name.
     * If a child with the same name already exists, no folder is created and false
     * is returned.
     * Otherwise the new folder is added and true is returned.
     */
    public boolean addFolder(String folderName) {
        if (getChildByName(folderName) == null) {
            children.add(new FolderNode(this, folderName));
            return true;
        }
        return false;
    }

    /**
     * Searches this directory and all of its descendants for nodes whose name
     * matches the input.
     * When a match is found, its full path can be printed by the caller using
     * toString().
     */
    public boolean containsNameRecursive(String searchName) {
        FileSystemNode foundNode = getChildByName(searchName);
        if (foundNode == null) {
            for (FileSystemNode child : children) {
                if (child.isFolder()) {
                    FolderNode folderChild = (FolderNode) child;
                    boolean found = folderChild.containsNameRecursive(searchName);
                    if (found) {
                        return true;
                    }
                }
            }
        } else {
            System.out.println(foundNode);
            return true;
        }
        return false;
    }

    @Override
    public int getHeight() {
        if (children.size() == 0) {
            return 0;
        }
        ArrayList<Integer> heights = new ArrayList<Integer>();
        for (FileSystemNode child : children) {
            heights.add(1 + child.getHeight());
        }
        int max = 0;
        for (Integer height : heights) {
            if (height > max) {
                max = height;
            }
        }
        return max;
    }

    @Override
    public int getSize() {
        if (children.size() == 0) {
            return isFolder() ? 0 : getSize();
        }
        ArrayList<Integer> sizes = new ArrayList<Integer>();
        for (FileSystemNode child : children) {
            sizes.add(child.getSize());
        }
        int sum = 0;
        for (Integer size : sizes) {
            sum += size;
        }
        return sum;
    }

    @Override
    public int getTotalNodeCount() {
        int sum = 0;
        for (FileSystemNode child : children) {
            sum += child.getTotalNodeCount();
        }
        return sum + 1;
    }
}
