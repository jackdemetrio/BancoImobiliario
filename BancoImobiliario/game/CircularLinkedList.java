package game;

import models.Board;
import models.Property;

public class CircularLinkedList {
    private Board.Node head; // Cabeça da lista circular

    // Construtor da classe CircularLinkedList
    public CircularLinkedList() {
        this.head = null;
    }

    // Método para adicionar um nó à lista circular
    public void addNode(String type, Property property) {
        Board.Node newNode = new Board.Node(type, property);

        if (head == null) {
            head = newNode;
            head.next = head; // O primeiro nó aponta para si mesmo (circularidade)
        } else {
            Board.Node temp = head;
            while (temp.next != head) {
                temp = temp.next; // Vai até o último nó
            }
            temp.next = newNode;  // O último nó aponta para o novo nó
            newNode.next = head;  // O novo nó aponta para o primeiro nó (circularidade)
        }
    }

    // Método para obter o nó inicial da lista
    public Board.Node getHead() {
        return head;
    }

    // Método para exibir todas as casas do tabuleiro
    public void displayBoard() {
        if (head == null) {
            System.out.println("Tabuleiro vazio.");
            return;
        }

        Board.Node current = head;
        int counter = 1;
        do {
            System.out.println("Casa " + counter + ": Tipo = " + current.type);
            if (current.property != null) {
                System.out.println("  Imóvel: " + current.property.getName());
            }
            current = current.next;
            counter++;
        } while (current != head); // Continua até voltar ao início (circular)
    }

    // Método para remover um nó específico (utilizado para casas de prisão ou casas temporárias)
    public void removeNode(String type) {
        if (head == null) {
            System.out.println("A lista está vazia.");
            return;
        }

        // Se o nó a ser removido for o primeiro
        if (head.type.equals(type)) {
            if (head.next == head) { // Se for o único nó
                head = null;
            } else {
                Board.Node temp = head;
                while (temp.next != head) {
                    temp = temp.next;
                }
                head = head.next;
                temp.next = head;
            }
            return;
        }

        // Remover qualquer outro nó
        Board.Node current = head;
        while (current.next != head && !current.next.type.equals(type)) {
            current = current.next;
        }

        if (current.next == head) {
            System.out.println("Casa não encontrada.");
        } else {
            current.next = current.next.next;
        }
    }
}
