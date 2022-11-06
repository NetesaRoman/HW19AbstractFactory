package models.api;

import models.factories.Foundry;

/*
 *
 * @author Roman Netesa
 *
 */
public abstract class CableFactory implements Runnable{

    private final Foundry foundry;
    private volatile boolean runFlag;

    public CableFactory(Foundry foundry) {
        this.foundry = foundry;
        runFlag = true;
    }

    public abstract Cable createCable();

    public void stop() {
        runFlag = false;
        foundry.notifyAllForFull();
    }

    public Foundry getFoundry() {
        return foundry;
    }

    public boolean isRunFlag() {
        return runFlag;
    }
}
