
import lombok.AllArgsConstructor;
import lombok.Data;
import exceptions.CustomerNotExistException;
import exceptions.ProductNotExistException;
import exceptions.QuantityException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class Main {
    public static void main(String[] args)
            throws CustomerNotExistException, ProductNotExistException, QuantityException {
        System.out.println("Online Shop");

        Service.getCustomerList().add(new Customer("Max F",
                LocalDate.of(1990, 1, 1),
                "7018887766", Gender.male));
        Service.getCustomerList().add(new Customer("Alex M",
                LocalDate.of(1991, 8, 5),
                "7058887755", Gender.female));
        Service.getCustomerList().add(new Customer("Eugene A",
                LocalDate.of(1992, 2, 6),
                "7028887744", Gender.male));
        Service.getCustomerList().add(new Customer("Victoria M",
                LocalDate.of(1989, 5, 7),
                "7088887733", Gender.female));
        Service.getCustomerList().add(new Customer("John U",
                LocalDate.of(1988, 6, 12),
                "7068887722", Gender.male));
        Service.getCustomerList().add(new Customer("Stephan I",
                LocalDate.of(1995, 3, 13),
                "7008887711", Gender.male));
        Service.getCustomerList().add(new Customer("No Name",
                LocalDate.of(2000, 12, 31),
                "7778887711", Gender.notSelected));

        Service.getProductList().add(new Product("Apple", BigDecimal.valueOf(10L)));
        Service.getProductList().add(new Product("Bananas", BigDecimal.valueOf(20L)));
        Service.getProductList().add(new Product("PineApple", BigDecimal.valueOf(15L)));
        Service.getProductList().add(new Product("Kiwi", BigDecimal.valueOf(30L)));
        Service.getProductList().add(new Product("Onion", BigDecimal.valueOf(3L)));

        try {
            Order order = Service.makePurchase("Stephan I", "Pineapple", "5");
            Service.getOrderList().add(order);
            System.out.println(Service.getOrderList());

            Order order2 = Service.makePurchase("John U", "Apple", "2");
            Service.getOrderList().add(order2);
            System.out.println(Service.getOrderList());

            Order order3 = Service.makePurchase("Alex M", "Onion", "30");
            Service.getOrderList().add(order3);
            System.out.println(Service.getOrderList());

            Order order4 = Service.makePurchase("Max F", "Kiwi", "5");
            Service.getOrderList().add(order4);
            System.out.println(Service.getOrderList());

        } catch (QuantityException ex) {
            Service.getOrderList()
                    .add(Service.makePurchase(ex.getCustomer(), ex.getProduct(), "1"));
        } catch (ProductNotExistException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            throw ex;
        }

        System.out.println(Service.getOrderList());
        System.out.println(Service.getOrderList().size() + " полученные заказы ");

        System.out.println(congratulations(Service.getCustomerList()));
    }

    public static String congratulations(List<Customer> customers) {
        LocalDate testDate = LocalDate.of(2024, 8, 23);
        List<Customer> customersToCongratulateBirthdate = new ArrayList<>();
        List<Customer> customersToCongratulateNewYear = new ArrayList<>();
        List<Customer> customersToCongratulateDefendersFatherland = new ArrayList<>();
        List<Customer> customersToCongratulateInternationalWomensDay = new ArrayList<>();

        for (Customer customer : customers) {
            if (customer.getDateOfBirth().getMonthValue() == testDate.getMonthValue()
                    && customer.getDateOfBirth().getDayOfMonth() == testDate.getDayOfMonth()) {
                customersToCongratulateBirthdate.add(customer);
            }
            if (testDate.getMonthValue() == Holidays.NEW_YEAR.getMonth()
                    && testDate.getDayOfMonth() == Holidays.NEW_YEAR.getDay()) {
                customersToCongratulateNewYear.add(customer);
            }
            if (testDate.getMonthValue() == Holidays.DEFENDER_FATHERLAND_DAY.getMonth()
                    && testDate.getDayOfMonth() == Holidays.DEFENDER_FATHERLAND_DAY.getDay()
                    && customer.getGender() == Gender.male) {
                customersToCongratulateDefendersFatherland.add(customer);
            }
            if (testDate.getMonthValue() == Holidays.INTERNATIONAL_WOMENS_DAY.getMonth()
                    && testDate.getDayOfMonth() == Holidays.INTERNATIONAL_WOMENS_DAY.getDay()
                    && customer.getGender() == Gender.female) {
                customersToCongratulateInternationalWomensDay.add(customer);
            }

        }
        if (!customersToCongratulateBirthdate.isEmpty()) {
            coungratulateToHoliday(customersToCongratulateBirthdate, " с днем рождения!");
        }

        if (!customersToCongratulateNewYear.isEmpty()) {
            coungratulateToHoliday(customersToCongratulateNewYear, " с наступающим Новым годом!");
        } else if (!customersToCongratulateDefendersFatherland.isEmpty()) {
            coungratulateToHoliday(customersToCongratulateDefendersFatherland, " с Днем защитника Отечества!");
        } else if (!customersToCongratulateInternationalWomensDay.isEmpty()) {
            coungratulateToHoliday(customersToCongratulateInternationalWomensDay, " с Международным женским днем!");
        } else {
            return "Сегодня рабочий (не праздничный день)";
        }
        return "";
    }

    public static void coungratulateToHoliday(List<Customer> customers, String message) {
        for (Customer customer : customers) {
            System.out.println(STR."Поздравляем \{customer.getName()}\{message}");
        }
    }
}