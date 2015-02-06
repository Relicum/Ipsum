package com.relicum.ipsum.Menus;

import org.apache.commons.lang3.text.StrBuilder;
import org.bukkit.Bukkit;
import org.bukkit.util.Vector;
import com.relicum.ipsum.Utils.TextProcessor;

/**
 * Name: Region.java Created: 04 February 2015
 *
 * @author Relicum
 * @version 0.0.1
 */
public class Region {

    private String min = "";
    private String max = "";
    private String cloneTo = "";
    private String mask = "";
    private String mode = "normal";

    public Region() {

    }

    public Region setMask(MASK mask) {
        this.mask = mask.get();
        return this;
    }

    public Region setMax(Vector max) {
        this.max = max.toString().replaceAll(",", " ");
        return this;
    }

    public Region setMin(Vector min) {
        this.min = min.toString().replaceAll(",", " ");
        return this;
    }

    public Region setMode(MODE mode) {
        this.mode = mode.get();
        return this;
    }

    public Region setCloneTo(Vector cloneTo) {
        this.cloneTo = cloneTo.toString().replaceAll(",", " ");
        return this;
    }

    public String buildCmd() {

        return "clone " + min + " " + max + " " + cloneTo + " " + mask + " " + mode;
    }

    public void runCmd() {

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), buildCmd());
    }

    public String getCloneTo() {
        return cloneTo;
    }

    public String getMode() {
        return mode;
    }

    public String getMin() {
        return min;
    }

    public String getMax() {
        return max;
    }

    public String getMask() {
        return mask;
    }

    @Override
    public String toString() {

        StrBuilder sb = new StrBuilder();
        sb.setNewLineText("\n");
        sb.appendNewLine();
        sb.append("&6Clone Min Location: &b").append(min).appendNewLine();
        sb.append("&6Clone Max Location: &b").append(max).appendNewLine();
        sb.append("&aClone to Location: &5").append(cloneTo).appendNewLine();
        sb.appendNewLine();
        sb.append("&6Mask: ");
        if (mask.isEmpty())
            sb.append("&bNot Set");
        else
            sb.append("&b").append(mask);
        sb.appendNewLine();
        sb.append("&6Mode: ");
        if (mode.isEmpty())
            sb.append("&bNot Set");
        else
            sb.append("&b").append(mode);
        sb.appendNewLine();

        return TextProcessor.stripColor(sb.toString());
    }
}
