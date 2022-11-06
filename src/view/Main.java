package view;

import models.factories.CopperCableFactory;
import models.factories.CopperPlateFactory;
import models.factories.Foundry;

/*
 *
 * @author Roman Netesa
 *
 */
public class Main {
    public static void main(String[] args) {
        Foundry foundry = new Foundry();

        CopperPlateFactory copperPlateFactory = new CopperPlateFactory(foundry);
        Thread copperPlateFactoryThread = new Thread(copperPlateFactory);
        CopperCableFactory copperCableFactory = new CopperCableFactory(foundry);
        Thread copperCableFactoryThread = new Thread(copperCableFactory);

        copperPlateFactoryThread.start();
        copperCableFactoryThread.start();


    }
}