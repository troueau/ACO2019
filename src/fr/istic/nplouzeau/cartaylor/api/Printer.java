package fr.istic.nplouzeau.cartaylor.api;

import java.io.PrintStream;

public interface Printer {
    /**
     * print an HTML descrption of current configuration
     * @param ps a PrintStream
     */
    void printDescription(PrintStream ps);
}
