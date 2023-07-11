package it.core.commonUtilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Console {

    private static final BufferedReader console = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

    public static BufferedReader getConsole(){
        return console;
    }

    private Console(){

    }
}