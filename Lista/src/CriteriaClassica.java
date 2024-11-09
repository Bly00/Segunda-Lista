public class CriteriaClassica extends Criteria{

    @Override
    public No apply(No no, int valor) {

        if(no == null){
            return null;
        }
        
    
        if(no.valor >= valor){

            if(no.esq != null){
                return no.esq;
            }
            else{
                return null;
            }

        }

       return null;
      
    }

    
    
}
