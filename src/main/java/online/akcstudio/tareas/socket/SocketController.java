package online.akcstudio.tareas.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@Controller
public class SocketController {

    @Autowired
    private SocketIOServer server;

    @Autowired
    private SocketService socketService;

    public SocketController(SocketIOServer server, SocketService socketService) {
        // this.server = server;
        // this.socketService = socketService;

        server.addConnectListener(onConnect());
        server.addDisconnectListener(onDisconnect());
        // server.addEventListener("send_message", Message.class, onChatReceived());
        System.out.println("SocketController");
    }

    public void emit(String eventName, Object data) {
        System.out.println("emit: " + eventName + ", " + data);
        server.getBroadcastOperations().sendEvent(eventName, data);
    }

    // @OnConnect
    private ConnectListener onConnect() {
        System.out.println("onConnect");
        return (client) -> {
            var params = client.getHandshakeData().getUrlParams();
            System.out.println("Cliente conectado: " + client.getSessionId());
            System.out.println("params: " + params);
            // emit("connect", new String("Hola"));
        };
    }

    private DisconnectListener onDisconnect() {
        System.out.println("onDisconnect");
        return (client) -> {
            System.out.println("Cliente desconectado: " + client.getSessionId());
            // String room = params.get("room").stream().collect(Collectors.joining());
            // String username = params.get("username").stream().collect(Collectors.joining());
            // socketService.saveInfoMessage(client,
            // String.format(Constants.DISCONNECT_MESSAGE, username), room);
            // log.info("Socket ID[{}] - room[{}] - username [{}] discnnected to chat module
            // through", client.getSessionId().toString(), room, username);
        };
    }

    // @OnEvent("mensaje") // Escucha el evento llamado "mensaje"
    // public void handleMensaje(SocketIOClient client, Object data) {
    // System.out.println("Mensaje recibido desde el cliente: " + data);

    // server.getBroadcastOperations().sendEvent("respuesta", "Mensaje recibido");
    // }
}
