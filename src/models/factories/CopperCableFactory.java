package models.factories;

import models.api.Cable;
import models.api.CableFactory;
import models.cables.CopperCable;
import models.plates.CopperPlate;

/*
 *
 * @author Roman Netesa
 *
 */
public class CopperCableFactory extends CableFactory {


    public CopperCableFactory(Foundry foundry) {
        super(foundry);
    }




    @Override
    public void run() {
        consume();
    }

    public void consume() {
        while (isRunFlag()) {
            CopperPlate plate;
            if (getFoundry().isCopperPlateEmpty()) {
                try {
                    getFoundry().waitOnEmpty();
                } catch (InterruptedException e) {
                    break;
                }
            }
            if (!isRunFlag()) {
                break;
            }
            plate = getFoundry().removeCopperPlate();
            System.out.println("Copper plate CONSUMED!!!!!!");
            getFoundry().notifyAllForFull();
            produce();
        }
    }

    public void produce() {


            CopperCable cable = createCable();

            while (getFoundry().isCopperCableFull()) {
                try {
                    getFoundry().waitOnFull();
                } catch (InterruptedException e) {
                    break;
                }
            }
            if (!isRunFlag()) {
                return;
            }
            getFoundry().addCopperCable(cable);
            getFoundry().notifyAllForEmpty();

    }


    public CopperCable createCable(){
        System.out.println("CopperCableCreated");
        return new CopperCable(1, 2,4);
    }
}
