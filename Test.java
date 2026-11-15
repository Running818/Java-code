class Speaker{}

class AudioPlayer{
    protected boolean openSpeaker(Speaker sp){
        System.out.println("普通播放器：插线打开音响");
        return true;
    }
}
class StreamingAudioPlayer extends AudioPlayer{
    @Override
    protected boolean openSpeaker(Speaker sp){
        System.out.println("流媒体播放器：蓝牙连接打开音响");
        return true;
    }
}
public class Test {
    public static void main(String[] args){
        Speaker mySpeaker = new Speaker();

        AudioPlayer normalPlayer = new AudioPlayer();
        normalPlayer.openSpeaker(mySpeaker);

        AudioPlayer streamPlayer = new StreamingAudioPlayer();
        streamPlayer.openSpeaker(mySpeaker);
    }
}
