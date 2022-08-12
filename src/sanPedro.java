import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import crypto.Cryptographer;
import gui.Ventana;

public class sanPedro {
    public static void main(String[] args) {
        long startTime;
        long endTime;
        long elapsed;

        Scanner teclado = new Scanner(System.in);
        String password;
        String mode;
        boolean encrypt = false;
        String fileName;
        File sourceFile;
        File targetFile;
        byte[] text;

        Ventana ventanaDeAplicacion = new Ventana();
        ventanaDeAplicacion.setVisible(true);
        
        // Request password
        System.out.print("Password: ");
        password = teclado.nextLine();

        // Request mode
        System.out.print("Encrypt or decrypt (e/d): ");
        mode = teclado.nextLine();
        if (mode.toLowerCase().equals("e")) {
            encrypt = true;
        } else if (!mode.toLowerCase().equals("d")) {
            // if mode is not 'e' and it is not 'd' either application exits
            System.out.println("Program terminating.");
            System.exit(-1);
        }

        // Request source file
        while (true) {
            System.out.print("Origin file name: ");
            fileName = teclado.nextLine();
            sourceFile = new File(fileName);
            if (!sourceFile.isFile()) {
                System.out.println("No such file.");
            } else break;
        }

        // Request target file
        while (true) {
            System.out.print("Target file name: ");
            fileName = teclado.nextLine();
            targetFile = new File(fileName);
            if (targetFile.isFile()) {
                System.out.print("The target file already exists. Would you like to overwrite it? y/n: ");
                if (teclado.nextLine().toLowerCase().equals("y")) {
                    break;
                } else if (teclado.nextLine().toLowerCase().equals("n")) {
                    continue;
                }
            } else break;
        }

        // Release input stream
        teclado.close();

        // Read source file to byte[]
        try {
            text = Files.readAllBytes(Paths.get(sourceFile.getPath()));
            
            // Init Cryptographer with passord, file file text byte[], encrypt boolean
            Cryptographer cryptographer = new Cryptographer(password, text, encrypt);

            // Process Cryptographer and measure time elapsed
            startTime = System.currentTimeMillis();
            cryptographer.processCipher();
            endTime = System.currentTimeMillis();
            elapsed = endTime - startTime;
            System.out.println("Encryption/Decryption done in: " + elapsed + " miliseconds");


            // Write Cryptographer.output to target file
            startTime = System.currentTimeMillis();
            Files.write(Paths.get(targetFile.getPath()), cryptographer.getOutput());
            endTime = System.currentTimeMillis();
            elapsed = endTime - startTime;
            System.out.println("File was written in: " + elapsed + " miliseconds");

        } catch (OutOfMemoryError | SecurityException | IOException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

    }
}