import java.util.ArrayList;

class Emirps {

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        long n = 120000;
//        System.out.println(simpleNumGen(n));

        findEmirp(n);
        long timeSpent = System.currentTimeMillis();
        System.out.println("программа выполнялась " + (timeSpent - startTime) + " миллисекунд");
    }

    private static ArrayList<Integer> simpleNumGen(long n) {

        ArrayList<Integer> list = new ArrayList();
        list.add(2);
        list.add(3);
        list.add(5);
        list.add(7);

        long roundOfn = getRoundOfn(n);

        for (int countOfnum = 9; countOfnum < roundOfn; countOfnum++){
            if(countOfnum % 2 == 0 || countOfnum % 3 == 0 || countOfnum % 5 == 0 || countOfnum% 7 == 0 || ifHasSqrt(countOfnum)){     //
                continue;
            }
            if (ifPrime(countOfnum, list)){
                list.add(countOfnum);
            }
        }
        System.out.println(list.size());
        System.out.println(list.get(list.size() - 1));
        return list;
    }

    private static boolean ifHasSqrt(int countOfn) {
        int sqrtOfn = (int) Math.sqrt(countOfn);
        int quadOfnum = sqrtOfn * sqrtOfn;
        return quadOfnum == countOfn;
    }

    public static long[] findEmirp(long n){
        long[] result = new long[3];
        ArrayList<Integer> listOfSimples = simpleNumGen(n);
        deleteNonPalindromes(listOfSimples, n);

        result[0] = listOfSimples.size();
        result[1] = listOfSimples.get(listOfSimples.size() - 1);
        for (int primeNum = 0; primeNum< listOfSimples.size(); primeNum++ ) {
            result[2] += listOfSimples.get(primeNum);
        }
        System.out.println("Count of primes, who got emirp: " + result[0] + " Max int of primes: " + result[1] + " Sum of all primes: " + result[2]);
        return result;
    }

    private static ArrayList<Integer> deleteNonPalindromes(ArrayList<Integer> listOfSimples, long n) {

        for (int i = 0; i <= listOfSimples.size() - 1; i++){
            if (listOfSimples.get(i) < 13 || listOfSimples.get(i) == inverse(listOfSimples.get(i)) || listOfSimples.get(i) > n ||!listOfSimples.contains(inverse(listOfSimples.get(i)))){
                listOfSimples.remove(i);
                i--;
            }
        }
        return listOfSimples;
    }

    private static int inverse(int value) {
        String reverse = new StringBuilder(String.valueOf(value)).reverse().toString();
        int result = Integer.parseInt(reverse);
//        while(value > 0) {
//            result = result * 10 + value % 10;
//            value /= 10;
//        }
        return result;
    }

    private  static boolean ifPrime(int countOfNum, ArrayList<Integer> list){
        int indexOfPrimes = 4;
        while(indexOfPrimes < Math.sqrt(countOfNum)){
            if (countOfNum % list.get(indexOfPrimes) == 0){
                return false;
            }
            else {
                indexOfPrimes++;
            }
        }
        return true;
    }

    private static long getRoundOfn(long n){
        int firstNumOfn = (int) n;
        int countOfNums = 0;
        int countOfZeros = 10;
        long roundOfn = 0;
        while(firstNumOfn > 9){
            if (firstNumOfn % 10 != 0){
                countOfNums++;
            }
            firstNumOfn = firstNumOfn / 10;
            countOfZeros *= 10;
        }
        if (countOfNums == 0 && firstNumOfn == 1){
            roundOfn = n;
        }
        else {
            roundOfn = countOfZeros;
        }
        return roundOfn;
    }
}
// n = 12 000;  2205 119993 137429553  программа выполнялась 15950 миллисекунд

//    Count of primes, who got emirp: 2205 Max int of primes: 119993 Sum of all primes: 137429553
//        программа выполнялась 30240 миллисекунд
