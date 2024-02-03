package com.api.taskfy.constants;

public class RegularExpressions {
    public static final String PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=_!]).*$";
    public static final String HEX = "^([0-9A-Fa-f]{3}|[0-9A-Fa-f]{6}|[0-9A-Fa-f]{8})$";
    public static final String UUID = "^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
    public static final String TASK_GROUP_ROLE = "OWNER|MANAGER|NORMAL";
    public static final String TASK_GROUP_STATUS = "PENDING|ACCEPTED|REJECTED";
    public static final String TASK_STATUS = "ON_HOLD|OPEN|CONCLUDED|IN_PROGRESS|CHECKED";
}
