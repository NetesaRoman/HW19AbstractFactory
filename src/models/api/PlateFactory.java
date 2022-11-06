package models.api;

import models.factories.Foundry;

/*
 *
 * @author Roman Netesa
 *
 */
public abstract class PlateFactory implements Runnable{

   private final Foundry foundry;
   private volatile boolean runFlag;

   public PlateFactory(Foundry foundry, boolean runFlag) {
      this.foundry = foundry;
      this.runFlag = runFlag;
   }

   public abstract void produce() ;

   public void stop() {
      runFlag = false;
      foundry.notifyAllForFull();
   }


   public abstract Plate createPlate();

   public Foundry getFoundry() {
      return foundry;
   }

   public boolean isRunFlag() {
      return runFlag;
   }
}
