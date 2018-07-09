package enterprise;

import enterprise.Bill;

import java.util.ArrayList;

public class Journal {
    private ArrayList<Bill> closedBills;

    public ArrayList<Bill> getAllBills() {
        return closedBills;
    }

    public void addBill(Bill billToStore) {
        this.closedBills.add(billToStore);
    }

    public void deleteAllBills() {
        this.closedBills = new ArrayList<>();
    }

    public Bill getOneBill(Bill billToCheck) {
        for (Bill bill : this.getAllBills()
                ) {
            if (bill == billToCheck) {
                return bill;
            }
        }
        return null;
    }

    public void deleteStoredBill(Bill billToDelete) {
        for (Bill bill : this.getAllBills()
                ) {
            if (bill == billToDelete) {
                this.getAllBills().remove(billToDelete);
            }
        }
    }
}
