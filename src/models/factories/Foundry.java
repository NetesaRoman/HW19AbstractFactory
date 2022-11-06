package models.factories;

import models.cables.CopperCable;
import models.plates.CopperPlate;

import java.util.LinkedList;
import java.util.Queue;

/*
 *
 * @author Roman Netesa
 * This class contains queues for plates and cables, also it has methods to control threads
 */
public class Foundry {

    private int copperCables = 0;
    private final Object fullQueue = new Object();
    private final Object emptyQueue = new Object();
    private final Queue<CopperPlate> copperQueue = new LinkedList<>();
    private final Queue<CopperCable> copperCableQueue = new LinkedList<>();
    private final int copPlateMaxSize = 10;
    private final int copCableMaxSize = 10;



    public void waitOnFull() throws InterruptedException {
        synchronized (fullQueue) {
            fullQueue.wait();
        }
    }

    public void notifyAllForFull() {
        synchronized (fullQueue) {
            fullQueue.notifyAll();
        }
    }

    public void waitOnEmpty() throws InterruptedException {
        synchronized (emptyQueue) {
            emptyQueue.wait();
        }
    }

    public void notifyAllForEmpty() {
        synchronized (emptyQueue) {
            emptyQueue.notify();
        }
    }

    public void addCopperPlate(CopperPlate plate) {
        synchronized (copperQueue) {
            copperQueue.add(plate);
        }
    }

    public void addCopperCable(CopperCable cable) {
        synchronized (copperCableQueue) {
            copperCableQueue.add(cable);
        }
    }

    public CopperPlate removeCopperPlate() {
        synchronized (copperQueue) {
            return copperQueue.poll();
        }
    }

    public boolean isCopperPlateFull(){
        return copperQueue.size() >= copPlateMaxSize;
    }

    public boolean isCopperPlateEmpty(){
        return copperQueue.isEmpty();
    }

    public boolean isCopperCableFull(){
        return copperCableQueue.size() >= copCableMaxSize;
    }

    public boolean isCopperCableEmpty(){
        return copperCableQueue.isEmpty();
    }

    //GETTERS AND SETTERS






}
