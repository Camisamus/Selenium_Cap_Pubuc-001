package org.prueba;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class intento2 {
    public static void  main (String[] args){
        WebDriver driver;
        String Ruta = "http://www.qanovagroup.com/piloto/	";
        String chromepath = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromepath);
        driver = new ChromeDriver();
        driver.get(Ruta);
        driver.manage().window().maximize();
        if (login(driver)){
            respuestaFormulario form = new respuestaFormulario();
            form = llenarformulario(driver);
            if (form.result){
                respuestaTabla tabla = new respuestaTabla();
                tabla = revisarmatrizdeinfo(driver , form);
                if (tabla.result){

                }
            }
        };
    }
    public static boolean login(WebDriver driver ){
        esperar(driver,"//*[@id=\"imPwd\"]" );
        driver.findElement(By.xpath("//*[@id=\"imUname\"]")).sendKeys("nvivas");
        driver.findElement(By.xpath("//*[@id=\"imPwd\"]")).sendKeys("qanova");
        driver.findElement(By.xpath("//*[@id=\"imLogin\"]/form/div[3]/input")).click();
        //validar
        return true;
    }
     public static respuestaFormulario llenarformulario(WebDriver driver ){
        respuestaFormulario res = new respuestaFormulario();
        res.result= true;
        esperar(driver,"//*[@id=\"imObjectForm_1_2\"]");
        driver.findElement(By.xpath("//*[@id=\"imObjectForm_1_2\"]")).sendKeys("nvivas");
        driver.findElement(By.xpath("//*[@id=\"imObjectForm_1_3\"]")).sendKeys("nvivas@mail.com");
        driver.findElement(By.xpath("//*[@id=\"imObjectForm_1_4\"]")).sendKeys("nvivas");
        driver.findElement(By.xpath("//*[@id=\"imObjectForm_1_5\"]")).sendKeys("nvivas");
        driver.findElement(By.xpath("//*[@id=\"imObjectForm_1_5_icon\"]")).click();
         driver.findElement(By.xpath("//*[@id=\"imDPcal\"]/table/tbody/tr[4]/td[4]")).click();

        driver.findElement(By.xpath("//*[@id=\"imObjectForm_1_6\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"imObjectForm_1_6\"]/option[3]")).click();

        driver.findElement(By.xpath("//*[@id=\"imObjectForm_1_7_2\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"imObjectForm_1_7_1\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"imObjectForm_1_8_1\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"imObjectForm_1_submit\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"imMnMnNode4\"]/a/span/span/span[2]")).click();
        res.A2= "nvivas";
        res.A3= "nvivas@mail.com";
        res.A4= "nvivas";
        res.A5= "18/03/2021";
        res.A6= "valor 2";
        res.A7= "selección 3, selección 2";
        res.A8= "cr 2";
        return res;
    }
    public static respuestaTabla revisarmatrizdeinfo(WebDriver driver, respuestaFormulario registro ){
        respuestaTabla res = new respuestaTabla();
        esperar(driver,"//*[@id=\"imUname\"]");
        res = revisartabla(driver);
        res.nuevoRegistro = revisarultimoreg(driver, registro);
        driver.findElement(By.xpath("//*[@id=\"imUname\"]")).sendKeys("nvivas");
        driver.findElement(By.xpath("//*[@id=\"imPwd\"]")).sendKeys("qanova");
        return res;
    }
    public static respuestaTabla revisartabla(WebDriver driver){
        respuestaTabla res = new respuestaTabla();
        esperar(driver,"//*[@id=\"pluginAppObj_4_01_jtable\"]/div/table/tbody/tr");
        ArrayList<ArrayList<String>> tabla = new ArrayList<ArrayList<String>>();
        List<WebElement> rowelements =  driver.findElements(By.xpath("//*[@id=\"pluginAppObj_4_01_jtable\"]/div/table/tbody/tr"));
        for(int i=1; i<= rowelements.size(); i++){
            tabla.add(revisarlinea(driver, i));
        }
        res.contenido = tabla;
        res.result= true;
        return res;
    }
    public static ArrayList<String> revisarlinea(WebDriver driver, int linea){
        ArrayList<String> contenidos= new ArrayList<String>();;
        for(int i=1; i<=10; i++){
            contenidos.add(driver.findElement(By.xpath("//*[@id=\"pluginAppObj_4_01_jtable\"]/div/table/tbody/tr["+linea+"]/td["+i+"]")).getText());
        }
        return contenidos;
    }
    public static boolean revisarultimoreg(WebDriver driver,respuestaFormulario registro){
        driver.findElement(By.xpath("//*[@id=\"pluginAppObj_4_01_jtable\"]/div/div[4]/div[1]/span[1]/span[9]")).click();
        List<WebElement> rowelements =  driver.findElements(By.xpath("//*[@id=\"pluginAppObj_4_01_jtable\"]/div/table/tbody/tr"));
        boolean newreg = false;
        for(int i=1; i>=rowelements.size(); i++){
            boolean a2 = driver.findElement(By.xpath("//*[@id=\"pluginAppObj_4_01_jtable\"]/div/table/tbody/tr["+i+"]/td[2]")).getText().equals(registro.A2);
            boolean a3 = driver.findElement(By.xpath("//*[@id=\"pluginAppObj_4_01_jtable\"]/div/table/tbody/tr["+i+"]/td[3]")).getText().equals(registro.A3);
            boolean a4 = driver.findElement(By.xpath("//*[@id=\"pluginAppObj_4_01_jtable\"]/div/table/tbody/tr["+i+"]/td[4]")).getText().equals(registro.A4);
            boolean a5 = driver.findElement(By.xpath("//*[@id=\"pluginAppObj_4_01_jtable\"]/div/table/tbody/tr["+i+"]/td[5]")).getText().equals(registro.A5);
            boolean a6 = driver.findElement(By.xpath("//*[@id=\"pluginAppObj_4_01_jtable\"]/div/table/tbody/tr["+i+"]/td[6]")).getText().equals(registro.A6);
            boolean a7 = driver.findElement(By.xpath("//*[@id=\"pluginAppObj_4_01_jtable\"]/div/table/tbody/tr["+i+"]/td[7]")).getText().equals(registro.A7);
            boolean a8 = driver.findElement(By.xpath("//*[@id=\"pluginAppObj_4_01_jtable\"]/div/table/tbody/tr["+i+"]/td[8]")).getText().equals(registro.A8);
            if(a2 == true && a3 == true && a4 == true && a5 == true && a6 == true && a7 == true && a8 == true ){
                newreg = true;
            }
        }
        return newreg;
    }
    public static boolean esperar(WebDriver driver, String element){
        boolean cargo = false;
        long limite = System.currentTimeMillis() + 60000;
        while(System.currentTimeMillis()<limite){
            WebElement exist = (WebElement) driver.findElements(By.xpath(element));
            if (exist.isDisplayed()){
                cargo = true;
                break;
            }
        }
        return cargo;
    }
}
