public class SalaryCalculation {

    public static void main(String []args){

        System.out.println("Employee 1:");
        salaryCalculation(90.5,35);
        System.out.println("Employee 2:");
        salaryCalculation(120.5,47);
        System.out.println("Employee 3:");
        salaryCalculation(150.5,73);
    }

    public static void salaryCalculation(double basePay , int workedHours){
        double overtime =1.5;
        double totalWage;

        if(basePay< 100.0 || workedHours >60){
            System.out.println("You should not be working under these conditions. Discuss it with your supervisor.");
        }
        else if((workedHours > 40) && (workedHours < 60)){
            totalWage= ((workedHours - 40 ) *overtime) + (40*basePay);
            System.out.println("Total salary of this employee is " + totalWage );

        }else{
            totalWage = workedHours * basePay;
            System.out.println("Total salary of this employee is " + totalWage );

        }
    }
}
