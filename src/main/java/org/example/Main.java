package org.example;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static int countSTSIncome = 0;
    public static int countSTSIncomeMinusExpenses = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long income = 0L;
        long expenses = 0L;
        boolean isOperationOngoing = true;
        do {
            PrintMenu(income, expenses);
            if(scanner.hasNextInt()){
                switch (scanner.nextInt()){
                    case 1:
                        System.out.println("Введите сумму дохода: ");
                        income += scanner.nextLong();
                        break;
                    case 2:
                        System.out.println("Введите сумму расхода: ");
                        expenses += scanner.nextLong();
                        break;
                    case 3:
                        PrintBestSTS(income, expenses);
                        break;
                    default:
                        System.out.println("Неверный номер");
                }
            } else if (scanner.hasNext("end")) {
                isOperationOngoing = false;
                System.out.println("Досвидания!");
            }else {
                System.out.println("Неверное значение на входе, введите номер комманды или end");
            }
        }while (isOperationOngoing);
    }

    public static void PrintMenu(long income, long expenses){
        System.out.println("Текущий доход: "+income);
        System.out.println("Текущие расходы: "+expenses);
        System.out.println("Выберите операцию и введите её номер:\n" +
                "1. Добавить новый доход\n" +
                "2. Добавить новый расход\n" +
                "3. Выбрать систему налогообложения");
        System.out.println();
    }
    public static void PrintBestSTS(long income, long expenses){
        long stsIncome = STSIncome(income);
        long stsIncomeMinusExpenses = STSIncomeMinusExpenses(income, expenses);
        long stsDifference = Math.abs(stsIncome - stsIncomeMinusExpenses);

        if(stsIncome < stsIncomeMinusExpenses){
            System.out.println("УСН доходы выгоднее");
            System.out.println("УСН доходы составит: "+ stsIncome);
            System.out.println("УСН доходы минус расходы составит: "+ stsIncomeMinusExpenses);
            System.out.println("Экономия: "+ stsDifference);
        }else if (stsIncome > stsIncomeMinusExpenses){
            System.out.println("УСН доходы минус расходы выгоднее");
            System.out.println("УСН доходы составит: "+ stsIncome);
            System.out.println("УСН доходы минус расходы составит: "+ stsIncomeMinusExpenses);
            System.out.println("Экономия: "+ stsDifference);
        }else{
            System.out.println("Оба варианта УСН одинаковы");
            System.out.println("УСН доходы составит: "+ stsIncome);
            System.out.println("УСН доходы минус расходы составит: "+ stsIncomeMinusExpenses);
            System.out.println("Экономия: "+ stsDifference);
        }

    }
//    public static boolean isFirstOptionGood(long income, long expenses){
//        return STSIncome(income)<STSIncomeMinusExpenses(income, expenses);
//    }
    public static long STSIncome(long income){
        countSTSIncome++;
        System.out.println("УСН доходы посчитан "+countSTSIncome+" раз");
        return income*6/100;
    }
    public static long STSIncomeMinusExpenses(long income, long expenses){
        countSTSIncomeMinusExpenses++;
        System.out.println("УСН доходы минус расходы посчитан "+countSTSIncomeMinusExpenses+" раз");
        return (Math.max(0, income-expenses))*15/100;
    }
}