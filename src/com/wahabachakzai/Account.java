package com.wahabachakzai;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Account {

    private int customerNumber;
    private int pinNumber;
    private double checkingBalance = 0;
    private double savingBalance = 0;

    Scanner input = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$' ###,##0.00");


    public Account(){
    }

    public Account(int customerNumber, int pinNumber){
        this.customerNumber = customerNumber;
        this.pinNumber = pinNumber;
    }

    public Account(
            int customerNumber,
            int pinNumber,
            double checkingBalance,
            double savingBalance){
        this.customerNumber = customerNumber;
        this.pinNumber = pinNumber;
        this.checkingBalance = checkingBalance;
        this.savingBalance = savingBalance;
    }

    public int setCustomerNumber(int customerNumber){
        this.customerNumber = customerNumber;
        return customerNumber;
    }

    public int getCustomerNumber(){ return customerNumber; }

    public int setPinNumber(int pinNumber){
        this.pinNumber = pinNumber;
        return pinNumber;
    }

    public int getPinNumber(){  return pinNumber; }

    public double getCheckingBalance() {
        return checkingBalance;
    }

    public double getSavingBalance(){return savingBalance; }

    public double calcCheckingWithdraw(double amount){
        checkingBalance = (checkingBalance - amount);
        return checkingBalance;
    }

    public double calcSavingWithdraw(double amount){
        savingBalance = (savingBalance - amount);
        return  savingBalance;
    }

    public double calcCheckingDeposit(double amount){
        checkingBalance =( checkingBalance + amount);
        return checkingBalance;
    }

    public double calcSavingDeposit(double amount){
        savingBalance = (savingBalance + amount);
        return amount;
    }

    public void calcCheckTransfer(double amount){
        checkingBalance = checkingBalance - amount;
        savingBalance = savingBalance + amount;
    }

    public void calcSavingTransfer(double amount){
        savingBalance = savingBalance - amount;
        checkingBalance = checkingBalance + amount;
    }


    public void getCheckingWithdrawInput(){
        boolean end = false;

        while(!end){
            try{
                System.out.println(String.format("\n Current Checking Balance = " + moneyFormat.format(checkingBalance)));
                System.out.println("\n Money you want to withdraw from checking ");
                double amount = input.nextDouble();
                if((checkingBalance - amount) >= 0 && amount > 0 ){
                    calcCheckingWithdraw(amount);
                    System.out.println("\n Current checkings amount balance: " + moneyFormat.format(checkingBalance));
                    end= true;
                }else{
                    System.out.println("Balance can not be negative");
                }
            }catch(InputMismatchException e){
                System.out.println("\n Invlaid option !!!");
                input.next();
            }
        }
    }



    public void getSavingWithdrawInput(){

        boolean end = false;
        while(!end){
            try{
                System.out.println("\n Currently Savings Account Balance: "+ moneyFormat.format(savingBalance));
                System.out.println("\n Amount you want to withdraw from savings Account");
                double amount = input.nextDouble();
                if((savingBalance - amount ) >= 0 && amount >= 0 ){
                    calcSavingWithdraw(amount);
                    System.out.println("\n Current Saving account balance:  " + moneyFormat.format(savingBalance));
                    end = true;
                }else{
                    System.out.println("\n Balance Cannnot be negative ");
                }
            }catch(InputMismatchException e){
                System.out.println("\n Invalid Choice.");
                input.next();
            }
        }
    }


    public void getCheckingDepositInput(){
        boolean end = false;

        while(!end){
            try{
                System.out.println("\n Current checking Account Balance: " + moneyFormat.format(checkingBalance));
                System.out.println("\n Amount you want to deposit from Account: ");
                double amount = input.nextDouble();
                if((checkingBalance + amount) >= 0 && amount > 0 ){
                    calcCheckingDeposit(amount);
                    System.out.println("\n Current checkings Account Balance: " + moneyFormat.format(checkingBalance));
                    end = true;
                }else{
                    System.out.println("Balance can not be negative");
                    end = true;
                }
            }catch(InputMismatchException e){
                System.out.println("\n Invalid Choice");
                input.next();
            }
        }
    }


    public void getSavingDepositInput(){
        System.out.println("\n *** Welcome to Savings Deposit ***");
        boolean end = false;

        while(!end){
            try{
                System.out.println("\n Current savings Account Balance: "+ moneyFormat.format(savingBalance));
                System.out.println("\n Amount you want to deposit into your savings account: ");
                double amount = input.nextDouble();

                if((savingBalance + amount) >= 0 && amount > 0 ){
                    calcSavingDeposit(amount);
                    System.out.println("\n Current savings Account Balance: "+ moneyFormat.format(savingBalance));
                    end = true;
                }else{
                    System.out.println("\n !!! Balance can not be negative !!!");
                }
            }catch(InputMismatchException e){
                System.out.println("\n Invalid Choice !!!");
                input.next();
            }
        }
    }


    public void getTransferInput(String accountType){
        boolean end = false;
        System.out.println("\n *** Welcome to Transfer Section ***");

        while(!end){
            try{
                if(accountType.equals("Checkings")){
                    System.out.println("\n Select an account you wish to transfer funds to:");
                    System.out.println("1. Savings");
                    System.out.println("2. Exit");
                    System.out.println("\n Choice:");
                    int choice = input.nextInt();

                    switch(choice){
                        case 1:
                            System.out.println("\n Current checkings account balance: " + moneyFormat.format(checkingBalance));
                            System.out.println("\n Amount you want to deposite into your account: ");
                            double amount = input.nextDouble();

                            if((savingBalance + amount) >= 0 && (checkingBalance - amount) >= 0 && amount >= 0 ){
                                calcCheckTransfer(amount);
                                System.out.println("\n Current Savings Account Balance: " + moneyFormat.format(savingBalance));
                                System.out.println("\n Current checkings Account Balance: "+ moneyFormat.format(checkingBalance));
                                end = true;
                            }else{
                                System.out.println("\n !!! Balance can not be negative !!!");
                            }
                        case 2:
                            return;
                        default:
                            System.out.println("\n Invalid Choice");
                            break;
                    }
                }else if(accountType.equals("Savings")){
                    System.out.println("\n Select an Account you wish to transfer fund to :");
                    System.out.println("\n 1. Checkings");
                    System.out.println("\n 2. Exit");
                    System.out.println("\n Choice:");
                    int choice = input.nextInt();

                    switch(choice){
                        case 1:
                            System.out.println("\n Your Savings Account Balance is: " + moneyFormat.format(savingBalance));
                            System.out.println("\n Amount you want to deposit to your savings account: ");
                            double amount = input.nextDouble();

                            if((checkingBalance + amount) >= 0 && (savingBalance - amount) >= 0 && amount >= 0 ){
                                calcSavingTransfer(amount);
                                System.out.println("\n Current checkings account balance: " + moneyFormat.format(checkingBalance));
                                System.out.println("\n Current savings account balance: "+ moneyFormat.format(savingBalance));
                                end = true;
                            }else{
                                System.out.println("\n Balance can not be negative");
                            }
                        case 2:
                            return;
                        default:
                            System.out.println("\n !!! Invalid Choice !!!");
                            break;
                    }
                }
            }catch(InputMismatchException e){
                System.out.println("\n Invalid Choice.");
                input.next();
            }
        }
    }











}
