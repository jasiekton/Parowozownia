package projekt;

public class TrainQueue {

    public Train[] Trains;

    public TrainQueue(int size){
        Trains = new Train[size];
        for(int i=0;i<Trains.length;i++)
        {
            Trains[i] = null;
        }
    }

    public void add(Train t){
        for(int i = 0; i < Trains.length; i++){
            if(Trains[i] == null) {
                Trains[i]= t;
                return;
            }
        }
    }

    public void remove(Train t) {
        for(int i = 0; i < Trains.length; i++){
            if(Trains[i] != null) {
                if(Trains[i].id == t.id){
                    Trains[i] = null;
                    return;
                }
            }
        }
    }

}
