package LabManagement.ClassSuport;

import java.util.ArrayList;
import java.util.List;
/** Các ROLE Mặc định của hệ thống */

public class RoleSystem {
    public static String ROLE_ADMIN = "ROLE_ADMIN";
    public static String ROLE_MANAGER = "ROLE_MANAGER";
    public static String ROLE_TEACHER = "ROLE_TEACHER";
    public static String ROLE_TEACHERWAIT = "ROLE_TEACHERWAIT";
    public static String ROLE_RESERVATIONIST = "ROLE_RESERVATIONIST";
    public static String ROLE_TAECHERWAITCANCEL = "ROLE_TAECHERWAITCANCEL";
    public static List<String> ROLE = new ArrayList<String>() {{
        add(ROLE_ADMIN);
        add(ROLE_MANAGER);
        add(ROLE_TEACHER);
        add(ROLE_RESERVATIONIST);
        add(ROLE_TEACHERWAIT);
        add(ROLE_TAECHERWAITCANCEL);
    }};
}
