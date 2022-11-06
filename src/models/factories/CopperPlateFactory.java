package models.factories;

import models.api.Plate;
import models.api.PlateFactory;
import models.plates.CopperPlate;

/*
 *
 * @author Roman Netesa
 *
 */
public class CopperPlateFactory extends PlateFactory {


    public CopperPlateFactory(Foundry foundry) {
        super(foundry, true);
    }

    @Override
    public void produce() {
        {
            while (isRunFlag()) {
                CopperPlate plate = createPlate();

                while ( getFoundry().isCopperPlateFull()) {
                    try {
                        getFoundry().waitOnFull();
                    } catch (InterruptedException e) {
                        break;
                    }
                }
                if (!isRunFlag()) {
                    break;
                }
                getFoundry().addCopperPlate(plate);
                getFoundry().notifyAllForEmpty();
            }
        }
    }

    public CopperPlate createPlate(){
        System.out.println("Copper plate created!");
        return new CopperPlate();
    }

    @Override
    public void run() {
        produce();
    }
}
