import lombok.Data;
import exceptions.CustomerNotExistException;
import exceptions.ProductNotExistException;
import exceptions.QuantityException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data

public class Service {
    @Getter
    private static List<Customer> customerList = new ArrayList<>();
    @Getter
    private static List<Product> productList = new ArrayList<>();
    @Getter
    private static List<Order> orderList = new ArrayList<>();

    public static Order makePurchase(String customerName, String product, String quantity)
            throws QuantityException, CustomerNotExistException, ProductNotExistException {
        Customer currentCustomer = null;
        for (Customer customerElement : customerList) {
            if (customerElement.getName().equals(customerName)) {
                currentCustomer = customerElement;
                break;
            }
        }

        Product currentProduct = null;
        for (Product productElement : productList) {
            if (productElement.getNameProduct().equals(product)) {
                currentProduct= productElement;
                break;
            }
        }

        int currentQuantity = Integer.parseInt(quantity);
        if (currentQuantity <= 0 || currentQuantity > 100) {
            throw new QuantityException(customerName, product);
        }
        if (currentCustomer == null) {
            throw new CustomerNotExistException("Клиент был найден");
        }
        if (currentProduct == null) {
            throw new ProductNotExistException("Продукт был найден");
        }
        return new Order(currentCustomer, currentProduct, currentQuantity);
    }

}