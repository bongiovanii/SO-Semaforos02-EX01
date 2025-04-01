package view;

import controller.ServidorThread;

public class ServidorMultiprocess {
	public static void main(String[] args) {
		for (int i = 1; i <= 21; i++) {
            new ServidorThread(i).start();
        }
	}
}
