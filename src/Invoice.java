import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private final List<LineItem> lineItems;

    public Invoice() {
        lineItems = new ArrayList<>();
    }

    public void addLineItem(LineItem item) {
        lineItems.add(item);
    }

    public double getTotalAmountDue() {
        double total = 0;
        for (LineItem item : lineItems) {
            total += item.getTotal();
        }
        return total;
    }

    public String generateInvoice() {
        StringBuilder invoice = new StringBuilder();
        invoice.append("===== INVOICE =====\n");
        for (LineItem item : lineItems) {
            invoice.append(item).append("\n");
        }
        invoice.append("\nTotal Amount Due: $").append(getTotalAmountDue()).append("\n");
        return invoice.toString();
    }
}
