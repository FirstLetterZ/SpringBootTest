package com.zpf.demo.global;

public enum UserPermission {
    PERMISSION_DELETE(8001, "delete"),
    PERMISSION_EDIT(8002, "edit"),
    PERMISSION_CREATE(8003, "create"),
    PERMISSION_QUERY(8004, "query"),
    PERMISSION_READ(8005, "read");

    private int id;
    private String name;

    UserPermission(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static boolean checkPermission(String p) {
        for (UserPermission per : UserPermission.values()) {
            if (per.getName().equals(p)) {
                return true;
            }
        }
        return false;
    }
}
