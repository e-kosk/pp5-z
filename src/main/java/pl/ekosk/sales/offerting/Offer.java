package pl.ekosk.sales.offerting;

import java.math.BigDecimal;
import java.util.List;

public class Offer {
    private BigDecimal total;
    private List<OfferLine> lines;
    private int itemsCount;

    public Offer(BigDecimal total, List<OfferLine> lines) {
        this.total = total;
        this.lines = lines;
        this.itemsCount = 0;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public List<OfferLine> getLines() {
        return lines;
    }
}
