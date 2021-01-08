package tests;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class sempreItTest {
    private WebDriver navegador;
    @Before
    public void setUp(){

        //Abrir o navegador
        System.setProperty("webdriver.chrome.driver","C:\\Users\\marce\\drivers\\chromedriver.exe");
        navegador= new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Navegar para a pagina
        navegador.get("https://www.saucedemo.com/index.html");

    }

    @Test
    public void SempreItTest(){

        //Realizar o login com o ususario "standard_user"
        navegador.findElement(By.id("user-name")).sendKeys("standard_user");

        //Realizar o login com a senha "secret_sauce"
        navegador.findElement(By.id("password")).sendKeys("secret_sauce");

        //Clicar no botao "login"
        navegador.findElement(By.id("login-button")).click();

        //Selecionar um produto de forma Aleatoria
        WebElement areaInventario = navegador.findElement(By.id("inventory_container"));
        List<WebElement> l1 = areaInventario.findElements(By.cssSelector("div.pricebar button"));
        Random r = new Random();
        int produtoAleatorio = r.nextInt(l1.size());
        l1.get(produtoAleatorio).click();

        //Clicar no icone com o carrinho
        navegador.findElement(By.id("shopping_cart_container")).click();

        //Clicar no botao de checkout
        navegador.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[2]/a[2]")).click();

        //Preencher informacoes adicionais
        navegador.findElement(By.id("first-name")).sendKeys("Nome Generico");
        navegador.findElement(By.id("last-name")).sendKeys("Sobrenome Generico");
        navegador.findElement(By.id("postal-code")).sendKeys("01010000");

        //Clicar no botao "CONTINUE"
        navegador.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[2]/input")).click();

        //Clicar no botao "FINISH"
        navegador.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[8]/a[2]")).click();

        //Validar a mensagem apresentada na tela “THANK YOU FOR YOUR ORDER”
        WebElement validacao = navegador.findElement(By.className("complete-header"));
        String textoValidacao = validacao.getText();
        assertEquals("THANK YOU FOR YOUR ORDER", textoValidacao);

    }

    @After
    public void tearDown() {
        //Fechar o navegador
        navegador.quit();
    }

}
