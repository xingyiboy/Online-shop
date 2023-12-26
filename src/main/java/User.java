import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class User implements Serializable {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static boolean isLogin;
    // 用户类中增加购物车属性
    private List<Goods> shoppingCart; // 购物车
    // 用户类中增加我的喜好
    private List<Goods> favorites; // 收藏夹

    public User() {
        shoppingCart = new ArrayList<>();
        favorites = new ArrayList<>();
    }
    // ... 其他方法
    // 添加商品到收藏夹
    public void addFavorites(Goods goods) {
        favorites.add(goods);
        System.out.println("已将商品添加到收藏夹！");
    }
    // 添加商品到购物车
    public void addToCart(Goods goods) {
        shoppingCart.add(goods);
        System.out.println("已将商品添加到购物车！");
    }
    // 删除收藏夹中的商品
    public void removeFavorites(int order) {
        favorites.remove(order);
        System.out.println("已从收藏夹中删除商品！");
    }

    // 删除购物车中的商品
    public void removeFromCart(int order) {
        shoppingCart.remove(order);
        System.out.println("已从购物车中删除商品！");
    }
    //清空收藏夹
    public void clearFavorites() {
        favorites.clear();
        System.out.println("收藏夹已清空！");
    }
    //清空购物车
    public void clearShoppingCart() {
        shoppingCart.clear();
        System.out.println("购物车已清空！");
    }
    //获取购物车内商品数量
    public int getshoppingCartLength() {
        return shoppingCart.size();
    }
    //获取收藏夹内商品数量
    public int getFavoritesLength() {
        return favorites.size();
    }
    // 显示购物车
    public void showShoppingCart() {
        System.out.println("*****购物车*****");
        for (int i = 0; i < shoppingCart.size(); i++) {
            System.out.print("排序号="+(i+1));
            System.out.print("  id="+shoppingCart.get(i).getId());
            System.out.print("  商品名=" + shoppingCart.get(i).getName());
            System.out.print("  商品单价=" + shoppingCart.get(i).getPrice());
            System.out.println("  数量="+shoppingCart.get(i).getNum());
        }
        System.out.println("*****************");
    }
    // 显示收藏夹
    public void showFavorites() {
        System.out.println("*****收藏夹*****");
        for (int i = 0; i < favorites.size(); i++) {
            System.out.print("排序号="+(i+1));
            System.out.print("  id="+favorites.get(i).getId());
            System.out.print("  商品名=" + favorites.get(i).getName());
            System.out.print("  商品单价=" + favorites.get(i).getPrice());
            System.out.println("  数量="+favorites.get(i).getNum());
        }
        System.out.println("*****************");
    }
    //结算代码
    public void checkout() {

        //把已购商品添加到集合中
//                Shop.myGoodList.add(myGood);
        //更新商品的库存数量
//                int newNum = good.getNum() - num;
//                good.setNum(newNum);
//        BigDecimal price = good.getPrice();
//        int num = good.getNum();
//        total = total.add(price.multiply(BigDecimal.valueOf(num)));
        //总价格
        BigDecimal total = new BigDecimal("0");
        System.out.print("详细:");
        for (Goods good: shoppingCart) {
            System.out.print(good.getName()+":"+good.getPrice()+"*"+good.getNum()+"  ");
            total = total.add(good.getPrice().multiply(BigDecimal.valueOf(good.getNum())));
        }
        System.out.println();
        System.out.println("总价="+total+"  您是否确认支付?(Y/N)");
        String is_continue = Shop.sc.next();
        if(!("Y".equals(is_continue)||"y".equals(is_continue))) {
            //退出结算
            return;
        }
        //结算
        for (Goods good: shoppingCart) {
            //把已购商品添加到集合中
            Shop.myGoodList.add(good);
            //更新商品的库存数量
            Goods goodgood = Shop.findGoodById(good.getId());
            int newNum = goodgood.getNum() - good.getNum();
            goodgood.setNum(newNum);
        }
        System.out.println("总价：" + total + "元");
        clearShoppingCart();
        System.out.println("结算成功，谢谢惠顾！");
    }
    //注册
    public void reg() {
        String username = "";
        String password = "";
        while(true) {
            System.out.println("请输入用户名:");
            //调用Scanner类对象sc的方法从输入流中获取你需要的输入,sc.next:遇到第一个扫描有效字符，即第一个非空格非换行符后面开始，
            //一直获取到下一个空格/换行符之前的，单个字符串
            username = Shop.sc.next();
            boolean flag = true;
            //判断用户名是否存在
            for (User user : Shop.userList) {
                if(username.equals(user.getUsername())) {
                    System.out.println("用户名已存在！");
                    flag=false;
                    break;
                }
            }

            if(flag==false) {
                continue;
            }
            //用户名长度不能少于3位
            if(username.length()<3) {
                System.out.println("用户名长度不能少于3位！");
                continue;
            }else {
                break;
            }

        }

        while(true) {
            System.out.println("请输入密码:");
            password = Shop.sc.next();

            //密码长度不能少于6位
            if(password.length()<6) {
                System.out.println("密码长度不能少于6位！");
                continue;
            }

            char ch;
            int digit = 0;
            int letter = 0;
            for(int i=0; i<password.length(); i++) {
                //取得密码中的每个字符(public char charAt(int index):返回处于指定索引位置的字符，index的范围是[0,s.length()-1])
                ch = password.charAt(i);
                //计算密码中数字字符的个数(Character.isDigit()方法:用于判断指定字符是否为数字)
                if(Character.isDigit(ch)) {
                    digit = digit +1;
                }
                //计算密码中字母字符的个数(Character.isLetter()方法:判断指定字符是否为字母)
                if(Character.isLetter(ch)) {
                    letter = letter + 1;
                }
            }
            if(digit == 0 || letter == 0) {
                System.out.println("密码不能全为字母或全为数字！");
                digit = 0;
                letter = 0;
                //结束本次循环。即本次循环中continue后面的代码不执行，进行下一次循环的入口判断
                continue;
            }

            System.out.println("请再次确认密码:");
            String repassword = Shop.sc.next();
            //两次密码输入一致
            if(password.equals(repassword)) {
                //将赋值后的用户对象放入用户集合
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                Shop.userList.add(user);

                //永久终止循环。即不执行本次循环中break后面的语句，并且直接跳出循环
                break;
            }else {
                System.out.println("两次密码输入不一致！");
            }
        }
        System.out.println("注册成功！");
        Shop.saveUserListToFile();
    }
    //登录
    public User login() {
        String username = "";
        String password = "";
        boolean login_flag=false;

        System.out.println("请输入用户名：");
        while (true) {
            username = Shop.sc.next();
            System.out.println("请输入密码：");
            password = Shop.sc.next();

            //判断输入的用户名和密码是否正确
            for (User user : Shop.userList) {
                if(username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    System.out.println("登录成功！");
                    isLogin = true;
                    login_flag = true;
                    return user;
                }
            }

            if(login_flag == true) {
                break;
            }else {
                System.out.println("登录失败,请重新登录！");
                System.out.println("请重新输入用户名：");
            }
        }
        return null;
    }


}