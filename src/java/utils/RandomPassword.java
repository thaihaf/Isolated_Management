/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package utils;

import java.util.Random;

/**
 *
 * @author Mountain
 */
public class RandomPassword {

    public String RandomPassword() {
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String specialChar = "~!@#$%^&*()_+-=[]{};':\",./<>?";

        String allChar = upperAlphabet + lowerAlphabet + numbers + specialChar;

        StringBuilder sb = new StringBuilder();

        Random random = new Random();

        int length = 8;

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allChar.length());
            char randomChar = allChar.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
