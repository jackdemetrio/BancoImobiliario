package models;

public class Board {
    public static class Node {
        public String type; // Tipo de nó: "Início", "Imóvel", "Imposto", "Restituição", "Prisão"
        public Property property; // Propriedade associada, apenas para nós do tipo "Imóvel"
        public Node next; // Referência para o próximo nó na lista

        // Construtor da classe Node
        public Node(String type, Property property) {
            this.type = type;
            this.property = property;
            this.next = null;
        }
    }

    private Node head; // Cabeça da lista circular

    // Método para adicionar um novo nó à lista
    public void addNode(String type, Property property) {
        Node newNode = new Node(type, property);
        if (head == null) {
            head = newNode;
            head.next = head; // Aponta para si mesmo para formar a lista circular
        } else {
            Node temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.next = head; // Fecha o círculo
        }
    }

    // Método para obter o nó cabeça da lista
    public Node getHead() {
        return head;
    }
}
