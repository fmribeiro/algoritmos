import java.util.*;

public class Rearrange {

    static int multiply(int current, int next) {
        return current * next;
    }

    static int permutation(int qttyElements) {
        int totalDigits = qttyElements;
        int permutation = 1;
        int next = totalDigits - 1;
        while (totalDigits > 1) {
            permutation = permutation * multiply(totalDigits, next);
            totalDigits = next - 1;
            next = totalDigits - 1;
        }
        return permutation;
    }

    static public int rearrangeCounter(int n) {
        System.out.println("current number: " + n);

        String numberStr = String.valueOf(n);
        int digits[] = new int[numberStr.length()];
        int totalDigits = numberStr.length();
        int zerosFounded = 0;
        int repetitions = 0;
        Map<Character, Integer> repetitionsMap = new HashMap<>();

        //quantidade de permutacoes com um determinada qtde de nros
        int permutation = permutation(totalDigits);

        //encontra zero e nros repetidos
        List<Integer> currentList = new ArrayList<>();
        for (int i = 0; i < numberStr.length(); i++) {
            char zero = '0';
            char c = numberStr.charAt(i);
            Integer number = Integer.valueOf(String.valueOf(c));

            //encontrar zero na sequencia
            if (c == zero) {
                zerosFounded++;
            }

            //encontrar nros repetidos
            if (c != zero && currentList.contains(number)) {
//            if (currentList.contains(number)) {
                repetitions++;

                if (repetitionsMap.containsKey(c)) {
                    int qttyRepetitions = repetitionsMap.get(c) + 1;
                    repetitionsMap.put(c, qttyRepetitions);
                } else {
                    repetitionsMap.put(c, 2);
                }
            }
            currentList.add(number);
            digits[i] = number;
        }

        System.out.println("repetitions: " + repetitions);
        System.out.println("zeros founded: " + zerosFounded);

        if (zerosFounded >= 1 && repetitions >= 1) {
            int total = permutation(totalDigits) - (permutation(totalDigits - 1) * (zerosFounded + repetitions));
            total = total / (permutation(zerosFounded) * permutation(repetitions));
            return total;
        }

        if (zerosFounded >= 1) {
            int total;
            //caso apenas tenha um algarismo seguido de zeros
            if (totalDigits - zerosFounded == 1) {
                total = 1;
                return total;
            }

            total = permutation(totalDigits) - (permutation(totalDigits - 1) * (zerosFounded ));
            total = total / permutation(zerosFounded);
            return total;
        }

        if (repetitions >= 1) {
            int repetitionsPermutation = 1;
            for (Map.Entry<Character, Integer> entry : repetitionsMap.entrySet()) {
                System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
//                System.out.println("permution: " + entry.getValue() + " " + permutation(entry.getValue()));
                repetitionsPermutation = repetitionsPermutation * permutation(entry.getValue());
            }

            System.out.println("repetionsPermutation: " + repetitionsPermutation);
            System.out.println("permutation: " + permutation);
            return (permutation / repetitionsPermutation);
        }

        return permutation;
    }

    /**
     * Swap Characters at position
     *
     * @param a string value
     * @param i position 1
     * @param j position 2
     * @return swapped string
     */
    public String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    static Set<String> rearrangeSet = new HashSet<>();

    public void recursionTree(String word, int startIndex, int endIndex) {

        if (startIndex == endIndex) {
            System.out.println("word: " + word);
            if (!word.startsWith("0"))
                rearrangeSet.add(word);

        } else {
            for (int counter = startIndex; counter <= endIndex; counter++) {
                word = swap(word, startIndex, counter);
                recursionTree(word, startIndex + 1, endIndex);
                word = swap(word, startIndex, counter);
//                System.out.println(word);
            }
        }

    }

    public static void main(String args[]) {
        String str = "1234";
        System.out.println("rearrange: " + rearrangeCounter(Integer.parseInt(str)));

        int n = str.length();
        Rearrange rearrange = new Rearrange();
        rearrange.recursionTree(str, 0, n - 1);
        System.out.println(rearrangeSet);
        System.out.println("rearrange set size: " + rearrangeSet.size());
    }
}


//1112 => 1112 - 1121 - 1211 - 2111 ok
//1200 => 1002 - 1020 - 1200 - 2001 - 2010 - 2100 ok
//12000 => 10002 - 10020 - 10200 - 12000 - 20001 - 20010 - 20100 - 21000 ok
//100 => 100 ok
//120 => 102 - 120 - 201 - 210 ok
//110 => 101 - 110 ok
//42 => 24 - 42 ok
//432 => 234 - 243 - 324 - 342 - 423 - 432 ok

//4320 => 2043 - 2034 - 2340 - 2304 - 2403 - 2430 - 3042 - 3024 - 3204 - 3240 - 3420 - 3402 - 4032 - 4023 - 4203 - 4230 - 4320 - 4302 -
//43200 =>  20034 - 20043 - 20340 - 20304 - 20403 - 20430 -
//43201 =>  21034 - 21043 - 21340 - 21304 - 21403 - 21430 - 20034 - 20143 - 20340 - 20304 - 20403 - 20430 -
//1110 => 1011 - 1101 - 1110 Ok
//1100 => 1001 - 1010 - 1100 OK
//1110 => 1011 - 1101 - 1110 OK
//11100 => 10011 - 10101 - 10110 - 11001 - 11010 - 11100 OK
//11000 => 10001 - 10010 - 10100 - 11000 OK
//110000 -> 100001 - 100010 - 100100 - 101000 - 110000 OK
//11100 => 10011 - 10101 - 10110 - 11001 - 11010 - 11100 NOK

//4255 => 2455 - 2545 - 2554 - 4255 - 4525 - 4552 - 5245 - 5254 - 5425 - 5452 - 5524 - 5542
//4455 => 4455 - 4545 - 4554 - 5445 - 5454 - 5544
//4550 => 4550 - 4505 - 4055 - 5054 - 5540 - 5504
