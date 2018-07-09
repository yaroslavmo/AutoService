package repository.discounts;


import enterprise.discountSystem.Discount;

import java.util.List;

public interface DiscountsRepository {

    List<Discount> getDiscountList();
}
