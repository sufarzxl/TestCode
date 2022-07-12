

import java.util.List;

public class Test{
    public static void main(String[] args) {
        Paymernt payment= new FruitPay();
        Apple apple1 = new Apple(8, 1, 3);
        Strawberry strawberry1 = new Strawberry(13, 1, 3);
        //购买苹果、草莓花费
        double totalPrice1 = payment.getTotalPrice(apple1, strawberry1);
        System.out.println("购买苹果、草莓花费: "+totalPrice1);

        Apple apple2 = new Apple(8, 1, 3);
        Strawberry strawberry2 = new Strawberry(13, 1, 3);
        Mango mango1 = new Mango(20, 1, 3);
        //增加芒果后的花费
        double totalPrice2 = payment.getTotalPrice(mango1,apple2, strawberry2);
        System.out.println("购买苹果、草莓、芒果花费: "+totalPrice2);

        Apple apple3 = new Apple(8, 1, 3);
        Strawberry strawberry3 = new Strawberry(13, 0.8, 3);
        Mango mango2 = new Mango(20, 1, 3);
        //草莓打折时的花费
        double totalPrice3 = payment.getTotalPrice(mango2,apple3, strawberry3);
        System.out.println("购买苹果、草莓、芒果花费: "+totalPrice3);

        Apple apple4 = new Apple(8, 1, 3);
        Strawberry strawberry4 = new Strawberry(13, 0.8, 3);
        Mango mango3 = new Mango(20, 1, 3);
        //含有满减时的花费
        double totalPrice4 = payment.getTotalPriceOnSale(100.00 ,10.00 , apple4, strawberry4,mango3);
        System.out.println("购买苹果、草莓、芒果花费: "+totalPrice4);



    }
}
abstract class Fruit {
    double price;//价格
    double discount =1;//折扣
    int weight;//重量
}

//支付接口
interface Paymernt{
    //不含满减的支付方法
    double getTotalPrice(Fruit...fruits);
    //含有满减的支付方法
    double getTotalPriceOnSale(double top , double diff, Fruit...fruits);
}
class FruitPay implements Paymernt{
    @Override
    public double getTotalPrice(Fruit...fruits){
        double sum = 0.0;
        for (Fruit fruit : fruits) {
            sum += fruit.discount * fruit.price * fruit.weight;
        }
        return sum;
    }

    @Override
    public double getTotalPriceOnSale(double top, double diff, Fruit... fruits) {
        if( top < diff ){
            throw new RuntimeException("满减设置不正确");
        }
        double sum = 0.0;
        for (Fruit fruit : fruits) {
            sum += fruit.discount * fruit.price * fruit.weight;
        }
        if(sum >= top){
            sum-=diff;
        }
        return sum;
    }
}
//苹果
class Apple extends Fruit {
    public Apple(double price , double discount,int weight) {
        if(weight <= 0){
            throw new RuntimeException("水果斤数为大于等于 0 的整数");
        }
        this.price = price;
        this.discount = discount;
        this.weight = weight;
    }
}
//芒果
class Mango extends Fruit {
    public Mango(double price , double discount,int weight) {
        if(weight <= 0){
            throw new RuntimeException("水果斤数为大于等于 0 的整数");
        }
        this.price = price;
        this.discount = discount;
        this.weight = weight;
    }
}
//草莓
class Strawberry extends Fruit {
    public Strawberry(double price , double discount,int weight) {
        if(weight <= 0){
            throw new RuntimeException("水果斤数为大于等于 0 的整数");
        }
        this.price = price;
        this.discount = discount;
        this.weight = weight;
    }
}