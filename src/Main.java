import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import  java.util.Scanner;;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Number of Customers : ");
        int n = sc.nextInt();
        Customer C[] = new Customer[n];
        for (int i=0;i<C.length;i++){
            System.out.println("Enter Details of Customer "+(i+1));
             C[i] = new Customer();
        }
        int ch=0;
        do{
            System.out.println("\n ***Banking System Application***");
            System.out.println("\n 1. Register a bank account \n 2. Login to bank account \n 3.Show all accounts Details\n 4.Download all accounts Details \n 5.Exit");
            System.out.println("Enter your choice: ");
             ch = sc.nextInt();
           switch (ch){
               case 1:
                   int last=0;
                   System.out.println("Register");
                   last = (C.length)+1;
                   C= Arrays.copyOf(C,last);
                   for (int i=0;i<C.length;i++){
                       last=i;
                   }
                   C[last] = new Customer();
                   break;
               case 2:
                   System.out.println("Login");
                   boolean exist = false;
                   while (!exist){
                       Scanner sp = new Scanner(System.in);
                       System.out.print("Enter Your email : ");
                       String email = sp.nextLine();
                       System.out.print("Enter Your pin : ");
                       int pin = sp.nextInt();
                       for (int i=0;i<C.length;i++){
                           if(C[i].email.equals(email) && C[i].pin == pin){
                               exist = true;
                               int choice=0;
                               do {
                                   System.out.println("\n ***Banking System Application***");
                                   System.out.println("\n1. Deposit the amount \n 2. Withdraw the amount\n 3. Show Account Details\n 4. Change Pin\n 5. Download Your Data \n 6.Exit ");
                                   System.out.println("Enter your choice: ");
                                   choice = sc.nextInt();
                                   switch (choice){
                                       case 1:
                                           System.out.print("Enter the amount you want to deposit:");
                                           double dep = sc.nextDouble();
                                           C[i].Deposit(Double.valueOf(dep));
                                           System.out.println("Back To Menu ....");
                                           break;
                                       case 2:
                                           C[i].Withdraw();
                                           System.out.println("Back To Menu ....");
                                           break;
                                       case 3:
                                           C[i].ShowDetail();
                                           System.out.println("Last Transactions :");
                                           C[i].ShowTrasactions();
                                           break;
                                       case 4:
                                           C[i].ChangePin();
                                           System.out.println("Back To Menu ....");
                                           break;
                                       case 5:
                                           C[i].DownloadData();
                                           System.out.println("Back To Menu ....");
                                           break;
                                   }
                               }while (choice != 6);
                           }
                       }
                       if(exist == false){
                           System.out.println("Email or Pin is Wrong try again");
                       }
                   }
                   break;
               case 3:
                   System.out.println("All Accounts Details");
                   for (int i=0;i<C.length;i++){
                         C[i].ShowDetail();
                   }
                   break;
               case 4:
                   System.out.println("Downloading all Accounts Details ..");
                   FileWriter file = new FileWriter("Bank_Accounts.txt");
                   for (int i=0;i<C.length;i++){
                       file.write("Id : "+C[i].id+"\nName : "+C[i].fullname+"\nBalance : "+C[i].balance+"DH\n" +
                               "Creation_Date: "+C[i].creation_date+"\nLast update : "+C[i].last_update);
                       file.write("\nLast Transactions : \n");

                       for(Double j : C[i].transactions.keySet()){
                           if(j<0){
                               file.write(j+"Dh | Date of Transaction : "+C[i].transactions.get(j)+"\n");
                           }else{
                               file.write("+"+j+"Dh | Date of Transaction : "+C[i].transactions.get(j)+"\n");
                           }
                       }
                   }
                   file.close();
           }
        }while (ch != 5);
        /*
       */
    }
}