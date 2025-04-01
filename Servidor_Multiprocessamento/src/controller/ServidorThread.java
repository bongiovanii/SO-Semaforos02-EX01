package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ServidorThread extends Thread{

	private static final Random random = new Random();
    private static final Semaphore semaforoBD = new Semaphore(1);
    private int id;

    public ServidorThread(int id) {
        this.id = id;
    }

    private void calcular(int min, int max) throws InterruptedException {
        int tempo = random.nextInt(max - min + 1) + min;
        System.out.println("Thread " + id + " realizando cálculos por " + tempo + "ms");
        Thread.sleep(tempo);
    }

    private void transacaoBD(int tempo) throws InterruptedException {
        semaforoBD.acquire();
        System.out.println("Thread " + id + " realizando transação no BD por " + tempo + "ms");
        Thread.sleep(tempo);
        semaforoBD.release();
    }

    @Override
    public void run() {
        try {
            int tipo = id % 3;
            if (tipo == 1) {
                calcular(200, 1000);
                transacaoBD(1000);
                calcular(200, 1000);
                transacaoBD(1000);
            } else if (tipo == 2) {
                calcular(500, 1500);
                transacaoBD(1500);
                calcular(500, 1500);
                transacaoBD(1500);
                calcular(500, 1500);
                transacaoBD(1500);
            } else {
                calcular(1000, 2000);
                transacaoBD(1500);
                calcular(1000, 2000);
                transacaoBD(1500);
                calcular(1000, 2000);
                transacaoBD(1500);
            }
            System.out.println("Thread " + id + " finalizada.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
