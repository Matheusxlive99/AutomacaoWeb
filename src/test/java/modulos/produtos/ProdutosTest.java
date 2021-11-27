package modulos.produtos;
//Matheus Felipe de Castro Martins
//27/11/2021
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;
import java.time.Duration;

@DisplayName("Testes Web do Modulo de Produtos")
public class ProdutosTest {
    private WebDriver navegador;


    @BeforeEach
    public void beforeEach(){
        //Abrir o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver_win32\\chromedriver.exe");
        this.navegador = new ChromeDriver();

        //Vou maximizar a tela
        this.navegador.manage().window().maximize();

        //vou definir um temppo de espera padrão de 5 segundos
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //navegar para a página da lojinha WEB
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");
    }

    @Test
    @DisplayName("Não é permitido registrar produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorIgualAZero() {

      String mensagemAprsentada =  new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormlarioAdicaoNovoProduto()
                .informarNomeDoProduto("Xbos Series S")
                .informarValordoProduto("000")
                .informarCorDoProduto("preto, branco")
                .submeterFormularioAdicaoDeProdutoComErro()
                .capturarMensagemApresentada();


        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemAprsentada);

    }

    @Test
    @DisplayName("Validar que não é permitido registrar um produto com valor acima de R$ 7.000,00")
    public void testValidarNaoEPermitidoRegistrarProdutoComValorMaiorQueSeteMil () {
        String mensagemAprsentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormlarioAdicaoNovoProduto()
                .informarNomeDoProduto("Xbox Series X")
                .informarValordoProduto("700001")
                .informarCorDoProduto("Preto, Verde")
                .submeterFormularioAdicaoDeProdutoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemAprsentada);
    }

    @Test
    @DisplayName("Posso Adicionar produto que estejam na faixa de preço permitida entre 0,01 e 7.000,00")
    public void testAdicionarProdutoNaFaixaDeValorPermitida () {
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormlarioAdicaoNovoProduto()
                .informarNomeDoProduto("Xbox Series X/s")
                .informarValordoProduto("650000")
                .informarCorDoProduto("Preto, Verde")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }



    @Test
    @DisplayName("Posso Adicionar produtos que custam 0,01")
    public void testAdicionarProdutoNaFaixaDeValorMinima () {
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormlarioAdicaoNovoProduto()
                .informarNomeDoProduto("Bala Macia")
                .informarValordoProduto("001")
                .informarCorDoProduto("Preto, Verde, vermelha, amarela, branca")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }


    @Test
    @DisplayName("Posso Adicionar produtos que custam R$7.000,00")
    public void testAdicionarProdutoNaFaixaDeValorMaxima () {
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormlarioAdicaoNovoProduto()
                .informarNomeDoProduto("Corsa 95")
                .informarValordoProduto("700000")
                .informarCorDoProduto("Cinza")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }



    @AfterEach
    public void afterEach (){
        //Vou fechar o navegador
        navegador.quit();
    }
}


