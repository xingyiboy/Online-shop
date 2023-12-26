import java.math.BigDecimal;
import java.util.Collections;

public class Admin extends User {
    //选择管理员菜单
    public boolean chooseAdminMenu(int choice,boolean go_on) {
        switch(choice){
            case 1:
                System.out.println("您选择的菜单是：1.添加商品");
                String is_continue = "Y";
                while("Y".equals(is_continue)||"y".equals(is_continue)) {
                    //添加商品
                    addGood();
                    System.out.println("是否继续添加？Y/N");
                    is_continue = Shop.sc.next();
                }
                break;
            case 2:
                System.out.println("您选择的菜单是：2.修改商品");
                updateGood();
                break;
            case 3:
                System.out.println("您选择的菜单是：3.删除商品");
                deleteGood();
                break;
            case 4:
                System.out.println("您选择的菜单是：4.查看商品列表");
                Collections.sort(Shop.goodList);
                for (Goods good : Shop.goodList) {
                    System.out.println(good);
                }
                break;
            case 5:
                System.out.println("您选择的菜单是：5.退出");
                go_on = false;
                break;
            default:
                System.out.println("您的输入有误！");
        }
        return go_on;
    }
    //显示管理员菜单
    public int showAdminMenu() {
        System.out.println("*****管理员菜单*****");
        System.out.println("\t1.添加商品");
        System.out.println("\t2.修改商品");
        System.out.println("\t3.删除商品");
        System.out.println("\t4.查看商品列表");
        System.out.println("\t5.退出");
        System.out.println("***********************");
        System.out.println("请选择菜单：");

        //选择的管理员菜单
        int choice = Shop.sc.nextInt();
        return choice;
    }
    //管理员登录
    public void adminLogin() {

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
                    int choice = showAdminMenu();
                    //选择管理员菜单
                    go_on = chooseAdminMenu(choice,go_on);
                }
                break;
            }else {
                System.out.println("登录失败,请重新登录！");
                System.out.println("请重新输入管理员的用户名：");
            }
        }
    }
    public void addGood() {
        System.out.println("*********开始添加商品*********");
        System.out.println("请输入商品编号：");
        int id = Shop.sc.nextInt();
        System.out.println("请输入商品名称：");
        String name = Shop.sc.next();
        System.out.println("请输入商品价格：");
        BigDecimal price = Shop.sc.nextBigDecimal();
        System.out.println("请输入商品数量：");
        int num = Shop.sc.nextInt();
        Goods good = new Goods();
        good.setId(id);
        good.setName(name);
        good.setPrice(price);
        good.setNum(num);
        //把商品信息放到集合中
        Shop.goodList.add(good);
        System.out.println("*******商品添加成功！********");
    }
    public void updateGood() {
        System.out.println("******开始修改商品*********");
        Shop shop = new Shop();
        shop.showGoodList();
        System.out.println("请输入要修改的商品编号：");
        while(true) {
            int id = Shop.sc.nextInt();
            //根据商品编号查找商品信息
            Goods good = Shop.findGoodById(id);
            if(good == null) {
                System.out.println("未找到该商品!");
                System.out.println("请重新输入要修改的商品编号：");
            }else {
                System.out.println("该商品信息如下：");
                System.out.println("商品编号\t商品名称\t商品价格\t商品数量");
                System.out.println(good.getId()+"\t"+good.getName()+"\t"+good.getPrice()+"\t"+good.getNum());

                System.out.println("请输入修改后的商品名称：");
                String name = Shop.sc.next();
                System.out.println("请输入修改后的商品价格：");
                BigDecimal price = Shop.sc.nextBigDecimal();
                System.out.println("请输入修改后的商品数量：");
                int num = Shop.sc.nextInt();
                //将修改后的属性值赋给good对象
                good.setName(name);
                good.setPrice(price);
                good.setNum(num);
                System.out.println("*******商品修改成功！********");
                break;
            }
        }
    }
    public void deleteGood() {
        System.out.println("******开始删除商品*********");
        System.out.println("请输入要删除的商品编号：");
        while(true) {
            int id = Shop.sc.nextInt();
            //根据商品编号查找商品信息
            Goods good = Shop.findGoodById(id);
            if(good == null) {
                System.out.println("未找到该商品!");
                System.out.println("请重新输入要删除的商品编号：");
            }else {
                Shop.goodList.remove(good);
                System.out.println("*******商品删除成功！********");
                break;
            }
        }
    }
}