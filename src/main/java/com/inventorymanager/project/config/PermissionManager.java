package com.inventorymanager.project.config;

import com.inventorymanager.project.model.UserRole;

public class PermissionManager {

    private static PermissionManager instance;

    private PermissionManager() {}

    public static synchronized PermissionManager getInstance() {
        if (instance == null) {
            instance = new PermissionManager();
        }
        return instance;
    }

    public boolean hasPermission(UserRole role, String permission) {
        switch (role) {
            case ADMIN:
                return true;

            case USER:
                return switch (permission) {
                    case "READ", "VIEW_PROFILE" -> true;
                    default -> false;
                };

            default:
                return false;
        }
    }
}