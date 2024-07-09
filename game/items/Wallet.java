package game.items;
import game.interfaces.Soul;

public class Wallet implements Soul {
    /**
     * This class implemets the soul interface and handles the transfer of souls as well as
     * adding or subtracting the number of souls.
     */
    public int totalSouls;

    public Wallet(int totalSouls) {
        /**
         * constructor
         * contains an integer attribute that represents the total number of money in the wallet.
         */
        this.totalSouls = totalSouls;
    }

    @Override
    public void transferSouls(Soul soulObject) {
        /**
         * transfers the souls to an object that also implents the soul interface.
         *
         * @param soulObject
         */
        soulObject.addSouls(this.totalSouls);
        subtractSouls(this.totalSouls);
    }

    @Override
    public boolean addSouls(int souls) {
        /**
         * adds a given number of souls to the wallet
         */
        this.totalSouls = souls + this.totalSouls;
        return Soul.super.addSouls(souls);
    }

    @Override
    public boolean subtractSouls(int souls) {
        /**
         * subtracts a given a number of souls from the wallet
         */
        this.totalSouls = this.totalSouls-souls;
        return Soul.super.subtractSouls(souls);
    }
}
