import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
    Main main = new Main();
    String word = "god";
    String palindromeWord = "arara";
    String reverseWord = main.reverseWord(word);
    System.out.println("isPalindrome(" + word + "): " + reverseWord.equals(word));
    reverseWord = main.reverseWord(palindromeWord);
    System.out.println("isPalindrome(" + palindromeWord + "): " + reverseWord.equals(palindromeWord));

    char character = 'o';
    String wordWithoutCharacter = main.removeCharacterWithRecursion(word, character);
    System.out.println("Word after removing character(recursion): " + wordWithoutCharacter);
    wordWithoutCharacter = main.removeCharacterWithLoop(word, character);
    System.out.println("Word after removing character(loop): " + wordWithoutCharacter);

    System.out.println("Factorial: " + main.factorial("12345"));

    System.out.println("Permutations(" + word + "):");
    main.permutations(word, 0, word.length() - 1);

    System.out.println("Countdown:");
    main.countdown(5);

    int[] primeNumbers = new int[]{1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 91, 97};
    int targetValue = 23;
    System.out.println("Binary Search:" + main.binarySearch(primeNumbers, targetValue));
    System.out.println("Binary Search(recursive):" + main.binarySearchRecursive(primeNumbers, targetValue, 0, primeNumbers.length - 1));

    int[] numberToSwap = new int[]{9, 4, 7, 1, 2, 6, 5};
    System.out.println("Array before swap: " + Arrays.toString(numberToSwap));
    main.swap(numberToSwap, 2, 0);
    System.out.println("Array after swap: " + Arrays.toString(numberToSwap));

    numberToSwap = new int[]{13, 19, 18, 4, 10};
    int indexOfMinimum = main.indexOfMinimum(numberToSwap, 1);
    System.out.println("Index of minimum: " + indexOfMinimum);
    main.selectionSort(numberToSwap);
    System.out.println("Selection sort: " + Arrays.toString(numberToSwap));

    int[] array = new int[]{2, 3, 5, 7, 11, 13, 9, 6};
    array = new int[]{12, 14, 8, 1, 5, 11, 0};
//    int rightIndex = 5;
//    int value = 9;
//    main.insert(array, rightIndex, value);
    System.out.println("Before sort: " + Arrays.toString(array));
    main.insertionSort(array);
    System.out.println("Insertion sort: " + Arrays.toString(array));

    int iterativeFactorial = main.iterativeFactorial(5);
    System.out.println("Iterative Factorial: " + iterativeFactorial);
    int recursiveFactorial = main.recursiveFactorial(5);
    System.out.println("Recursive Factorial: " + recursiveFactorial);

    System.out.println("IsPalindrome: " + main.isPalindrome("roma"));
    System.out.println("IsPalindrome: " + main.isPalindrome("xyzyzyx"));

    System.out.println("Power: " + main.power(2, 4));

//    System.out.println("Towers of Hanoi ");
//    main.solveHanoi(5, 1, 2);

    System.out.println("Merge Sort");
    array = new int[]{5, 4, 3, 2, 1};
    main.mergeSort(array, 0, array.length);
    System.out.println(Arrays.toString(array));
  }

  // Takes in an array that has two sorted subarrays,
  //  from [p..q] and [q+1..r], and merges the array
