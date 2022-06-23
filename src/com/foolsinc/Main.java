package com.foolsinc;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.Robot;

public class Main {

    private static final List<Sums> solutions = new ArrayList<>();

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        int goal;
        int digits;
        Sums excludedValues;
        goal = getAnInt(myObj,"Goal is: ");
        int start = 9;
        while (goal > 0) {
            if (goal < start) {
                start = goal - 1;
            }
            digits = getAnInt(myObj, "Number of Digits: ");
            excludedValues = getExcludedValues(myObj);
            solutions.clear();
            arrayCreator(start);
            boolean exclude;
            for (Sums solution : solutions) {
                exclude = false;
                if (solution.factors.size() == digits) {
                    int sum = 0;
                    for (int j = 0; j < solution.factors.size(); j++) {
                        sum += solution.factors.get(j);
                        if (excludedValues.factors.contains(solution.factors.get(j))) {
                            exclude = true;
                        }
                    }
                    if ((sum == goal) && !exclude) {
                        System.out.println(solution.factors);
                    }
                }
            }
            System.out.println("Excluded values: " + excludedValues.factors);
            start = 9;
            goal = getAnInt(myObj,"Goal is: ");
        }
    }


    private static Sums getExcludedValues(Scanner myObj) {
        Sums exclusions = new Sums();
        System.out.print("Enter values to exclude: ");
        int getValue = myObj.nextInt();
        while (getValue > 0){
            exclusions.factors.add(getValue);
            getValue = myObj.nextInt();
        }
        return exclusions;
    }

    private static int getAnInt(Scanner myObj, String question) {
        int enteredInt;
        System.out.print(question);
        enteredInt = myObj.nextInt();
        return enteredInt;
    }

    public static class Sums {
        public List<Integer> factors = new ArrayList<>();

        public Sums() {

        }
    }

    public static void factor(int goal, int number){
        int start = 9;
        if(goal <= 9) {
            start = goal-1;
        }
        if(goal >= (number*(number+1))/2){
            Sums sumers = new Sums();
            factor(goal, start, number, sumers);
        }
    }

    public static void factor(int goal, int start, int number, Sums sumers){
        System.out.println("Goal: "+goal+" start: "+ start+" Factors left: "+number+" || " + sumers.factors);
        int nextStart;
        if(goal >= (number*(number+1))/2) {
            for (int value = start; value >= (goal - (number - 1) * number / 2); value--) {
                sumers.factors.add(value);
                if (number > 1) {
                    nextStart = Math.min((goal - value), (value - 1));
                    System.out.println("in the need another");
                    factor(goal - value, nextStart, number - 1, sumers);
                } else {
                    System.out.println(" did it work "+ value);
                    System.out.println(sumers.factors);
                    solutions.add(sumers);
                    for (Sums solution : solutions) {
                        System.out.println("Factors: " + solution.factors);
                    }
                }
                sumers.factors.remove(sumers.factors.size() - 1);
            }
            System.out.println("Solutions:   !!!!");
            for (Sums solution : solutions) {
                System.out.println(solution.factors);
            }
        }
    }

    public static void arrayCreator(int start) {
        Sums tempSums;
        for(int a = start; a > 0; a--) {
//            if(a==1){
                tempSums = new Sums();
                tempSums.factors.add(a);
                solutions.add(tempSums);
//            }
            for (int b = a - 1; b > 0; b--) {
//                if(b==1){
                    tempSums = new Sums();
                    tempSums.factors.add(a);
                    tempSums.factors.add(b);
                    solutions.add(tempSums);
//                }
                for (int c = b - 1; c > 0; c--) {
//                    if(c==1){
                        tempSums = new Sums();
                        tempSums.factors.add(a);
                        tempSums.factors.add(b);
                        tempSums.factors.add(c);
                        solutions.add(tempSums);
//                    }
                    for (int d = c - 1; d > 0; d--) {
//                        if(d==1){
                            tempSums = new Sums();
                            tempSums.factors.add(a);
                            tempSums.factors.add(b);
                            tempSums.factors.add(c);
                            tempSums.factors.add(d);
                            solutions.add(tempSums);
//                        }
                        for (int e = d - 1; e > 0; e--) {
                            tempSums = new Sums();
                            tempSums.factors.add(a);
                            tempSums.factors.add(b);
                            tempSums.factors.add(c);
                            tempSums.factors.add(d);
                            tempSums.factors.add(e);
                            solutions.add(tempSums);
                        }
                    }
                }
            }
        }
    }
}
