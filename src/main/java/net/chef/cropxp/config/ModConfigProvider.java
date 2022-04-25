package net.chef.cropxp.config;

import com.mojang.datafixers.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModConfigProvider implements SimpleConfig.DefaultConfig {

    private String configContents = "";

    public List<Pair> getConfigsList() {
        return configsList;
    }

    private final List<Pair> configsList = new ArrayList<>();

    public void addKeyValuePair(Pair<String, ?> keyValuePair, String comment) {
        configsList.add(keyValuePair);

        configContents += "#" + comment + " | default: " + keyValuePair.getSecond() + "\n" + keyValuePair.getFirst() + "=" + keyValuePair.getSecond() + "\n";
    }

    public void addKeyValuePairList(Pair<String, String[]> keyValuePair, String comment) {
        configsList.add(keyValuePair);

        configContents += "#" + comment + " | default: " + Arrays.asList(keyValuePair.getSecond()) + "\n" + keyValuePair.getFirst() + "= " + Arrays.asList(keyValuePair.getSecond());

    }

    @Override
    public String get(String namespace) {
        return configContents;
    }
}