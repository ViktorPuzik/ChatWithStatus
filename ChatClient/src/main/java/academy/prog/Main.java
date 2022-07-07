package academy.prog;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	static String login;
	static String to;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int res = 0;
		try {
			System.out.println("Enter your login: ");
			login = scanner.nextLine();
			to = null;
	
			Thread th = new Thread(new GetThread());
			th.setDaemon(true);
			th.start();

            System.out.println("Enter your message: ");
			while (true) {
				String text = scanner.nextLine();
				if (text.isEmpty()) break;
				if(text.substring(0,1).equals("@")){
					//Ввод имени адресата
					to = text.substring(1, text.indexOf(" "));
					text = text.substring(text.indexOf(" "));
					Message m = new Message(login, text, to);
					res = m.send(Utils.getURL() + "/add");
				} else if (text.substring(0,1).equals("#")){
					res = ServiceMessage.sendService();
				}else {to = null;

				Message m = new Message(login, text, to);
				res = m.send(Utils.getURL() + "/add");
				}
				if (res != 200) { // 200 OK
					System.out.println("HTTP error ocurred: " + res);
					return;
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			scanner.close();
		}
	}
}
