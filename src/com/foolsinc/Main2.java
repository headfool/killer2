package com.foolsinc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main2 {

    private static List<Sums> solutions = new ArrayList<>();

    private static Sums sumers = new Sums();
    public static void main(String[] args) throws CloneNotSupportedException {
        Scanner myObj = new Scanner(System.in);
        int goal = 21;
        int digits = 4;
        factor(goal,digits);
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
            for (int i = 0; i < solutions.size(); i++) {
                exclude = false;
                if (solutions.get(i).factors.size() == digits) {
                    int sum = 0;
                    for (int j = 0; j < solutions.get(i).factors.size(); j++) {
                        sum += solutions.get(i).factors.get(j);
                        if (excludedValues.factors.contains(solutions.get(i).factors.get(j))){
                            exclude = true;
                        }
                    }
                    if ((sum == goal) && !exclude) {
                        System.out.println(solutions.get(i).factors);
                    }
                }
            }
            System.out.println("Excluded values: " + excludedValues.factors);
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

        public Sums(Sums obj){
            factors = obj.factors;
        }

        public Sums() {

        }
        @Override
        public Sums clone() throws CloneNotSupportedException {
            return (Sums) super.clone();
        }
    }

    public static void make(int goal, int start, int number, Sums factor){
        if(number>1) {
            for (int add = start; add >= number; add--) {

                System.out.print(add + " ");
                make(goal-add,add -1 , number - 1, factor);
            }
        } else {
            System.out.print(start + " ");
        }

        System.out.println(" ");
    }

    public static void factor(int goal, int number) throws CloneNotSupportedException {
        int start = 9;
        if(goal - (number * (number -1)/2) <= 9) {
            start = goal - (number * (number -1)/2);
        }
        if(goal >= (number*(number+1))/2){
            System.out.println("in first factor");
            factor(goal, start, number,sumers);
        }
    }

    public static void factor(int goal, int start, int number, Sums originalSums) throws CloneNotSupportedException {
        System.out.println("Goal: "+goal+" start: "+ start+" Factors left: "+number+" || " + originalSums.factors);
        int nextStart;
        if(goal - (number * (number -1)/2) < start) {
            start = goal - (number * (number -1)/2);
        }
        if(goal >= (number*(number+1))/2) {
            for (int value = start; value >= (((number - 1) * number)/ 2); value--) {
                originalSums.factors.add(value);
                if (number > 1) {
                    if ((goal-value) < (value -1)) {
                        nextStart = goal - value;
                    } else {
                        nextStart = value -1;
                    }

                    System.out.println(originalSums.factors);
                    System.out.println("in the need another");
                    factor(goal - value, nextStart, number - 1,originalSums);
                    originalSums.factors.remove(originalSums.factors.size() - 1);
                } else {
                    System.out.println(" did it work "+ value);
                    if (goal > value){
                        return;
                    }
                    Sums newSums = new Sums();
                        for (int x = 0; x< (originalSums.factors.size()); x++) {
                            newSums.factors.add(originalSums.factors.get(x));
                        }
                    solutions.add(newSums);
                    return;
//                    for(int i = 0; i < solutions.size(); i++) {
//                        System.out.println("Factors: " + solutions.get(i).factors);
//                    }
                }
                if(originalSums.factors.size() > 0) {
                    originalSums.factors.remove(originalSums.factors.size() - 1);
                }
            }
            System.out.println("Solutions:   !!!!");
            for(int i = 0; i < solutions.size(); i++) {
                System.out.println(solutions.get(i).factors);
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
