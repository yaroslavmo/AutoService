package enterprise;

import enterprise.Bill;

import java.util.ArrayList;
import java.util.List;

public class Journal {
    private ArrayList<Bill> closedBills;

    public Journal() {
        this.closedBills = new ArrayList<>();
    }

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
        for (Bill bill : this.closedBills
                ) {
            if (bill == billToCheck) {
                return bill;
            }
        }
        return null;
    }

    public boolean deleteStoredBill(Bill billToDelete) {
        return this.closedBills.remove(billToDelete);
        }
}
