package com.playx.gtx.data;
import com.playx.gtx.GTXRegistries;
import com.playx.gtx.data.lang.LangHandler;
import com.tterrag.registrate.providers.ProviderType;

public class GTXDataGen {
    public static void init() {
        GTXRegistries.REGISTRATE.addDataGenerator(ProviderType.LANG, LangHandler::init);
    }
}
