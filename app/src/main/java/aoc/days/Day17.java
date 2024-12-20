package aoc.days;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import aoc.util.Readers;

public class Day17 implements Day {

    @Override
    public void run(String fileName) throws Exception {
        int a = 2;
        String data = Readers.fileToString(fileName);
        System.out.println(part1(data));
        System.out.println(part2(data));
    }

    String part1(String data) {
        Config conf = new Config(data);
        long A = conf.A;
        long B = conf.B;
        long C = conf.C;
        long[] instructions = conf.instructions;
        Program program = new Program(A, B, C, instructions);
        return program.run().toString();
    }

    long part2(String data) {
        Config conf = new Config(data);
        long A = 0L;
        long B = conf.B;
        long C = conf.C;
        long[] instructions = conf.instructions;
        List<Long> instructionList = Arrays.stream(instructions).boxed().collect(Collectors.toList());

        for (int i = instructions.length - 1; i >= 0; --i) {
            A <<= 3;
            while (!new Program(A, B, C, instructions).run().equals(instructionList.subList(i, instructionList.size()))) {
                ++A;
            }
        }
        return A;
    }

    class Config {
        long A, B, C;
        long[] instructions;

        Config(String data) {
            String[] parts = data.trim().split("\n");
            A = Long.parseLong(parts[0].substring(parts[0].lastIndexOf(" ") + 1));
            B = Long.parseLong(parts[1].substring(parts[1].lastIndexOf(" ") + 1));
            C = Long.parseLong(parts[2].substring(parts[2].lastIndexOf(" ") + 1));
            instructions = Arrays.stream(parts[4].substring(parts[4].lastIndexOf(" ") + 1).split(","))
                                 .mapToLong(Long::parseLong)
                                 .toArray();
        }
    }

    class Program {
        long A, B, C;
        long ptr;
        long[] instructions;
        List<Long> out;
        int check;

        Program(long A, long B, long C, long[] instructions) {
            this.A = A;
            this.B = B;
            this.C = C;
            ptr = 0;
            this.instructions = instructions;
            out = new LinkedList<>();
        }

        private List<Long> run() {
            while (ptr < instructions.length) {
                executeInstruction(instructions[(int)ptr], instructions[(int)ptr + 1]);
            }
            return out;
        }
    
        private void executeInstruction(long opcode, long operand) {
            if (opcode == 0) {
                adv(opcode, operand);
            } else if (opcode == 1) {
                bxl(opcode, operand);
            } else if (opcode == 2) {
                bst(opcode, operand);
            } else if (opcode == 3) {
                jnz(opcode, operand);
            } else if (opcode == 4) {
                bxc(opcode, operand);
            } else if (opcode == 5) {
                out(opcode, operand);
            } else if (opcode == 6) {
                bdv(opcode, operand);
            } else if (opcode == 7) {
                cdv(opcode, operand);
            }
        }
    
        private void adv(long opcode, long operand) {
            operand = getComboOperand(operand);
            A = (long)(A / Math.pow(2, operand));
            ptr += 2;
        }
    
        private void bxl(long opcode, long operand) {
            B = B ^ operand;
            ptr += 2;
        }
    
        private void bst(long opcode, long operand) {
            operand = getComboOperand(operand);
            B = operand % 8;
            ptr += 2;
        }
    
        private void jnz(long opcode, long operand) {
            if (A != 0) {
                ptr = operand;
            } else {
                ptr += 2;
            }
        }
    
        private void bxc(long opcode, long operand) {
            B = B ^ C;
            ptr += 2;
        }
    
        private void out(long opcode, long operand) {
            operand = getComboOperand(operand);
            out.add(operand % 8);
            ptr += 2;
        }
    
        private void bdv(long opcode, long operand) {
            operand = getComboOperand(operand);
            B = (long)(A / Math.pow(2, operand));
            ptr += 2;
        }
    
        private void cdv(long opcode, long operand) {
            operand = getComboOperand(operand);
            C = (long)(A / Math.pow(2, operand));
            ptr += 2;
        }
    
        private long getComboOperand(long operand) {
            if (operand < 4) {
                return operand;
            } else if (operand == 4) {
                return A;
            } else if (operand == 5) {
                return B;
            } else if (operand == 6) {
                return C;
            }
    
            return -1;
        }
    }
    
}
