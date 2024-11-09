public class CriteriaInversa extends Criteria{

    @Override
    public No apply(No no, int valor) {

        if(no == null){
            return null;
        }

        if(no.valor < valor){

            if(no.dir != null){
                return no.dir;
            }else{
                return null;
            }

        }

        return null;
       
    }


    
}
