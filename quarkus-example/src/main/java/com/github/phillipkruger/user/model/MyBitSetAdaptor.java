package com.github.phillipkruger.user.model;

import java.util.BitSet;

import javax.json.bind.adapter.JsonbAdapter;

/**
 *
 * @author Phillip Kruger (phillip.kruger@redhat.com)
 */
public class MyBitSetAdaptor implements JsonbAdapter<BitSet, String> {

    @Override
    public String adaptToJson(BitSet bitSet) throws Exception {
        return bitSet.toString();
    }

    @Override
    public BitSet adaptFromJson(String s) throws Exception {
        String[] items = s.split(",");
        BitSet bs = new BitSet();
        for(String i:items){
            i = i.replace("{", "");
            i = i.replace("}", "");
            i = i.trim();
            int b = Integer.valueOf(i);
            bs.set(b);
        }
        return bs;
    }
}