package com.nikhil;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        List<String> bingoPool = new ArrayList<>(75);

        int start = 1;
        for (char c: "BINGO".toCharArray()) {
            for (int i = start; i < start+15; i++) {
                bingoPool.add(""+c+i);
//                System.out.println(""+c+i);
            }
            start+=15;
        }

        Collections.shuffle(bingoPool);

        for (int i = 0; i < 15; i++) {
            System.out.println(bingoPool.get(i));
        }

        List<String> firstOnes = new ArrayList<>(bingoPool.subList(0,15));
        firstOnes.sort(Comparator.naturalOrder());

        firstOnes.replaceAll(s -> {
            if (s.indexOf('G') == 0 || s.indexOf("O") == 0) {
                String updated = s.charAt(0) + "-" + s.substring(1);
                System.out.print(updated + " ");
                return updated;
            }
            return s;
        });

        System.out.println("\n----------------------------------");

        for (int i = 0; i < 15; i++) {
            System.out.println(bingoPool.get(i));
        }
        System.out.println("------------------------------------");

//        String[] strings = {"One","Two","Three"};
//
//        var firstStream = Arrays.stream(strings)
//                .sorted(Comparator.reverseOrder());
////                .forEach(System.out::println);
//
//        System.out.println();
//
//        var secondStream = Stream.of("Six","Seven","Eight")
//                .map(String::toUpperCase);
////                .forEach(System.out::println);
//
//        Stream.concat(secondStream,firstStream)
//                .map(s -> s.charAt(0) + " - " + s)
//                .forEach(System.out::println);

        /**
        Map doesn't implement Collection interface, it is a kind of different category
         But we can use map's collection views, keySet, entrySet or values to generate a stream to
         process parts of Maps
         **/

//        Map<Character, int[]> myMap = new LinkedHashMap<>();


        IntStream.iterate(1, n -> n+1)
//                .map(Main::isPrime)
                .limit(20)
                .forEach(s -> System.out.print(s+" "));


        var tempStream = bingoPool.stream()
                .limit(15)
                .filter(s -> s.indexOf('G') == 0 || s.indexOf("O") == 0)
                .map(s -> s.charAt(0) + "-" + s.substring(1))
                .sorted();
//                .forEach(s -> System.out.print(s + " "));

        tempStream.forEach(s-> System.out.print(s + " "));
        System.out.println("\n----------------------------------");

        String[] strings = {"One", "Two", "Three"};
        var firstStream = Arrays.stream(strings)
                .sorted(Comparator.reverseOrder());
//                .forEach(System.out::println);

        var secondStream = Stream.of("Six", "Five", "Four")
                .map(String::toUpperCase);
//                .forEach(System.out::println);

        Stream.concat(secondStream, firstStream)
                .map(s -> s.charAt(0) + " - " + s)
                .forEach(System.out::println);

        Map<Character, int[]> myMap = new LinkedHashMap<>();
        int bingoIndex = 1;
        for (char c : "BINGO".toCharArray()) {
            int[] numbers = new int[15];
            int labelNo = bingoIndex;
            Arrays.setAll(numbers, i -> i + labelNo);
            myMap.put(c, numbers);
            bingoIndex += 15;
        }

        myMap.entrySet()
                .stream()
                .map(e -> e.getKey() + " has range: " + e.getValue()[0] + " - " +
                        e.getValue()[e.getValue().length - 1])
                .forEach(System.out::println);

        Random random = new Random();
        Stream.generate(() -> random.nextInt(2))
                .limit(10)
                .forEach(s -> System.out.print(s + " "));

        System.out.println();
        IntStream.iterate(1, n -> n + 1)
                .filter(Main::isPrime)
                .limit(20)
                .forEach(s -> System.out.print(s + " "));

        System.out.println();
        IntStream.iterate(1, n -> n + 1)
                .limit(100)
                .filter(Main::isPrime)
                .forEach(s -> System.out.print(s + " "));

        System.out.println();
        IntStream.iterate(1, n -> n <= 100, n -> n + 1)
                .filter(Main::isPrime)
                .forEach(s -> System.out.print(s + " "));

        System.out.println();
        IntStream.rangeClosed(1, 100)
                .filter(Main::isPrime)
                .forEach(s -> System.out.print(s + " "));


        System.out.println("==============CHALLENGE=======================");



    }

    public static boolean isPrime(int n) {
        if (n<=2) {
            return n==2;
        }
        for (int i = 2; i < n; i++) {
            if ((n%i) == 0 ) {
                return false;
            }
        }
        return true;

    }
}