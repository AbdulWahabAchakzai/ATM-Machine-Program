package com.wahabachakzai;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class MenuOptions{

    Scanner menuInput = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");
    HashMap<Integer, Account> data = new HashMap<Integer, Account>();




    public void getLogin() throws IOException{
       boolean end = false;
       int customerNo = 0;
       int pinCode = 0;

       while(!end){
           try {
               System.out.print("\n Enter your customer number: ");
               customerNo = menuInput.nextInt();
               System.out.print("\n Enter your PIN code: ");
               pinCode = menuInput.nextInt();

               Iterator it = data.entrySet().iterator();
               while(it.hasNext()){
                Map.Entry pair = (Map.Entry)it.next();
                Account acc = (Account)pair.getValue();
                if(data.containsKey(customerNo) && pinCode == acc.getPinNumber()){
                    getAccountType(null);
                    end = true;
                    break;
                }
               }

               if(!end){
                   System.out.println("Wrong Customer Number or Pin code !!!");
               }

           }catch(InputMismatchException e){
               System.out.println("\n Invalid Charachters. Only  Numbers: " + e.getMessage());
           }
       }

    }


    public void getAccountType(Account acc){
        boolean end = false;
        while(!end){
            try{
                System.out.println("Select type of account you want to enter");
                System.out.println("Type 1 - Checkings Account");
                System.out.println("Type 2 - Savings Account");
                System.out.println("Type 3 - Exit");
                System.out.print("Your Choice: ");

                int accountType = menuInput.nextInt();

                switch(accountType){
                    case(1):
                        getCheckingAccount(acc);
                        end = true;
                        break;
                    case(2):
                        getSavingAccount(acc);
                        end = true;
                        break;
                    default:
                        System.out.println("\n Invalid Choice");
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid Choice");
                menuInput.next();
            }
        }
    }

    public void getCheckingAccount(Account account) {
        boolean end = false;

        System.out.println("\n ___ Welcome to Checkings Account ___");
        System.out.println(" Choose your desire checking Account");

        while(!end){
            try{
                System.out.println("Type 1 - View Balance");
                System.out.println("Type 2 - Withdraw Funds");
                System.out.println("Type 3 - Deposit Funds");
                System.out.println("Type 4 - Transfer Funds");
                System.out.println("Type 5 - Exit");
                System.out.print("\n Choice: ");
                int choice = menuInput.nextInt();

                switch(choice){
                    case 1:
                        System.out.println("\n Checking Account Balance: "+ moneyFormat.format(account.getCheckingBalance()));
                        break;
                    case 2:
                        account.getCheckingWithdrawInput();
                        end = true;
                        break;
                    case 3:
                        account.getCheckingDepositInput();
                        end = true;
                        break;
                    case 4:
                        account.getCheckingDepositInput();
                        end = true;
                        break;
                    case 5:
                        System.out.println("Exiting --->>>");
                        end = true;
                        break;
                    default:
                        System.out.println("Invlaid option");
                }
            }catch(InputMismatchException e){
                System.out.print("\n Invalid Choice");
                menuInput.next();
            }
        }
    }



    public void getSavingAccount(Account acc){
        boolean end = false;

        System.out.println("\n ___Welcome to Savings Account___");

        while(!end){
            try{
                System.out.println("Savings Account: ");
                System.out.println("Type 1 - View Balance");
                System.out.println("Type 2 - Withdraw Funds");
                System.out.println("Type 3 - Deposit Funds");
                System.out.println("Type 4 - Transfer Funds");
                System.out.println("Type 5 - Exit");
                System.out.print("Choice: ");
                int choice = menuInput.nextInt();

                switch (choice){
                    case 1:
                        System.out.println("\n Savings Account Balance: " + moneyFormat.format(acc.getSavingBalance()));
                        break;
                    case 2:
                        acc.getCheckingWithdrawInput();
                        break;
                    case 3:
                        acc.getCheckingDepositInput();
                    case 4:
                        acc.getTransferInput("Savings");
                    case 5:
                        end = true;
                        break;
                    default:
                        System.out.println("\n !!! Invalid Choice");
                }
            }catch(InputMismatchException e){
                System.out.println("\n !!! Invalid choice !!!");
                menuInput.next();
            }
        }
    }



    public void createAccount() throws IOException{
        int customerAccountNo = 0;
        boolean end = false;

        while(!end){
            try{
                System.out.print("\n Enter your customer number: ");
                customerAccountNo = menuInput.nextInt();
                Iterator it = data.entrySet().iterator();
                while(it.hasNext()){
                    Map.Entry pair = (Map.Entry) it.next();
                    if(!data.containsKey(customerAccountNo)){
                        end = true;
                    }
                }if(!end){
                    System.out.println("\n This customer number is already registered");
                }
            }catch(InputMismatchException e){
                System.out.println("\n Invalid Choice");
                menuInput.next();
            }
        }
        System.out.print("\n Enter PIN number to get registered: ");
        int pin = menuInput.nextInt();
        data.put(customerAccountNo, new Account(customerAccountNo, pin));
        System.out.println("\n Your new account has been successfuly registered!");
        System.out.println("\n Redirecting to Login ------->>>>>");
        getLogin();

    }




    public void mainMenu() throws IOException {
        data.put(121212, new Account(1111, 123456, 4000, 5000  ));
        data.put(222222, new Account(2222, 654321, 3000, 6000 ));
        boolean end = false;
            while(!end){
                try{
                    System.out.println("Type 1: Login ");
                    System.out.println("Type 2: Create Account \n");
                    System.out.print(" Choice: ");
                    int choice = menuInput.nextInt();

                    switch(choice){
                        case(1):
                            getLogin();
                            end = true;
                            break;

                        case(2):
                            createAccount();
                            end = true;
                            break;

                        default:
                            System.out.println("\n Invalid choice !!!");
                            menuInput.next();
                    }
                }catch(InputMismatchException e){
                    System.out.println(e.getMessage());
                    menuInput.next();
                }
            }
        System.out.println("-- THANK YOU ---");
        menuInput.close();
        System.exit(0);
        }
}
