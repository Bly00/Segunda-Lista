public class Arvore {

    No raiz;

    public static void main(String[] args) {
        
        Arvore tree = new Arvore();
        Arvore tree2 = new Arvore();

        tree.addNo(true, 10, null);
        tree.addNo(true, 20, tree.raiz);
        tree.addNo(false, 30, tree.raiz);

        tree2.addNo(true, 10, null);
        tree2.addNo(true, 50, tree2.raiz);
        tree2.addNo(false, 30, tree2.raiz);
       

        tree.concatenar(tree2, tree.raiz.esq);
        
        
     
        mostrarArvore(tree.raiz);
       
        

        System.out.println("Tamanho: " + tree.numNo());

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

    

}