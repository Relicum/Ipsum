package com.relicum.ipsum.Permission;

import java.util.List;

/**
 * The interface Permissible.
 */
public interface Permissible {

    /**
     * Has permission.
     *
     * @param permission the permission
     * @return the boolean
     */
    public boolean hasPermission(final String permission);

    /**
     * Has permission.
     *
     * @param permission the permission
     * @return the boolean
     */
    public boolean hasPermission(final Permission permission);

    /**
     * Gets permissions.
     *
     * @return the permissions
     */
    public List<Permission> getPermissions();
}
