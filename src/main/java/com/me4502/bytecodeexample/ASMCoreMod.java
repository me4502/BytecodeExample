package com.me4502.bytecodeexample;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;

@IFMLLoadingPlugin.Name(value = "BytecodeExample")
public class ASMCoreMod implements IFMLLoadingPlugin {

    public ASMCoreMod() {
        MixinBootstrap.init();

        try {
            Mixins.addConfiguration("mixins.example.json");
        } catch(Throwable t) {
            t.printStackTrace();
            ClassLoader cl = this.getClass().getClassLoader();
            URL[] urls = ((URLClassLoader)cl).getURLs();
            for(URL url: urls){
                System.out.println(url.getFile());
            }
        }
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[] {
                "com.me4502.bytecodeexample.asm.ExampleTransformer"
        };
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
