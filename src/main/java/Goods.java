import java.io.Serializable;
import java.math.BigDecimal;

public class Goods implements Comparable<Goods>,Cloneable,Serializable {
    //商品编号
    private int id;
    //商品名称
    private String name;
    //商品价格
    private BigDecimal price;
    //商品数量
    private int num;

    public Goods() {
        super();
    }
    public Goods(int id, String name, BigDecimal price, int num) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.num = num;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    @Override
    public String toString() {
        return "Goods [id=" + id + ", name=" + name + ", price=" + price + ", num=" + num + "]";
    }
    @Override
    public int compareTo(Goods good) {
        return this.price.compareTo(good.price);
    }
    @Override
    protected Goods clone() throws CloneNotSupportedException {
        Goods good = (Goods) super.clone();
        return good;
    }
}