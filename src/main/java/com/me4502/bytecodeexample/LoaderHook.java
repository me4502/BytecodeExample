package com.me4502.bytecodeexample;

import net.minecraft.server.MinecraftServer;

import java.io.IOException;

public class LoaderHook {

    public static boolean runMe(MinecraftServer server) {
        for (int i = 0; i < 10; i++) {
            System.out.println("LOADING FUN STUFF");
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            return server.init();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void runMeMixin() {
        for (int i = 0; i < 10; i++) {
            System.out.println("LOADING EVEN MORE FUN STUFF");
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
