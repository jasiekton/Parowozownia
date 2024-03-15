package projekt;

public class Train extends Thread {
    private final TrainConsumerProducer tracks;
    private final TrainConsumerProducer signalBox;
    private final TrainConsumerProducer roundHouse;
    public int id;

    public char direction;

    public Train(TrainConsumerProducer tracks, TrainConsumerProducer signalBox, TrainConsumerProducer roundHouse, int id, char direction) {
        this.tracks = tracks;
        this.signalBox = signalBox;
        this.roundHouse = roundHouse;
        this.id = id;
        this.direction=direction;

    }

    private void Render(){
        Controller.Instance.RenderChanges(tracks, signalBox, roundHouse);
    }

    @Override
    public void run() {
        long starttime = System.currentTimeMillis();
        //wjezdzamy na tory
        try {
            roundHouse.produce1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            tracks.produce(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(tracks.is_renovation) {
            try {
                tracks.consume(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (tracks.is_renovation) {

            }
            try {
                tracks.produce(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long time = Math.round((System.currentTimeMillis()-starttime)/1000);
        System.out.println(" Pociag nr " + id +  " wjeżdza na tory, czas: " + time);
        Render();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //wjezdzamy na nastawnie, zwalniamy tory
        try {
            signalBox.produce(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            tracks.consume(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        time = Math.round((System.currentTimeMillis()-starttime)/1000);
        System.out.println(" Pociag nr " + id +  " wjeżdza na nastawnie, czas: "+ time);
        Render();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //wjezdzamy do parowozowni, zwalniamy nastawnie
        try {
            signalBox.consume(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            roundHouse.produce2(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        time = Math.round((System.currentTimeMillis()-starttime)/1000);
        System.out.println(" Pociag nr " + id +  " wjeżdza do parowozowni i zaczyna naprawę, czas: "+ time);
        Render();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.direction='R';
        //wjezdzamy na nastawnie, zwalnami miejsce w parowozowni
        try {
            tracks.produce1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            signalBox.produce(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            roundHouse.consume(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        time = Math.round((System.currentTimeMillis()-starttime)/1000);
        System.out.println(" Pociag nr " + id +  " kończy naprawę i wjeżdza na nastawnię, czas: "+ time);
        Render();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //wjezdzamy na tory, zwalniamy miejsce w nastawni - tory byly juz zajete
        try {
            signalBox.consume(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            tracks.produce2(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        time = Math.round((System.currentTimeMillis()-starttime)/1000);
        System.out.println(" Pociag nr " + id +  " wjeżdza z powrotem na tory, czas: "+ time);
        Render();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //wyjezdzamy z obiektu
        try {
            tracks.consume(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        time = Math.round((System.currentTimeMillis()-starttime)/1000);
        System.out.println(" Pociag nr " + id +  " wyjeżdza z obiektu, czas: " + time);
        Render();
    }
}
