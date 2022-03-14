package controller.logic.server;

import controller.dao.ClientDAO;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;
import view.ServerView;

public class ServerController implements Runnable {

    // const
    public static final String defaultIP = "192.168.10.154"; // please set your IPv4 address

    // reference
    private ServerView view;

    // field
    private ServerSocket serverSocket;
    private Socket socket;

    public ServerController(ServerView view) {
        this.view = view;
        Thread thread = new Thread(this); // receive data from clients
        thread.start();
    }

    @Override
    public void run() {
        view.printMessage("Waiting for a connection");
        try {
            serverSocket = new ServerSocket(5000);
            while (true) {
                socket = serverSocket.accept();
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                String message = dataInputStream.readUTF();
                if (message.equals("VALIDATE")) {
                    validate(dataInputStream);
                } else if (message.equals("DISCONNECT")) {
                    disconnect(dataInputStream.readUTF());
                } else if (message.equals("READ")) {
                    read(dataInputStream.readUTF());
                } else {
                    System.out.println("invalid petition code"); // server debugging
                }
            }
        } catch (IOException ex) {
            view.printMessage(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void validate(DataInputStream dataInputStream) throws IOException {
        String userName = dataInputStream.readUTF();
        String password = dataInputStream.readUTF();
        boolean valid = ClientDAO.validate(userName, password);
        Socket socketResponse = new Socket(defaultIP, 5050);
        DataOutputStream dataOutputStream = new DataOutputStream(socketResponse.getOutputStream());
        if (valid) {
            view.printMessage(userName + " has been connected from: localhost");
            view.printMessage("Waiting for a new connection");
        } else {
            view.printMessage("An unregistered client has tried to connect");
        }
        dataOutputStream.writeUTF(valid + "");
        if (valid) {
            String message = "Welcome " + userName + "!";
            dataOutputStream.writeUTF(message);
            read(message);
            dataOutputStream.writeUTF(userName);
        }
        socketResponse.close();
    }

    public void disconnect(String userName) throws IOException {
        Socket socketResponse = new Socket(defaultIP, 5050);
        DataOutputStream dataOutputStream = new DataOutputStream(socketResponse.getOutputStream());
        String message = "See you later " + userName + "!";
        dataOutputStream.writeUTF(message);
        read(message);
        socketResponse.close();
        view.printMessage("An user has disconnected from server");
        view.printMessage("Waiting for a new connection");
    }

    public void read(String text) {
        if(text.equals("BYE")||text.equals("bye")){
            
        }
        view.printMessage("Reproducing text: " + text);
        Speech.getSpeech().read(text);
        System.out.println(text);
    }
}

class Speech {
    private static Speech speech;
    //private Voice[] voices;
    //private Voice voice;
    private Synthesizer synth;
    private SynthesizerModeDesc desc;
    private static final String voiceName = "kevin16";
    
    public Speech() {
        try {
            System.setProperty("FreeTTSSynthEngineCentral", "com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
            desc = new SynthesizerModeDesc(null, "general", Locale.US, null, null);

            synth = Central.createSynthesizer(desc);
            synth.allocate();
            desc = (SynthesizerModeDesc) synth.getEngineModeDesc();
            Voice[] voices = desc.getVoices();
            Voice voice = null;
            for (Voice entry : voices) {
                if (entry.getName().equals(voiceName)) {
                    voice = entry;
                    break;
                }
            }
            synth.getSynthesizerProperties().setVoice(voice);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void read(String text) {
        try {
            synth.resume();
            synth.speakPlainText(text, null);
            synth.waitEngineState(Synthesizer.QUEUE_EMPTY);
            //synth.deallocate();

        } catch (Exception ex) {
            String message = " missing speech.properties in " + System.getProperty("user.home") + "\n";
            System.out.println("" + ex);
            System.out.println(message);
            ex.printStackTrace();
        }
    }
    
    public static Speech getSpeech() {
        if(speech == null) {
            speech = new Speech();
        }
        return speech;
    }
}
