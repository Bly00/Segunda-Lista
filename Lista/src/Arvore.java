import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arvore {

    No raiz;

    public static void main(String[] args) {
        
       Arvore tree = new Arvore();

       List<No> nos = new ArrayList<>();

       No no1 = new No();
       No no2 = new No();
       No no3 = new No();
       No no4 = new No();
       No no5 = new No();
       No no6 = new No();
       No no7 = new No();
       No no8 = new No();
       No no9 = new No();
       No no10 = new No();
       No no11 = new No();
       No no12 = new No();
       No no13 = new No();
       No no14 = new No();
       No no15 = new No();
       No no16 = new No();
       No no17 = new No();
       No no18 = new No();
       No no19 = new No();
       No no20 = new No();
         
       no1.valor = 10;
       no2.valor = 20;
       no3.valor = 30;
       no4.valor = 40;
       no5.valor = 50;
       no6.valor = 60;
       no7.valor = 70;
       no8.valor = 80;
       no9.valor = 90;
       no10.valor = 100;
       no11.valor = 110;
       no12.valor = 120;
       no13.valor = 130;
       no14.valor = 140;
       no15.valor = 150;
       no16.valor = 160;
       no17.valor = 170;
       no18.valor = 180;
       no19.valor = 190;
       no20.valor = 200;
      

       nos.add(no1);
       nos.add(no2);
       nos.add(no3);
       nos.add(no4);
       nos.add(no5);
       nos.add(no6);
       nos.add(no7);
       nos.add(no8);
       nos.add(no9);
       nos.add(no10);
       nos.add(no11);
       nos.add(no12);
       nos.add(no13);
       nos.add(no14);
       nos.add(no15);
       nos.add(no16);
       nos.add(no17);
       nos.add(no18);
       nos.add(no19);
       nos.add(no20);

       int[] x = { 30, 50, 120, 230};

       tree = tree.addLista(nos);

       System.out.println("Arvore: ");
       mostrarArvore(tree.raiz);

       System.out.print("Valores procurados:");
       for(int ints: x){
        System.out.print(" " + ints);
       }

       System.out.println();

       System.out.print("Valores encontrados:");

       for(No n : tree.encontreNum(x, tree.raiz)){
            System.out.print(" " + n.valor);
       }
       

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

    public No noIncompleto(No raiz){

        Random ram = new Random();

        if(raiz == null){
             return null;
        }



        if(raiz.esq == null || raiz.dir == null){
            return raiz;
        }

        if(ram.nextBoolean()){
           
            No a = noIncompleto(raiz.esq);
            if(a.esq == null || a.dir == null)
            return a;

        }else{   

            No a = noIncompleto(raiz.dir);
            if(a.esq == null || a.dir == null)
            return a;

        }

        
        return null;

    }

    public Arvore addLista(List<No> nós){

        Arvore treeList = new Arvore();
        treeList.raiz = new No();

        treeList.raiz.valor = nós.get(0).valor;

        nós.remove(0);

        for(No x : nós){

            No noIncompleto = treeList.noIncompleto(treeList.raiz);

            if(noIncompleto.esq == null){
                treeList.addNo(true, x.valor, noIncompleto);
            }else if(noIncompleto.dir == null){
                treeList.addNo(false, x.valor, noIncompleto);
            }

        }

        return treeList;

    }
    

}