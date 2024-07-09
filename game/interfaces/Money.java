package game.interfaces;

import game.items.Wallet;

public interface Money {
    /**
     * This interface conatins the getters and setters of any actor that contains an instance of a wallert.
     * These methods allow us to return the current state of the wallet and even set the wallet to a particular state.
     *
     */
    Wallet getWallet();
    void setWallet(Wallet wallet);
}
