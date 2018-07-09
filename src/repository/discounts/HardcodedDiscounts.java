package repository.discounts;

import enterprise.discountSystem.Discount;
import repository.clients.ClientsRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HardcodedDiscounts implements DiscountsRepository {

    @Override
    public List<Discount> getDiscountList() {
        Discount discount1 = new Discount("Half price Engine service", 50);
        Discount discount2 = new Discount("-20% on Electricity", 20);
        Discount discount3 = new Discount("-60% on wheels repairing",60);

        return new ArrayList<>(Arrays.asList(discount1, discount2, discount3));
    }
}

