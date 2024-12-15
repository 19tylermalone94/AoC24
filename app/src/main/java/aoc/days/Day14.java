package aoc.days;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import aoc.util.Printers;
import aoc.util.Readers;

public class Day14 implements Day {

    public int M;
    int N;

    public Day14() {
        M = 103;
        N = 101;
    }

    public Day14(int M, int N) {
        this.M = M;
        this.N = N;
    }

    class Robot {
        int i, j;
        int di, dj;

        Robot(int i, int j, int di, int dj) {
            this.i = i;
            this.j = j;
            this.di = di;
            this.dj = dj;
        }

        void move() {
            i = (i + di + M) % M;
            j = (j + dj + N) % N;
        }

        @Override
        public String toString() {
            return i + " " + j;
        }
    }

    List<Robot> robots;

    @Override
    public void run(String fileName) throws Exception {
        String data = Readers.fileToString(fileName);
        System.out.println(part1(data));
        part2(data);
    }

    int part1(String data) throws Exception {
        initBots(data);
        for (int i = 0; i < 100; ++i) {
            robots.forEach(Robot::move);
        }
        Printers.printArr(makeGridFromBots());
        return calcSafetyScore();
    }

    int part2(String data) throws Exception {
        initBots(data);
        int imageWidth = 100 * N;
        int imageHeight = 100 * N;
        BufferedImage output = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = output.createGraphics();
        for (int i = 0; i < 100; ++i) {
            for (int j = 0; j < 100; ++j) {
                BufferedImage image = makeImage(makeGridFromBots());
                Graphics2D g2 = image.createGraphics();
                String s = i * 100 + j + "";
                FontMetrics fm = g2.getFontMetrics();
                int x = image.getWidth() - fm.stringWidth(s) - 5;
                int y = fm.getHeight();
                g2.drawString(s, x, y);
                g2.dispose();
                g.drawImage(image, N * j, M * i, N, M, null);
                robots.forEach(Robot::move);
            }
        }
        g.dispose();
        File outputFile = new File("image.png");
        ImageIO.write(output, "png", outputFile);
        return calcSafetyScore();
    }

    int calcSafetyScore() {
        int q1 = 0, q2 = 0, q3 = 0, q4 = 0;
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                int numBots = countBotsInCell(i, j);
                if (i < M / 2 && j < N / 2) {
                    q1 += numBots;
                } else if (i < M / 2 && j >= N / 2 + 1) {
                    q2 += numBots;
                } else if (i >= M / 2 + 1 && j < N / 2) {
                    q3 += numBots;
                } else if (i >= M / 2 + 1 && j >= N / 2 + 1) {
                    q4 += numBots;
                }
            }
        }
        return q1 * q2 * q3 * q4;
    }

    private int countBotsInCell(int i, int j) {
        return (int) robots.stream()
                           .filter(r -> r.i == i && r.j == j)
                           .count();
    }

    private void initBots(String data) {
        robots = Arrays.stream(data.trim().split("\n"))
                       .map(line -> {
                            int[] pos = Arrays.stream(line.substring(line.indexOf("=") + 1, line.indexOf(" ")).split(","))
                                            .mapToInt(Integer::parseInt)
                                            .toArray();

                            int[] vel = Arrays.stream(line.substring(line.lastIndexOf("=") + 1).split(","))
                                            .mapToInt(Integer::parseInt)
                                            .toArray();

                            return new Robot(pos[1], pos[0], vel[1], vel[0]);
                       })
                       .collect(Collectors.toList());
    }

    private char[][] makeGridFromBots() {
        char[][] grid = new char[M][N];
        Arrays.stream(grid).forEach(row -> Arrays.fill(row, '.'));
        robots.forEach(r -> grid[r.i][r.j] = '0');
        return grid;
    }

    BufferedImage makeImage(char[][] grid) {
        BufferedImage image = new BufferedImage(M, N, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                if (grid[i][j] == '0' && image.getRGB(i, j) != 65280) {
                    image.setRGB(i, j, 65280);
                }
            }
        }
        return image;
    }
    
}
