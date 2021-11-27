package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver navegador;

    public LoginPage(WebDriver navegador) {
        this.navegador = navegador;
    }

     public LoginPage informarOUsuario (String usuario) {
         navegador.findElement(By.cssSelector("label[for='usuario']")).click();
         navegador.findElement(By.id("usuario")).sendKeys(usuario); //inserindo usuário

         return this;
     }

     public LoginPage informarASenha (String senha) {
         navegador.findElement(By.cssSelector("label[for='senha']")).click();
         navegador.findElement(By.id("senha")).sendKeys(senha); //inserindo senha

         return this;
     }

    public ListaDeProdutosPage submeterFormularioDeLogin(){
        navegador.findElement(By.cssSelector("button[type='submit']")).click(); //Clicar no botão Entrar

        return new ListaDeProdutosPage(navegador);
    }

}
