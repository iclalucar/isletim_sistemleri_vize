import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// 1'den 1.000.000'e kadar olan sayılardan oluşan ArrayList'i oluştur
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 1_000_000; i++) {
            numbers.add(i);
        }

		// ArrayList'i 4 eşit parçaya böl
        int chunkSize = numbers.size() / 4;
        ArrayList<Integer> chunk1 = new ArrayList<>(numbers.subList(0, chunkSize));
        ArrayList<Integer> chunk2 = new ArrayList<>(numbers.subList(chunkSize, 2 * chunkSize));
        ArrayList<Integer> chunk3 = new ArrayList<>(numbers.subList(2 * chunkSize, 3 * chunkSize));
        ArrayList<Integer> chunk4 = new ArrayList<>(numbers.subList(3 * chunkSize, numbers.size()));

        // Ortak ArrayList'ler
        ArrayList<Integer> evenNumbers = new ArrayList<>();
        ArrayList<Integer> oddNumbers = new ArrayList<>();
        ArrayList<Integer> primeNumbers = new ArrayList<>();

        // Her bir chunk için ayrı bir thread oluştur
        NumberTypeThread thread1 = new NumberTypeThread(chunk1, evenNumbers, oddNumbers, primeNumbers);
        NumberTypeThread thread2 = new NumberTypeThread(chunk2, evenNumbers, oddNumbers, primeNumbers);
        NumberTypeThread thread3 = new NumberTypeThread(chunk3, evenNumbers, oddNumbers, primeNumbers);
        NumberTypeThread thread4 = new NumberTypeThread(chunk4, evenNumbers, oddNumbers, primeNumbers);

        // Thread'leri başlat
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        // Tüm thread'lerin bitmesini bekleyerek sonuçları birleştir
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Sonuçları yazdır
        System.out.println("Toplam çift sayı adedi: " + evenNumbers.size());
        System.out.println("Toplam tek sayı adedi: " + oddNumbers.size());
        System.out.println("Toplam asal sayı adedi: " + primeNumbers.size());
    }
	
}