//  private void merge(int[] array, int p, int q, int r) {
//    // This code has been purposefully obfuscated,
//    //  as you'll write it yourself in next challenge.
//                                                                                                                 int i, j, k; int n1 = q - p + 1; int n2 =  r - q; int[] L = new int[n1]; int[] R = new int[n2]; for (i = 0; i < n1; i++) L[i] = array[p + i]; for (j = 0; j < n2; j++) R[j] = array[q + 1+ j]; i = 0;j = 0;k = p; while (i < n1 && j < n2){ if (L[i] <= R[j]) { array[k] = L[i]; i++;} else { array[k] = R[j]; j++; } k++; } while (i < n1) { array[k] = L[i]; i++; k++; } while (j < n2) { array[k] = R[j]; j++; k++; }
//  }

  private void merge(int[] array, int p, int q, int r) {
    int[] tempArray1 = Arrays.copyOfRange(array, p, q + 1);
    int[] tempArray2 = Arrays.copyOfRange(array, q + 1, r + 1);

    int i = 0, j = 0;

    for (int k = p; k < r; k++) {
      int temp1 = tempArray1[i];
      int temp2 = tempArray2[j];

      if (temp1 < array[k]) {
        array[k] = temp1;
        i++;
      }
      if (temp2 < array[k]) {
        array[k] = temp2;
        j++;
      }
      k++;
    }
  }

  // Takes in an array and recursively merge sorts it
  private void mergeSort(int[] array, int p, int r) {
    r = r - 1;
    if (p >= r) {
      return;
    }

    int q = (p + r) / 2;
    mergeSort(array, p, q + 1);
    mergeSort(array, q + 1, r + 1);
    merge(array, p, q, r);

//    if (p >= r) {
//      return;
//    }
//    int q = (p + r) / 2;
//    mergeSort(array, p, q );
//    mergeSort(array, q + 1, r);
//    merge(array, p, q, r );
  }

  public void modeDisk(int fromPeg, int toPeg) {

  }

  public int getSparePeg(int fromPeg, int toPeg) {
    int sparePeg = 1;
    for (int i = 1; i <= 3; i++) {
      if (fromPeg != i || toPeg != i) {
        sparePeg = i;
      }
    }
    return sparePeg;
  }

  public void solveHanoi(int disks, int fromPeg, int toPeg) {
    if (disks == 0) {
      return;
    }
    if (disks == 1) {
      modeDisk(fromPeg, toPeg);
    }

    System.out.println("Spare Peg: " + getSparePeg(fromPeg, toPeg));
    int sparePeg = getSparePeg(fromPeg, toPeg);
    solveHanoi(disks - 1, fromPeg, sparePeg);
  }

  public double power(int x, int n) {
    if (n == 0) {
      return 1;
    }

    if (n < 0) {
      return 1 / power(x, -n);
    }

    if (n % 2 != 0) {
      return power(x, n - 1) * x;
    }

    if (n % 2 == 0) {
      double y = power(x, n / 2);
      return y * y;
    }
    return x;
  }

  public boolean isPalindrome(String word) {
    if (word.isEmpty() || word.length() == 1) {
      return true;
    }

    if (word.charAt(0) != word.charAt(word.length() - 1)) {
      return false;
    }
    return isPalindrome(word.substring(1, word.length() - 1));
  }

  public int recursiveFactorial(int n) {
    // base case:
    if (n == 0) {
      return 1;
    }

    // recursive case:
    return recursiveFactorial(n - 1) * n;
  }

  public int iterativeFactorial(int n) {
    int result = 1;
    if (n == 0) {
      return result;
    }

    // Implement this method
    for (int i = 1; i <= n; i++) {
      result = result * i;
    }

    return result;
  }

  public void insertionSort(int[] array) {
    //Write this method
    for (int i = 0; i < array.length; i++) {
      int temp = array[i];
      insert(array, i, temp);
    }
  }

  private void insert(int[] array, int rightIndex, int value) {
    // write this method
    for (int i = rightIndex; i >= 0; i--) {
      int temp = array[i];
      if (temp > value) {
        array[i + 1] = temp;
        array[i] = value;
      }
    }
  }

  private void selectionSort(int[] array) {
    for (int i = 0; i < array.length; i++) {
      int indexOfMinimum = indexOfMinimum(array, i);
      swap(array, i, indexOfMinimum);
    }
  }

  public int indexOfMinimum(int[] array, int startIndex) {
    // Set initial values for minValue and minIndex,
    // based on the leftmost entry in the subarray:
    int minValue = array[startIndex];
    int minIndex = startIndex;

    // Loop over items starting with startIndex,
    // updating minValue and minIndex as needed:
    for (int i = startIndex; i < array.length; i++) {
      int temp = array[i];
      if (minValue > temp) {
        minIndex = i;
        minValue = temp;
      }
    }
    return minIndex;
  }

  private void swap(int[] array, int firstIndex, int secondIndex) {
    int length = array.length;

    if (firstIndex < length && secondIndex < length) {
      int first = array[firstIndex];
      int second = array[secondIndex];

      array[firstIndex] = second;
      array[secondIndex] = first;
    }
  }

  private int binarySearch(int[] array, int targetValue) {
    int min = 0;
    int max = array.length - 1;
    int guess;

    do {
      guess = (min + max) / 2;
      int guessedNumber = array[guess];
      if (targetValue == guessedNumber) {
        return guess;
      }
      if (guessedNumber < targetValue) {
        min = guess + 1;
      } else {
        max = guess - 1;
      }
    } while (max >= min);

    return -1;
  }

  private int binarySearchRecursive(int[] array, int targetValue, int min, int max) {
    int guess = (min + max) / 2;
    int guessedNumber = array[guess];

    if (max < min) {
      return -1;
    }
    if (targetValue == guessedNumber) {
      return guess;
    }

    if (guessedNumber < targetValue) {
      min = guess + 1;
    } else {
      max = guess - 1;
    }
    return binarySearchRecursive(array, targetValue, min, max);
  }

  private void countdown(int number) {
    if (number == 0) {
      System.out.println(0);
    } else {
      System.out.println(number);
      countdown(number - 1);
    }
  }

  private int factorial(String numberStr) {
    if (numberStr.isEmpty()) {
      return 1;
    }
    int length = numberStr.length();
    return factorial(numberStr.substring(0, length - 1)) * length;
  }

  private String swap(String a, int i, int j) {
    char temp;
    char[] charArray = a.toCharArray();
    temp = charArray[i];
    charArray[i] = charArray[j];
    charArray[j] = temp;
    return String.valueOf(charArray);
  }

  //god
  //god - ogd - dog
  //gdo - odg - dgo
  private void permutations(String word, int startIndex, int endIndex) {
    if (startIndex == endIndex) {
      System.out.println("-> " + word);
    } else {
      for (int counter = startIndex; counter <= endIndex; counter++) {
        word = swap(word, startIndex, counter);
        permutations(word, startIndex + 1, endIndex);
      }
    }
  }

  private String removeCharacterWithLoop(String word, char character) {
    StringBuffer wordWithoutCharacter = new StringBuffer();

    for (int i = 0; i < word.length(); i++) {
      if (word.charAt(i) != character) {
        wordWithoutCharacter.append(word.charAt(i));
      }
    }
    return wordWithoutCharacter.toString();
  }

  private String removeCharacterWithRecursion(String word, char character) {
    if (word.isEmpty()) {
      return word;
    }
    char lastCharacter = word.charAt(word.length() - 1);
    if (lastCharacter == character) {
      word = word.substring(0, word.length() - 1);
      return removeCharacterWithRecursion(word, character);
    }

    return removeCharacterWithRecursion(word.substring(0, word.length() - 1), character) + lastCharacter;
  }

  private String reverseWord(String word) {
    if (word.isEmpty()) {
      return word;
    }
    char lastLetter = word.charAt(word.length() - 1);
    //    return  reverseWord( word.substring(0, word.length() - 1)) + lastLetter;
    return lastLetter + reverseWord(word.substring(0, word.length() - 1));
  }
}
