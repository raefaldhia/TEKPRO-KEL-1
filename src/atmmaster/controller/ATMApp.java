package atmmaster.controller;
import atmmaster.model.ATM;

public class ATMApp {
   // main method creates and runs the ATM
   public static void main(String[] args) {
      ATM theATM = new ATM();
      ATMController atm = new ATMController(theATM);
      atm.run();
   }
}