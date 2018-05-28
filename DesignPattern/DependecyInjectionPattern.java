package DesignPattern;

public class DependecyInjectionPattern {
	public static void main(String[] args) {
		Client clientA = new Client(new ServerA());
		clientA.processRequest();

		Client clientB = new Client(new ServerB());
		clientB.processRequest();
	}
}

interface ServerInterface {
	public void processRequest();
}
class ServerA implements ServerInterface {
	private String serverName;
	public ServerA() {
		serverName = "serverA";
	}
	@Override
	public void processRequest() {
		System.out.println("Process requests using" + serverName);
	}
}
class ServerB implements ServerInterface {
	private String serverName;
	public ServerB() {
		serverName = "serverB";
	}
	@Override
	public void processRequest() {
		System.out.println("Process requests using" + serverName);
	}
}

class Client {
	private ServerInterface server;
	public Client(ServerInterface serverImp) {
		this.server = serverImp;
	}
	public void processRequest() {
		this.server.processRequest();
	}
}