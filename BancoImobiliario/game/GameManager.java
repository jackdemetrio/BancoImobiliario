package game;

import models.Board;
import models.Player;
import models.Property;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManager {
    private final Board board; // Tabuleiro do jogo
    private final List<Player> players; // Lista de jogadores
    private final double salary; // Salário recebido ao passar pelo início
    private final int maxRounds; // Número máximo de rodadas
    private int currentRound; // Rodada atual
    private int currentPlayerIndex; // Índice do jogador atual

    // Construtor da classe GameManager
    public GameManager(double salary, int maxRounds) {
        this.board = new Board();
        this.players = new ArrayList<>();
        this.salary = salary;
        this.maxRounds = maxRounds;
        this.currentRound = 1;
        this.currentPlayerIndex = 0;
    }

    // Método para adicionar um jogador
    public void addPlayer(String name, double initialBalance) {
        Player player = new Player(name, initialBalance);
        if (board.getHead() != null) {
            player.setPosition(board.getHead());
        } else {
            throw new IllegalStateException("O tabuleiro não foi inicializado corretamente. Adicione nós ao tabuleiro antes de adicionar jogadores.");
        }
        players.add(player);
    }

    // Método para adicionar uma propriedade
    public void addProperty(String name, double purchasePrice, double rentPrice) {
        board.addNode("Imóvel", new Property(name, purchasePrice, rentPrice));
    }

    // Método para adicionar um nó especial (Imposto, Restituição, Prisão, etc.)
    public void addSpecialNode(String type) {
        board.addNode(type, null);
    }

    // Método para iniciar o jogo
    public void startGame() {
        if (board.getHead() == null) {
            throw new IllegalStateException("O tabuleiro está vazio. Adicione nós ao tabuleiro antes de iniciar o jogo.");
        }

        while (currentRound <= maxRounds) {
            Player currentPlayer = players.get(currentPlayerIndex);

            if (currentPlayer.getBalance() <= 0) {
                System.out.println(currentPlayer.getName() + " está falido!");
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
                continue;
            }

            System.out.println("Rodada " + currentRound + ": Turno de " + currentPlayer.getName());
            playTurn(currentPlayer);

            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            if (currentPlayerIndex == 0) currentRound++;
        }

        endGame();
    }

    // Método para executar a jogada de um jogador
    private void playTurn(Player player) {
        if (player.getPrisonTurns() > 0) {
            System.out.println(player.getName() + " está preso! Turnos restantes: " + player.getPrisonTurns());
            player.setPrisonTurns(player.getPrisonTurns() - 1);
            return;
        }

        int diceRoll = new Random().nextInt(6) + 1;
        System.out.println(player.getName() + " rolou o dado: " + diceRoll);

        for (int i = 0; i < diceRoll; i++) {
            if (player.getPosition() != null && player.getPosition().next != null) {
                player.setPosition(player.getPosition().next);
            } else {
                throw new IllegalStateException("A posição do jogador é inválida.");
            }
            if (player.getPosition() == board.getHead()) {
                player.setBalance(player.getBalance() + salary);
                System.out.println(player.getName() + " recebeu salário de " + salary);
            }
        }

        handleNode(player);
    }

    // Método para lidar com a lógica do nó em que o jogador está
    private void handleNode(Player player) {
        Board.Node node = player.getPosition();
        if (node == null) {
            throw new IllegalStateException("A posição do jogador é inválida.");
        }
        switch (node.type) {
            case "Imóvel":
                Property property = node.property;
                if (property.getOwner() == null) {
                    if (player.getBalance() >= property.getPurchasePrice()) {
                        player.setBalance(player.getBalance() - property.getPurchasePrice());
                        property.setOwner(player);
                        player.addProperty(property);
                        System.out.println(player.getName() + " comprou " + property.getName());
                    } else {
                        System.out.println(player.getName() + " não tem saldo suficiente para comprar " + property.getName());
                    }
                } else if (!property.getOwner().equals(player)) {
                    double rent = property.getRentPrice();
                    player.setBalance(player.getBalance() - rent);
                    property.getOwner().setBalance(property.getOwner().getBalance() + rent);
                    System.out.println(player.getName() + " pagou aluguel de " + rent + " a " + property.getOwner().getName());
                }
                break;

            case "Imposto":
                double tax = player.getBalance() * 0.05;
                player.setBalance(player.getBalance() - tax);
                System.out.println(player.getName() + " pagou imposto de " + tax);
                break;

            case "Restituição":
                double refund = salary * 0.1;
                player.setBalance(player.getBalance() + refund);
                System.out.println(player.getName() + " recebeu restituição de " + refund);
                break;

            case "Prisão":
                System.out.println(player.getName() + " foi para a prisão! Perde 3 turnos.");
                player.setPrisonTurns(3); // Define o número de turnos que o jogador ficará preso
                break;

            default:
                System.out.println(player.getName() + " está em uma casa especial.");
        }
    }

    // Método para finalizar o jogo
    private void endGame() {
        players.sort((p1, p2) -> Double.compare(p2.getBalance(), p1.getBalance()));
        System.out.println("Fim do jogo! O vencedor é " + players.get(0).getName() + " com saldo de " + players.get(0).getBalance());
    }
}
