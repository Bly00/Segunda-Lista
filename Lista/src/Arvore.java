import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arvore {
    
    No raiz;
   

    public static void main(String[] args) {

        Arvore tree = new Arvore();

        tree.addNo(false, 10, null);
        tree.addNo(true, 20, tree.raiz);
        tree.addNo(false, 30, tree.raiz);



        List<Integer> a = new ArrayList<>();

        a.add(10);
        a.add(20);
        a.add(30);
        a.add(40);
        a.add(50);

        tree.addLista(a);

        tree.mostrarArvore();

    }

    

    //Metodos publicos//

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


    public void mostrarArvore(){
        mostrarArvore(this.raiz);
    } 


    public int numNo(){
        return numNo(this.raiz);
    }


    public int altura(){
        return altura(this.raiz);
    }


    public List<No> encontreNum(int[] procurados){
        return encontreNum(procurados, this.raiz);
    }


    public List<No> encontreFolhas(){
        return encontreFolhas(this.raiz);
    }


    public void remover(No remove){
        remover(this.raiz, remove);
    }


    public void removerFilhos(No remove){
        removerFilhos(this.raiz, remove);
    }


    public No noIncompleto(){
        return noIncompleto(this.raiz);
    }


    public void concatenar(No refNo, Arvore extensao){
        concatenar(this.raiz, refNo, extensao);
    }


    public void addLista(List<Integer> elementos){

            Random r = new Random();

            while(!elementos.isEmpty()) {

                No noInc = this.noIncompleto();

                if(noInc.esq == null && noInc.dir == null){
                    this.addNo(r.nextBoolean(), elementos.getFirst(), noInc);
                }else{
                    this.addNo(noInc.esq == null, elementos.getFirst(), noInc);
                }

                elementos.removeFirst();

            }


    }

    //----------------------------------------------

    //Metodos privados

   
    private void addEsq(int valor, No ref){

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


    private void addDir(int valor, No ref){

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

    
    private void mostrarArvore(No no){

        if(no == null){
            return;
        }

        System.out.println(no.valor);
        mostrarArvore(no.esq);
        mostrarArvore(no.dir);

    }

  
    private int numNo(No no){

        if(no == null){
            return 0;
        }
        return 1 + numNo(no.esq) + numNo(no.dir);
    }


    private int altura(No no){

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

    


    private List<No> encontreNum(int[] procurados, No no){

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

    

    private List<No> encontreFolhas(No no){

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

   

    private void remover(No raiz, No remove){

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

  

    private void removerFilhos(No raiz, No noRemove){

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

   

    private void concatenar(No raiz, No refNo, Arvore extensao){

        if(raiz == null){
            return;
        }

        if(raiz == refNo){
            if(refNo.esq == null){
                raiz.esq = extensao.raiz;
                return;
            }else
            if(refNo.dir == null){
                raiz.dir = extensao.raiz;
                return;
            }else{
                System.out.println("Filhos ja oculpados");
            }
        }

        concatenar(raiz.esq, refNo,  extensao);
        concatenar(raiz.dir, refNo, extensao);

    }

   
    private No noIncompleto(No raiz){

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

}