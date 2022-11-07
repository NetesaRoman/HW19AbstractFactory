package models.factories;

import models.api.Cable;
import models.api.CableFactory;
import models.api.Plate;
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
            Plate plate;
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
            produce(plate);
        }
    }

    public void produce(Plate plate) {


            CopperCable cable = createCable(plate);

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


    public CopperCable createCable(Plate plate){
        System.out.println("CopperCableCreated");
        return new CopperCable(
                plate.getWeight() - 0.5,
                plate.getWeight() + 2,
                plate.getWeight() / 4
        );
    }
}
