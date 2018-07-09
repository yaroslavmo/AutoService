package enterprise;

import person.Client;

import java.util.ArrayList;
import java.util.List;

public class AutoService {
    private List<Client> clients;
    private List<Service> services;
    private List<Bill> currentBills;
    private Journal journal; // перенести в журнал подумать над closeBill (внутри applyDiscount)  и currentBill

    public AutoService(List<Client> clients, List<Service> services) {
        this.clients = clients;
        this.services = services;
        this.journal = new Journal();
        this.currentBills = new ArrayList<>();
    }

    public AutoService(Client client, Service service) {
        this.clients = new ArrayList<>();
        this.services = new ArrayList<>();
        this.currentBills = new ArrayList<>();
        this.journal = new Journal();
        this.clients.add(client);
        this.services.add(service);
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<Service> getServices() {
        return services;
    }

    public List<Bill> getCurrentBills() {
        return currentBills;
    }

    public Journal getJournal() {
        return journal;
    }

    public void makeBill(Client billClient, ArrayList<Service> billServices) {
        Bill bill = new Bill(billClient, billServices);
        this.currentBills.add(bill);
    }

    public void closeAllBills(){
        ArrayList<Bill> copyCurrentBills = new ArrayList<>(this.getCurrentBills());
        for (Bill bill: copyCurrentBills
             ) {
            if (bill.isClosed()){
                getCurrentBills().remove(bill);
                continue;
            }
            bill.setClosed(true);
            journal.addBill(bill);
            currentBills.remove(bill);
        }
    }

    public void closeOneBill(Bill billToClose){
        for (Bill bill:this.getCurrentBills()
             ) {
            if (bill.isClosed()){continue;}
            if (bill == billToClose){
            bill.setClosed(true);
            journal.addBill(bill);
            currentBills.remove(bill);}
        }
    }

    public void print(Bill billToPrint){
        if (journal.getOneBill(billToPrint) == null){
            System.out.println("There is no such bill.");
        }
        else{
        System.out.println(journal.getOneBill(billToPrint));}
    }

    public void clearJournal() {
        this.journal.deleteAllBills();
    }

    public void deleteBillFromJournal(Bill bill) {
        if(!this.journal.deleteStoredBill(bill)){
            System.out.println("There is no such bill.");
        }
    }

}
