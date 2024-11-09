

/*  

        1. Represente uma árvore binária em classes. Assim como a classe Lista tem um atributo
        início do tipo Bloco, a classe Tree tem um atributo raiz do tipo Node. 


    public class Arvore {

    No raiz;

    }

    public class No {

    No esq, dir;
    int valor;
    

    public No(int valor) {
        this.valor = valor;
    }

    public No(){} 
}

    //-----------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------


 
        2. Adicione o método addNo. Esse método tem os parâmetros dir que é booleano, int
        valor, que é o valor que vai ser adicionado, refNo que é o nó no qual o novo nó será
        adicionado.


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
  

    //-----------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------

  
   
        3 -Adicione o método locacalizarNoIncompleto que retorna um nó incompleto através de
        uma descida randômica. Nó incompleto é um nó que não tem pelos 1 (um) nó filho.
        Deve ser desenvolvido um método público e um priva recursivo.


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


    public No noIncompleto(){
        return noIncompleto(this.raiz);
    }
   
   

    //-----------------------------------------------------------------------------------------------------------------
     //-----------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------


   
        4. Adicione o método altura que retorna a altura da árvore.


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

    

    //-----------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------

    

        5 -Adicione o método addLista() que recebe uma lista como parâmetro e utilizando o
        método addNo combinado com o localizarNoIncompleto, insere todos os valores da
        Lista na árvore.


    public static Arvore addLista(List<No> nós){

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


    //-----------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------

        6. Adicione o método numNo que retorna o número total de nós da árvore.


    private int numNo(No no){

        if(no == null){
            return 0;
        }
        return 1 + numNo(no.esq) + numNo(no.dir);
    }

    //-----------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------

    7. Adicione um método que retorna uma lista com todos os nós da árvore que têm um
    dado array de valores.


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

    //-----------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------

        8. Adicione um método que retorna uma lista com todos os nós folhas da árvore.


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

    //-----------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------

        9. Adicione um método que remove um nó passado como parâmetro.


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

    //-----------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------

        10. Adicione um método que recebe uma árvore como parâmetro e a “concatena” a um nó
        especificado


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


    //-----------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------

        11 - Adicione um método que remove a subárvore de um nó especificado.


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
*/