import java.util.ArrayList;

class NumberTypeThread extends Thread {
    private ArrayList<Integer> numbers;
    private ArrayList<Integer> evenNumbers;
    private ArrayList<Integer> oddNumbers;
    private ArrayList<Integer> primeNumbers;

    public NumberTypeThread(ArrayList<Integer> numbers, ArrayList<Integer> evenNumbers, ArrayList<Integer> oddNumbers, ArrayList<Integer> primeNumbers) {
        this.numbers = numbers;
        this.evenNumbers = evenNumbers;
        this.oddNumbers = oddNumbers;
        this.primeNumbers = primeNumbers;
    }

    @Override
    public void run() {
        for (int number : numbers) {
            if (number % 2 == 0) {
                evenNumbers.add(number);
            } else {
                oddNumbers.add(number);
            }

            if (isPrime(number)) {
                primeNumbers.add(number);
            }
        }
    }

    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
