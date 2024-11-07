import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arvore {

    No raiz;

    public static void main(String[] args) {
        
        Arvore tree = new Arvore();
        Arvore tree2 = new Arvore();



        tree.addNo(true, 10, null);
        tree.addNo(true, 20, tree.raiz);
        tree.addNo(false, 30, tree.raiz);


        tree2.addNo(true, 80, tree2.raiz);
        tree2.addNo(true, 20, tree2.raiz);
        tree2.addNo(false, 30, tree2.raiz);
        tree2.addNo(true, 40, tree2.raiz.esq);

        tree.remover(tree.raiz, tree.raiz.dir);

        tree.concatenar(tree.raiz, tree.raiz, tree2);

        mostrarArvore(tree.raiz);

        No  no = tree.noImcompleto(tree.raiz);

        System.out.println("No incompleto: " + no.valor);



    }

    public void addEsq(int valor, No ref){

        No novo = new No();
        novo.valor = valor;

        if(raiz == null){
            raiz = novo;
        }

        if(ref.esq == null){
            ref.esq = novo;
        }else{
            System.out.println("No ja ocupado");
        }

    }

    public void addDir(int valor, No ref){

        No novo = new No();
        novo.valor = valor;

        if(raiz == null){
            raiz = novo;
        }

        if(ref.dir == null){
            ref.dir = novo;
        }else{
            System.out.println("No ja ocupado");
        }

    }

    public void addNo(boolean esq, int valor, No refNo){

        if(raiz == null){
            raiz = new No();
            raiz.valor = valor;
            return;
        }else if(refNo == null){
            System.out.println("No referencia nao existe");
            return;
        }

        if(esq){

            addEsq(valor, refNo);
          
        }else{

            addDir(valor, refNo);
           
        }

    }

    public static void mostrarArvore(No no){

        if(no == null){
            return;
        }

        System.out.println(no.valor);
        mostrarArvore(no.esq);
        mostrarArvore(no.dir);

    }

    public int numNo(No no){

        if(no == null){
            return 0;
        }
        return 1 + numNo(no.esq) + numNo(no.dir);
    }

    public int numNo(){
        return numNo(this.raiz);
    }

    public void concatenar(Arvore extensao, No noAtual){

        if(raiz == null){
            return;
        }
        if(noAtual == null){
            System.out.println("No nao existe");
            return;
        }

        noAtual = extensao.raiz;

    }

    public int altura(No no){

        if(no == null){
            return 0;
        }

       int alturaEsq = altura(no.esq);
       int alturaDir = altura(no.dir);

       if(alturaEsq > alturaDir){
           return 1 + alturaEsq;        
        }else{
            return 1 + alturaDir;
        }

    }

    public List<No> encontreNum(int[] procurados, No no){

        if(no == null){
            return new ArrayList<>();
        }

        List<No> list = new ArrayList<>();

        for(int x : procurados){
            if(x == no.valor){
                list.add(no);
                break;
            }
        }

        list.addAll(encontreNum(procurados, no.esq));
        list.addAll(encontreNum(procurados, no.dir));

        return list;
        
    }

    public List<No> encontreFolhas(No no){

        if(no == null){
            return new ArrayList<>();
        }

        List<No> list = new ArrayList<>();

        if(no.esq == null && no.dir == null){
            list.add(no);
        }

        list.addAll(encontreFolhas(no.esq));
        list.addAll(encontreFolhas(no.dir));

        return list;

    }

    public void remover(No raiz, No remove){

        if(raiz == null){
            return;
        }

        if(raiz.esq == remove){
            raiz.esq = null;
            return;
        }
        if(raiz.dir == remove){
            raiz.dir = null;
            return;
        }

        remover(raiz.esq, remove);
        remover(raiz.dir, remove);

    }

    public void removerFilhos(No raiz, No noRemove){

        if(raiz == null){
            return;
        }

        if(raiz == noRemove){
           raiz.esq = null;
           raiz.dir = null;
            return;
        }

        removerFilhos(raiz.esq, noRemove);
        removerFilhos(raiz.dir, noRemove);

    }

    public void concatenar(No raiz, No no, Arvore extensao){

        if(raiz == null){
            return;
        }

        if(raiz == no){
            if(no.esq == null){
                raiz.esq = extensao.raiz;
                return;
            }else
            if(no.dir == null){
                raiz.dir = extensao.raiz;
                return;
            }else{
                System.out.println("Filhos ja oculpados");
            }
        }

        concatenar(raiz.esq, no,  extensao);
        concatenar(raiz.dir, no, extensao);

    }

    public No noImcompleto(No raiz){

        Random ram =new Random();

        if(raiz == null){
             return null;
        }

        if(raiz.esq == null || raiz.dir == null){
            return raiz;
        }

        if(ram.nextBoolean() == true){
            noImcompleto(raiz.esq);
        }else{
            noImcompleto(raiz.dir);
        }

        return raiz;

    }


    

}