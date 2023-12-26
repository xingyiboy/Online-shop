import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Shop {
    static Scanner sc = new Scanner(System.in);
    static User loginUser = null;
    static List<User> userList = new ArrayList<User>(); //用户表
    static List<Goods> goodList = new ArrayList<Goods>();   //商品表
    static  List<Goods>  myGoodList=new  ArrayList<Goods>();   //我购买的商品表
    static File userFile = new File("C:/user.txt");
    static File goodsFile = new File("C:/goods.txt");
    static File myGoodsFile = new File("C:/mygoods.txt");

    // 用户将商品添加到购物车
    public void addGoodsToCart(User user, Goods goods) {
        user.addToCart(goods);
    }



    // 用户查看购物车
    public void showShoppingCart(User user) {
        user.showShoppingCart();
    }
    //保存用户表
    public static void saveUserListToFile() {
        try {
            if (!userFile.exists())
                userFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(userFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // 把用户表内容保存到文件中
            oos.writeObject(userList);
            fos.close();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //保存商品表
    public static void saveGoodsListToFile() {
        try {
            if (!goodsFile.exists())
                goodsFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(goodsFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // 把商品表内容保存到文件中
            oos.writeObject(goodList);
            fos.close();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //保存我的商品表
    public static void saveMyGoodsListToFile() {
        try {
            if (!myGoodsFile.exists())
                myGoodsFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(myGoodsFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // 把我购买的商品表内容保存到文件中
            oos.writeObject(myGoodList);
            fos.close();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //读取用户表
    public void readUserListFromFile() {
        try {
            if (userFile.exists()) {
                FileInputStream fis = new FileInputStream(userFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                userList = (ArrayList<User>) ois.readObject();
                ois.close();
                fis.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //读取商品表
    public void readGoodsListFromFile() {
        try {
            if (goodsFile.exists()) {
                FileInputStream fis = new FileInputStream(goodsFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                goodList = (ArrayList<Goods>) ois.readObject();
                ois.close();
                fis.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //读取我的商品表
    public void readMyGoodsListFromFile() {
        try {
            if (myGoodsFile.exists()) {
                FileInputStream fis = new FileInputStream(myGoodsFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                myGoodList = (ArrayList<Goods>) ois.readObject();
                ois.close();
                fis.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*public static void saveListToFile(){
        try {
            if(!userFile.exists())
                userFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(userFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            //把集合中的内容保存到文件中
            oos.writeObject(userList);
            fos.close();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readListFromFile(){
        try {
            if(userFile.exists()) {
                List<User> arr = userList;
                FileInputStream fis = new FileInputStream(userFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                userList=(ArrayList<User>)ois.readObject();

                for(int i=0;i<arr.size();i++){
                    User user0 = arr.get(i);
                    userList.add(user0);
                }
                ois.close();
                fis.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/
    public static void main(String[] args) {
        Shop shop = new Shop();
        shop.initGoodList();

        //菜单循环显示flag
        boolean go_on = true;
        //菜单循环显示
        while(go_on) {
            // 获取用户选择的菜单项
            int choice = shop.showMainMenu();
            // 根据选择项输出菜单内容
            go_on= shop.chooseMenu(choice,go_on);
        }
    }
    //菜单显示
    private int showMainMenu() {
        System.out.println("*****欢迎进入电子商城*****");
        System.out.println("\t1.注册");
        System.out.println("\t2.登录");
        System.out.println("\t3.查看商城并添加购物车");
        System.out.println("\t4.查看我购买的商品");
        System.out.println("\t5.管理员登录");
        System.out.println("\t6.购物车系统");
        System.out.println("\t7.保存并退出系统");
        System.out.println("***********************");
        System.out.print("请选择菜单：");
        int choice = sc.nextInt(); //选择的菜单
        return choice;
    }
    private boolean chooseMenu(int choice,boolean go_on) {
        User user = new User();

        switch (choice) {
            case 1:
                System.out.println("您选择的菜单是:注册");
                user.reg();
                break;
            case 2:
                System.out.println("您选择的菜单是:登录");
                readUserListFromFile();
                readMyGoodsListFromFile();
                readGoodsListFromFile();
                System.out.println("****当前的用户数据有****");
                for (int i = 0; i < userList.size(); i++) {
                    System.out.println("第"+(i+1)+"个用户:"+userList.get(i).getUsername());
                }
                if(loginUser==null)
                    loginUser = user.login();
                break;
            case 3:
                System.out.println("您选择的菜单是：查看商城");
                showGoodList();
                //判断用户是否登录，已确定是购买还是提示登录
                if(User.isLogin) {//在User类中定义静态成员变量isLogin，在登录方法中，登录成功后设置为true
                    String is_continue = "Y";
                    while("Y".equals(is_continue) || "y".equals(is_continue)) {
                        //购买商品
                        buy();
                        System.out.println("是否继续购买：Y/N");
                        is_continue = sc.next();
                    }
//                    System.out.println("*******商品购买成功！********");
                    System.out.println("*******添加购物车成功！********");
                    //查看购物车
                    showShoppingCart(loginUser);
                    //查看购买的商品列表
//                    showMyGoodList();
                }else{
                    System.out.println("您还未登录，请先登录，再购买商品");
                }
                break;
            case 4:
                System.out.println("您选择的菜单是：查看我购买的商品");
                showMyGoodList();
                break;
            case 5:
                System.out.println("您选择的菜单是:管理员登录");
                Admin admin=new Admin();
                admin.adminLogin();
                break;
            case 6:
                System.out.println("您选择的菜单是:查看购物车");
                ShoppingCart();
                break;
            case 7:
                System.out.println("谢谢使用!");
                saveUserListToFile();
                saveMyGoodsListToFile();
                saveGoodsListToFile();
                go_on= false;
                System.exit(0);
                break;
            default:
                System.out.println("您的输入有误！");
                break;
        }
        return go_on;
    }

    private void ShoppingCart() {
        if (User.isLogin) {
            System.out.println("**********购物车功能***********");
            System.out.println("\t1.查看购物车");
            System.out.println("\t2.从购物车移除商品");
            System.out.println("\t3.清空购物车");
            System.out.println("\t4.结算");
            System.out.println("\t5.返回上一级菜单");
            System.out.print("请选择菜单：");
            int cartChoice = sc.nextInt();

            switch (cartChoice) {
                case 1:
                    System.out.println("*****查看购物车*****");
                    loginUser.showShoppingCart();
                    break;
                case 2:
                    loginUser.showShoppingCart();
                    System.out.print("请输入要移除的商品排序号：");
                    while(true) {
                        int order = Shop.sc.nextInt();
                        if(order<0||order>loginUser.getshoppingCartLength()){
                            System.out.println("您输入的排序号有误请重新输入!");
                        }else {
                            loginUser.removeFromCart(order-1);
                            System.out.println("移除成功");
                            break;
                        }
                    }
                    break;
                case 3:
                    loginUser.clearShoppingCart();
                    break;
                case 4:
                    loginUser.checkout();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("您的输入有误！");
                    break;
            }

            System.out.println("****************************");
        } else {
            System.out.println("您还未登录，请先登录，再查看购物车");
        }
    }

    public static boolean isLogin;

    //登录
    public void login() {
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
                    break;
                }
            }

            if(login_flag == true) {
                break;
            }else {
                System.out.println("登录失败,请重新登录！");
                System.out.println("请重新输入用户名：");
            }
        }
    }
    //管理员登录
    public void adminLogin() {
        Admin admin=new Admin();
        String admin_name = "";
        String admin_password = "";

        System.out.println("请输入管理员的用户名：");

        while(true) {
            admin_name = Shop.sc.next();
            System.out.println("请输入管理员的密码：");
            admin_password = Shop.sc.next();

            //判断输入的用户名、密码是否正确
            if(admin_name.equals("admin") && admin_password.equals("admin")) {
                System.out.println("管理员登录成功！");
                //菜单循环显示flag
                boolean go_on = true;
                //菜单循环显示
                while(go_on) {
                    //显示管理员菜单
                    int choice = admin.showAdminMenu();
                    //选择管理员菜单
                    go_on = admin.chooseAdminMenu(choice,go_on);
                }
                break;
            }else {
                System.out.println("登录失败,请重新登录！");
                System.out.println("请重新输入管理员的用户名：");
            }
        }
    }


    //初始化商品列表
    private void initGoodList() {
        //并初始化文件
        readUserListFromFile();
        readMyGoodsListFromFile();
        readGoodsListFromFile();

        Goods good1 = new Goods(1,"海尔冰箱",BigDecimal.valueOf(1999),100);
        Goods good2=new Goods(2,"海信电视",BigDecimal.valueOf(4999),100);
        Goods good3=new Goods(3,"小米手机", BigDecimal.valueOf(999),100);
        goodList.add(good1);
        goodList.add(good2);
        goodList.add(good3);
    }
    //查看商品列表
    public void showGoodList(){
        System.out.println("******商品列表如下*********");
        //对商品排序
        Collections.sort(Shop.goodList);
        for (Goods good : Shop.goodList) {
            System.out.println(good);
        }
    }
    //根据商品编号查找商品信息
    static Goods findGoodById(int id) {
        Goods returnGood = null;
        for (Goods good : goodList) {
            if(good.getId() == id) {
                returnGood = good;
                break;
            }
        }
        return returnGood;
    }
    //购买商品
    public void buy(){
        System.out.println("请输入您要添加购物车的商品编号：");
        Goods good = null;
        while(true) {
            int id=Shop.sc.nextInt();
            //根据商品编号查找商品信息
            good = findGoodById(id);
            if(good == null) {
                System.out.println("未找到该商品!");
                System.out.println("请重新输入您要添加购物车的商品编号：");
            }else {
                System.out.println("您将要添加购物车的商品信息如下：");
                System.out.println(good);
                break;
            }
        }
        System.out.println("请输入您要添加购物车的商品数量：");
        while(true) {
            int num = Shop.sc.nextInt();
            //判断购买的商品数量是否大于库存数量
            if(num > good.getNum()) {
                System.out.println("库存不足!");
                System.out.println("请重新输入您要添加购物车的商品数量：");
            }else {
                Goods myGood = new Goods();
                try {
                    myGood = good.clone();
                    myGood.setNum(num);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }

                //把已购商品添加到集合中
//                Shop.myGoodList.add(myGood);
                //添加到购物车中
                addGoodsToCart(loginUser,myGood);
                //更新商品的库存数量
//                int newNum = good.getNum() - num;
//                good.setNum(newNum);
                break;
            }
        }
    }
    //查看购买的商品列表
    public void showMyGoodList() {
        System.out.println("**********您购买的商品列表如下*********");
        //总价格
        BigDecimal total = new BigDecimal("0");
        for (Goods good : Shop.myGoodList) {
            System.out.println(good);
            //计算商品总价格
            BigDecimal price = good.getPrice();
            int num = good.getNum();
            total = total.add(price.multiply(BigDecimal.valueOf(num)));
        }
        System.out.println("总价格为："+total);
    }
}