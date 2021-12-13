package cave;

import java.util.HashSet;
import java.util.Set;

public class Path {
    private final Set<String> visitedSmallCaves;
    private final String lastVisited;
    private final boolean atTheEnd;
    private String multiCave = null;
    private String pathStr = "";

    public Path(Path p, String lastVisited, String foundMultiCave) {
        this(p, lastVisited);
        multiCave = foundMultiCave;
    }


    public Path(Path p, String lastVisited) {
        this.visitedSmallCaves = new HashSet<>();
        this.visitedSmallCaves.addAll(p.visitedSmallCaves);
        if(lastVisited.toLowerCase().equals(lastVisited)) {
            this.visitedSmallCaves.add(lastVisited);
        }
        this.lastVisited = lastVisited;
        atTheEnd = "end".equals(lastVisited);
        this.multiCave = p.multiCave;
        this.pathStr = p.pathStr + lastVisited + ",";
    }

    public Path(Set<String> visitedSmallCaves, String lastVisited) {
        this.visitedSmallCaves = new HashSet<>();
        this.visitedSmallCaves.addAll(visitedSmallCaves);
        if(lastVisited.toLowerCase().equals(lastVisited)) {
            this.visitedSmallCaves.add(lastVisited);
        }
        this.lastVisited = lastVisited;
        atTheEnd = "end".equals(lastVisited);
        this.pathStr = lastVisited + ",";
    }

    public boolean haveBeenAt(String cave) {
        if(cave.toLowerCase().equals(cave)) {
            if (
                multiCave == null &&
                !"start".equals(cave) &&
                !"end".equals(cave) &&
                visitedSmallCaves.contains(cave)
            ) {
                return false;
            }
            return visitedSmallCaves.contains(cave);
        }
        return false;
    }

    public boolean isMultiCave(String cave) {
        if (
            multiCave == null &&
            !"start".equals(cave) &&
            !"end".equals(cave) &&
            visitedSmallCaves.contains(cave)
        ) {
            return true;
        }
        return false;
    }

    public String getPathStr() {
        return pathStr;
    }

    public String getLastVisited() {
        return lastVisited;
    }

    public boolean isAtTheEnd() {
        return atTheEnd;
    }

    @Override
    public String toString() {
        return "Path{" +
                "pathStr='" + pathStr + '\'' +
                '}';
    }
}
