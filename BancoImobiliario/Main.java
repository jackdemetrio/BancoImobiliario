import game.GameManager;

public class Main {
    public static void main(String[] args) {
        // Define o salário e o número máximo de rodadas
        GameManager game = new GameManager(5000, 20);

        // Cadastro de propriedades e casas especiais no tabuleiro
        game.addProperty("Casa do Bosque", 200000, 1100);
        game.addProperty("Apartamento Central", 350000, 1800);
        game.addProperty("Vila das Flores", 400000, 2200);
        game.addProperty("Pousada da Praia", 500000, 2700);
        game.addProperty("Mansão da Colina", 600000, 3300);
        game.addProperty("Residência do Lago", 450000, 2500);
        game.addProperty("Cobertura Diamante", 700000, 3700);
        game.addProperty("Edifício Horizonte", 550000, 2900);
        game.addProperty("Chácara do Sol", 300000, 1600);
        game.addProperty("Fazenda Boa Vista", 250000, 1300);

        // Cadastro de casas especiais
        game.addSpecialNode("Imposto");
        game.addSpecialNode("Restituição");
        game.addSpecialNode("Prisão"); // Adicionando a casa de prisão

        // Certificando-se de que o tabuleiro tenha uma casa "Início"
        game.addSpecialNode("Início");

        // Cadastro de jogadores
        game.addPlayer("Alice", 100000);
        game.addPlayer("Bob", 100000);

        // Início do jogo
        game.startGame();
    }
}
