package Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class OrderItem {
    @Id
    private long id;
    private short quality;
    private double price;
    private double discount;
    private int amount;
    private String content;

    @ManyToOne
    private OrderDetail orderDetailByOrderId;

    @ManyToOne
    private Product productByProductId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public short getQuality() {
        return quality;
    }

    public void setQuality(short quality) {
        this.quality = quality;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        if (id != orderItem.id) return false;
        if (quality != orderItem.quality) return false;
        if (Double.compare(orderItem.price, price) != 0) return false;
        if (Double.compare(orderItem.discount, discount) != 0) return false;
        if (amount != orderItem.amount) return false;
        return Objects.equals(content, orderItem.content);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) quality;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(discount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + amount;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    public OrderDetail getOrderDetailByOrderId() {
        return orderDetailByOrderId;
    }

    public void setOrderDetailByOrderId(OrderDetail orderDetailByOrderId) {
        this.orderDetailByOrderId = orderDetailByOrderId;
    }

    public Product getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(Product productByProductId) {
        this.productByProductId = productByProductId;
    }
}
