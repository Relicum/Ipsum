package com.relicum.ipsum.Commands;

import java.lang.annotation.*;

/**
 * Name: CmdInfo.java Created: 05 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CmdInfo {

    public String description();

    public String usage();

    public String label();

    public String permission();

    public int minArgs();

    public int maxArgs();

    public boolean playerOnly();

    public boolean subCommand();

}
