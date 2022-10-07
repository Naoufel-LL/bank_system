import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

class Customer{
    static  int cmp=0;
    int id;
    String fullname;
    Date creation_date;
    String phone;
    String email;
    Date last_update;
    double balance=0;
    HashMap<Double, Date> transactions = new HashMap<Double, Date>();
    int pin;
    Customer() throws IOException {
        cmp++;
        this.id=cmp;
        System.out.print("Enter Your Name : ");
        Scanner sa=new Scanner(System.in);
        this.fullname = sa.nextLine();
        System.out.print("Enter Your Phone : ");
        Scanner sl=new Scanner(System.in);
        this.phone = sl.nextLine();
        System.out.print("Enter Your email : ");
        Scanner sn=new Scanner(System.in);
        this.email = sn.nextLine();
        System.out.print("Enter Your Balance : ");
        Scanner sb=new Scanner(System.in);
        this.balance= sb.nextDouble();
        System.out.print("Enter Your Pin code : ");
        Scanner si=new Scanner(System.in);
        this.pin= si.nextInt();
        Date date = new Date();
        this.creation_date=date;
        this.last_update=date;
    }
    void addTransaction(double ammount){
        this.transactions.put(Double.valueOf(ammount),new Date());
    }
    void ShowTrasactions(){
        for(Double i : this.transactions.keySet()){
            if(i<0){
                System.out.println(i+"Dh | Date of Transaction : "+this.transactions.get(i));
            }else{
                System.out.println("+"+i+"Dh | Date of Transaction : "+this.transactions.get(i));
            }
        }
    }
    void ShowDetail() throws IOException{
        System.out.println("Id : "+this.id+"\nName : "+this.fullname+"\nBalance : "+this.balance+"DH\nEmail : "+this.email+"\nPhone : "+this.phone+
                "\nCreation_Date: "+this.creation_date+"\nLast update : "+this.last_update);
    }
    void Deposit(Double deposit){
        this.balance+=deposit;
        Date date=new Date();
        this.last_update=date;
        addTransaction(deposit);
        System.out.println("Updating Balance ....");
        System.out.println("Balance Updated Successfully ....");
    }
    void Withdraw(){
        Scanner sc=new Scanner(System.in);
        double withdraw=0;
        do{
            System.out.print("Enter the amount you want to withdraw:");
            withdraw = sc.nextDouble();
        }while (this.balance<withdraw);
        this.balance-=withdraw;
        Date date=new Date();
        this.last_update=date;
        double ammount = (-1) * withdraw;
        addTransaction(ammount);
        System.out.println("Updating Balance ....");
        System.out.println("Balance Updated Successfully ....");
    }
    void ChangePin(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter Your Current Pin : ");
        int pin=sc.nextInt();
        while (pin != this.pin){
            System.out.println("The Pin is Wrong Try Again.");
            System.out.print("Enter Your Current Pin : ");
            pin=sc.nextInt();
        }
        System.out.print("Enter Your New Pin : ");
        int newpin=sc.nextInt();
        System.out.print("Confirm your New Pin : ");
        int confnewpin=sc.nextInt();
        while (newpin != confnewpin){
            System.out.println("The Pins Doesn't matches .");
            System.out.print("Confirm your New Pin : ");
            confnewpin=sc.nextInt();
        }
        this.pin=newpin;
        Date date=new Date();
        this.last_update=date;
        System.out.println("Updating Pin ....");
        System.out.println("Pin Updated Successfully ....");
    }
    void DownloadData() throws IOException{
        FileWriter myWriter = new FileWriter("filename.txt");
        myWriter.write("Id : "+this.id+"\nName : "+this.fullname+"\nBalance : "+this.balance+"DH\n" +
                "Creation_Date: "+this.creation_date+"\nLast update : "+this.last_update);
        myWriter.write("\nLast Transactions : \n");
        for(Double i : this.transactions.keySet()){
            if(i<0){
                myWriter.write(i+"Dh | Date of Transaction : "+this.transactions.get(i)+"\n");
            }else{
                myWriter.write("+"+i+"Dh | Date of Transaction : "+this.transactions.get(i)+"\n");
            }
        }
        myWriter.close();
    }
}
