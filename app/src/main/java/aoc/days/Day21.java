// package aoc.days;

// import java.util.Arrays;
// import java.util.Collections;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.PriorityQueue;
// import java.util.Queue;
// import java.util.Set;
// import java.util.stream.Collectors;

// import aoc.util.Node2;
// import aoc.util.Pair;
// import aoc.util.Readers;

// public class Day21 implements Day {

//     Map<Character, Node2> numMap = new HashMap<>() {{
//         put('7', new Node2(0, 0));
//         put('8', new Node2(0, 1));
//         put('9', new Node2(0, 2));
//         put('4', new Node2(1, 0));
//         put('5', new Node2(1, 1));
//         put('6', new Node2(1, 2));
//         put('1', new Node2(2, 0));
//         put('2', new Node2(2, 1));
//         put('3', new Node2(2, 2));
//         put(' ', new Node2(3, 0));
//         put('0', new Node2(3, 1));
//         put('A', new Node2(3, 2));
//     }};

//     Map<Character, Node2> dirMap = new HashMap<>() {{
//         put(' ', new Node2(0, 0));
//         put('^', new Node2(0, 1));
//         put('A', new Node2(0, 2));
//         put('<', new Node2(1, 0));
//         put('V', new Node2(1, 1));
//         put('>', new Node2(1, 2));
//     }};

//     char[][] numPad = {
//         {'7', '8', '9'},
//         {'4', '5', '6'},
//         {'1', '2', '3'},
//         {' ', '0', 'A'}
//     };

//     char[][] dirPad = {
//         {' ', '^', 'A'},
//         {'<', 'V', '>'}
//     };

//     int[] di = {-1, 1, 0, 0};
//     int[] dj = {0, 0, -1, 1};

//     @Override
//     public void run(String fileName) throws Exception {
//         String data = Readers.fileToString(fileName);
//         System.out.println(part1(data));
//     }

//     long part1(String data) {
//         List<String> codes = Arrays.stream(data.trim().split("\n"))
//                                    .collect(Collectors.toList());
//         long totalComplexity = 0L;
//         // for (String code : codes) {
//         for (String numberPadSeq : new String[] {"<A^A>^^AVVVA", "<A^A^>^AVVVA", "<A^A^^>AVVVA"}) {
//             // String numberPadSeq = getSequence(code, numMap, numPad);
//             // System.out.println(numberPadSeq);
//             String dirPadSeq1 = getSequence(numberPadSeq, dirMap, dirPad);
//             // System.out.println(dirPadSeq1);
//             String dirPadSeq2 = getSequence(dirPadSeq1, dirMap, dirPad);
//             System.out.println(dirPadSeq2.length());
//             // totalComplexity += dirPadSeq2.length() * codeValue(code);
//             // break;
//         }
//         return totalComplexity;    
//     }

//     String getSequence(String code, Map<Character, Node2> map, char[][] pad) {
//         StringBuilder seq = new StringBuilder();
//         Node2 start = map.get('A');
//         for (int i = 0; i < code.length(); ++i) {
//             Node2 target = map.get(code.charAt(i));
//             int dx = start.x - target.x;
//             int dy = start.y - target.y;
//             int adx = Math.abs(dx);
//             int ady = Math.abs(dy);
//             int currx = start.x;
//             int curry = start.y;
//             while (adx > 0 || ady > 0) {
//                 if (adx > 0 && dx > 0 && pad[currx - 1][curry] != ' ') {
//                     seq.append('^');
//                     currx--;
//                     adx--;
//                 } else if (adx > 0 && dx < 0 && pad[currx + 1][curry] != ' ') {
//                     seq.append('V');
//                     currx++;
//                     adx--;
//                 }
    
//                 if (ady > 0 && dy > 0 && pad[currx][curry - 1] != ' ') {
//                     seq.append('<');
//                     curry--;
//                     ady--;
//                 } else if (ady > 0 && dy < 0 && pad[currx][curry + 1] != ' ') {
//                     seq.append('>');
//                     curry++;
//                     ady--;
//                 }
//             }
//             seq.append("A");
//             start = target;
//         }
//         return seq.toString();
//     }

//     int codeValue(String code) {
//         return Integer.parseInt(code.substring(0, code.length() - 1));
//     }

//     int dfs(int i, int j, char target, char[][] grid, int count, Set<Node2> seen) {
//         if (grid[i][j] == target) {
//             return count;
//         }
//         seen.add(new Node2(i, j));
//         int dist = Integer.MAX_VALUE;
//         for (int d = 0; d < 4; ++d) {
//             int ni = i + di[d];
//             int nj = j + dj[d];
//             if (!offFrame(ni, nj, grid) && grid[ni][nj] != ' ') {
//                 dist = Math.min(dist, dfs(ni, nj, target, grid, count + 1, seen));
//             }
//         }
//         return dist;
//     }

//     List<String> allShortestPaths(int i, int j, char target, char[][] grid, Set<Node2> seen, int targetaDist, List<String> paths, String path) {
//         path += grid[i][j] + "";
//         if (grid[i][j] == target && path.length() == targetaDist) {
//             paths.add(path);
//         }
//         seen.add(new Node2(i, j));
//         for (int d = 0; d < 4; ++d) {
//             int ni = i + di[d];
//             int nj = j + dj[d];
//             if (!offFrame(ni, nj, grid) && grid[ni][nj] != ' ') {
//                 dist = Math.min(dist, dfs(ni, nj, target, grid, count + 1, seen));
//             }
//         }
//         return dist;
//     }

//     boolean offFrame(int i, int j, char[][] grid) {
//         return i < 0 || i >= grid.length || j < 0 || j >= grid[0].length;
//     }
  
// }
