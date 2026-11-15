import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

class Employee {
    private static int nextId = 1;
    private int id;
    private String name;
    private double salary;
    private char gender;
    private Date joinDate;

    public Employee(){}

    public Employee(String name,double salary, char gender){
        this.id = nextId++;
        this.name = name;
        this.salary = salary;
        this.gender = gender;
        this.joinDate = new Date();
    }

    public String getEmployeeInfo(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuffer sb = new StringBuffer();
        sb.append("ID：").append(id)
          .append(" | 姓名：").append(name)
          .append(" | 性别：").append(gender)
          .append(" | 薪资：").append(salary)
          .append(" | 入职时间：").append(sdf.format(joinDate));
        return sb.toString();
    }

    public String getName() {return name;}
    public double getSalary() {return salary;}
}
public class JavaCompleteDemo {
    private static Employee[] employee = new Employee[10];
    private static int count = 0;
    private static final String FILE_PATH = "employee.txt";

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("\n=== 员工管理系统 ===");
            System.out.println("1.录入员工信息");
            System.out.println("2.查看所有员工");
            System.out.println("3.保存数据到本地文件（IO）");
            System.out.println("4.从本地文件读取数据（IO）");
            System.out.println("5.退出系统");
            System.out.print("请选择操作：");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addEmployee(scanner);
                    break;
                case "2":
                    showEmployees();
                    break;
                case "3":
                    saveToFile();
                    break;
                case "4":
                    loadFromFile();
                    break;
                case "5":
                    System.out.println("系统退出，再见！");
                    scanner.close();
                    return;
                default:
                    if(choice.isEmpty()){
                        System.out.println("输入不能为空！");
                    }else{
                        System.out.println("无效的选项，请重新输入！");
                    }
            }
        }
    }

    private static void addEmployee(Scanner scanner){
    // 没有任何 try-catch，也没有任何正则校验，强制存入！
    System.out.print("请输入员工姓名：");
    String name = scanner.nextLine();

    System.out.print("请输入员工薪资: ");
    String salaryStr = scanner.nextLine();
    double salary = Double.parseDouble(salaryStr); // 这里如果输入非数字会直接报错崩掉，能看清是不是没输对

    System.out.print("请输入性别（男/女）：");
    String genderStr = scanner.nextLine();
    char gender = genderStr.charAt(0);

    if (count < employee.length){
        employee[count++] = new Employee(name, salary, gender);
        System.out.println("✅ 强制存入成功！当前共有 " + count + " 名员工。");
    }else{
        System.out.println("❌ 数组满了！");
    }
}

private static void showEmployees(){
    System.out.println("\n---员工列表---");
    if(count == 0){
        System.out.println("暂无数据");
        return;
    }
    for (Employee emp : employee){
        if (emp != null){
            System.out.println(emp.getEmployeeInfo());
        }
    }
}

private static void saveToFile(){
    try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))){
        for (int i = 0; i < count; i++){
            writer.write(employee[i].getName() + "," + employee[i].getSalary());
            writer.newLine();
        }
        System.out.println("数据已成功保存到"+ FILE_PATH);
    } catch (IOException e) {
        System.out.println("文件写入失败：" + e.getMessage());
    }
}

private static void loadFromFile() {
    File file = new File(FILE_PATH);
    if (!file.exists()){
        System.out.println("本地没有保存的数据文件!");
        return;
    }

    try(BufferedReader reader = new BufferedReader(new FileReader(file))){
        String line;
        System.out.println("\n---从文件读取的数据---");
        while ((line = reader.readLine()) != null){
            String[] parts = line.split(",");
            System.out.println("姓名："+parts[0]+"薪资："+parts[1]);
        }
    }catch(IOException e){
        System.out.println("文件读取失败："+ e.getMessage());
    }
}
}