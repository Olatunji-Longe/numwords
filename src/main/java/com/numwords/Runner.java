package com.numwords;

import com.numwords.translation.Translator;
import picocli.CommandLine;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Olatunji O. Longe
 * @since 06 May, 2021, 12:47 p.m.
 */
@CommandLine.Command(
        name = "numwords",
        version = "1.0",
        description = "This application prints out a number as words",
        mixinStandardHelpOptions = true
)
public class Runner implements Runnable {

    private static final Logger logger = Logger.getLogger(Runner.class.getName());

    @CommandLine.Option(
            names = {"-n", "--num"},
            paramLabel = "input number",
            required = true,
            description = "the number to be translated to words"
    )
    private String number;

    public static void main(String[] args) {
        System.exit(new CommandLine(new Runner()).execute(args));
    }

    @Override
    public void run() {
        try {
            logger.log(Level.INFO, String.format("program inputs {number: %s}", number));
            System.out.printf("%n[%s = %s]%n%n", number, new Translator().toWords(number));
        } catch (Exception ex) {
            logger.log(Level.SEVERE, String.format("Error while translating [number: %s]", number), ex);
        }
    }
}
