package com.relicum.ipsum.Permission;

/**
 * The type Permission.
 */
public class Permission {

    /**
     * The Permission.
     */
    protected String permission;

    /**
     * Instantiates a new Permission.
     *
     * @param permission the permission
     * @param isDefault  the is default
     */
    public Permission(String permission, final boolean isDefault) {
        this.permission = permission;
    }

    /**
     * Instantiates a new Permission.
     *
     * @param permission the permission
     */
    public Permission(String permission) {
        this(permission, false);
    }

    /**
     * Get raw permission.
     *
     * @return the string
     */
    public String getRawPermission() {
        return this.permission;
    }
}
