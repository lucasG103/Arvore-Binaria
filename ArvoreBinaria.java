public class ArvoreBinaria {
    No raiz;

    public ArvoreBinaria() {
        this.raiz = null;
        System.out.println("Arvore criada com sucesso");
    }

    private boolean estaVazia() {
        return this.raiz == null;
    }

    public void inserir(Integer conteudo) {
        No novoNo = new No(conteudo);

        if (estaVazia()) {
            this.raiz = novoNo;
            System.out.println("Raiz criada com sucesso com valor: " + novoNo.getConteudo());
            return;
        }

        No aux = this.raiz;

        while (aux != null) {
            if (aux.getConteudo() > novoNo.getConteudo()) {
                if (aux.getEsquerda() == null) {
                    aux.setEsquerda(novoNo);
                    System.out.println("No " + novoNo.getConteudo() + " inserido com sucesso.");
                    return;
                }
                aux = aux.getEsquerda();

            } else if (aux.getConteudo() < novoNo.getConteudo()) {
                if (aux.getDireita() == null) {
                    aux.setDireita(novoNo);
                    System.out.println("No " + novoNo.getConteudo() + " inserido com sucesso.");
                    return;
                }
                aux = aux.getDireita();

            } else {
                System.out.println("Nao sao permitidos nos repetidos na arvore binaria. O " + novoNo.getConteudo() + " ja existe na arvore.");
                return;
            }
        }
    }

    public void remover(Integer conteudo) {

        if (estaVazia()) {
            System.out.println("A arvore esta vazia.");
            return;
        }

        No atual = this.raiz;
        No pai = null;
        boolean ehEsquerdo = false;

        while (atual != null && !atual.getConteudo().equals(conteudo)) {
            pai = atual;
            if (conteudo < atual.getConteudo()) {
                atual = atual.getEsquerda();
                ehEsquerdo = true;
            } else {
                atual = atual.getDireita();
                ehEsquerdo = false;
            }
        }

        if (atual == null) {
            System.out.println("No " + conteudo + " nao encontrado na arvore.");
            return;
        }

        if (atual.getEsquerda() == null && atual.getDireita() == null) {
            removeFolha(pai, ehEsquerdo);
            System.out.println("No " + conteudo + " removido. Tipo: Remocao de no folha");

        } else if (atual.getEsquerda() != null && atual.getDireita() != null) {
            removeDoisFilhos(atual);
            System.out.println("No " + conteudo + " removido. Tipo: Remocao de no com dois filhos");

        } else {
            removeUmFilho(atual, pai, ehEsquerdo);
            System.out.println("No " + conteudo + " removido. Tipo: Remocao de no com um filho");
        }
    }

    private void removeFolha(No pai, boolean ehEsquerdo) {
        if (pai == null) {
            this.raiz = null;
        } else if (ehEsquerdo) {
            pai.setEsquerda(null);
        } else {
            pai.setDireita(null);
        }
    }

    private void removeUmFilho(No no, No pai, boolean ehEsquerdo) {
        No filho = (no.getEsquerda() != null) ? no.getEsquerda() : no.getDireita();

        if (pai == null) {
            this.raiz = filho;
        } else if (ehEsquerdo) {
            pai.setEsquerda(filho);
        } else {
            pai.setDireita(filho);
        }
    }

    private void removeDoisFilhos(No no) {
        No paiSucessor = no;
        No sucessor = no.getDireita();

        while (sucessor.getEsquerda() != null) {
            paiSucessor = sucessor;
            sucessor = sucessor.getEsquerda();
        }

        no.setConteudo(sucessor.getConteudo());

        if (paiSucessor == no) {
            paiSucessor.setDireita(sucessor.getDireita());
        } else {
            paiSucessor.setEsquerda(sucessor.getDireita());
        }
    }

    public void percurso(String percurso) {
        if (estaVazia()) {
            System.out.println("A arvore esta vazia.");
            return;
        }

        switch (percurso) {
            case "Pre":
                System.out.println("Pre ordem: ");
                preOrdem(this.raiz);
                break;
            case "Em":
                System.out.println("Em ordem: ");
                emOrdem(this.raiz);
                break;
            case "Pos":
                System.out.println("Pos ordem: ");
                posOrdem(this.raiz);
                break;
            default:
                System.out.println("Percurso invalido.");
        }
    }

    private void preOrdem(No no) {
        if (no == null) return;
        System.out.print(no.getConteudo() + " ");
        preOrdem(no.getEsquerda());
        preOrdem(no.getDireita());
    }

    private void emOrdem(No no) {
        if (no == null) return;
        emOrdem(no.getEsquerda());
        System.out.print(no.getConteudo() + " ");
        emOrdem(no.getDireita());
    }

    private void posOrdem(No no) {
        if (no == null) return;
        posOrdem(no.getEsquerda());
        posOrdem(no.getDireita());
        System.out.print(no.getConteudo() + " ");
    }
}
