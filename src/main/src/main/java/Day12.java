
import cave.Path;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.*;

public class Day12 {


    public static void main(String[] args) {
        String data = """
start-qs
qs-jz
start-lm
qb-QV
QV-dr
QV-end
ni-qb
VH-jz
qs-lm
qb-end
dr-fu
jz-lm
start-VH
QV-jz
VH-qs
lm-dr
dr-ni
ni-jz
lm-QV
jz-dr
ni-end
VH-dr
VH-ni
qb-HE
                """;
        String test = """
fs-end
he-DX
fs-he
start-DX
pj-DX
end-zg
zg-sl
zg-pj
pj-he
RW-he
fs-DX
pj-RW
zg-RW
start-pj
he-WI
zg-he
pj-fs
start-RW  
                """;

        try {

            HashMap<String, Set<String>> caveTransistion = new HashMap<>();

            for (String line : data.split("\n")) {
                String[] lineArr = line.split("-");
                if (!caveTransistion.containsKey(lineArr[0])) {
                    caveTransistion.put(lineArr[0], new HashSet<>());
                }
                if (!caveTransistion.containsKey(lineArr[1])) {
                    caveTransistion.put(lineArr[1], new HashSet<>());
                }
                caveTransistion.get(lineArr[0]).add(lineArr[1]);
                caveTransistion.get(lineArr[1]).add(lineArr[0]);
            }

            List<Path> allPaths = new ArrayList<>();

            allPaths.add(new Path(new HashSet<>(), "start"));

            boolean notAllAtEnd = true;
            while(notAllAtEnd) {
                notAllAtEnd = false;

                List<Path> newPaths = new ArrayList<>();

                for (Path p : allPaths) {
                    if (p.isAtTheEnd()) {
                        newPaths.add(p);
                        continue;
                    }
                    notAllAtEnd = true;

                    for (String c : caveTransistion.get(p.getLastVisited())) {
                        if (!p.haveBeenAt(c)) {
                            if(p.isMultiCave(c)) {
                                newPaths.add(new Path(p, c, c));
                            } else {
                                newPaths.add(new Path(p, c));
                            }
                        }
                    }
                }

                allPaths = newPaths;
            }

            for (Path p : allPaths) {
                System.out.println(p.getPathStr());
            }

            System.out.println(allPaths.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/*
start,A,b,A,c,A,b,A,end
start,A,b,A,c,A,b,end
start,A,b,A,c,A,c,A,end
start,A,c,A,b,A,c,A,end
start,b,A,c,A,b,A,end
start,b,A,c,A,b,end
start,b,A,c,A,c,A,end
*/



