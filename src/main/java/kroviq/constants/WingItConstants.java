package kroviq.constants;

import kroviq.utils.ActionType;

/**
 * WingIt Airlines — Element locators for the Quick Start demo.
 * Uses local HTML fixtures for deterministic execution (no external dependencies).
 */
public class WingItConstants {

    // Authentication Page
    public static final String TXT_EMAIL = "//input[@id='email']";
    public static final ActionType TXT_EMAIL_ACTION = ActionType.INPUT;

    public static final String TXT_PASSWORD = "//input[@id='password']";
    public static final ActionType TXT_PASSWORD_ACTION = ActionType.INPUT;

    public static final String BTN_LOGIN = "//button[@id='btn-login']";
    public static final ActionType BTN_LOGIN_ACTION = ActionType.CLICK;

    public static final String LBL_WELCOME = "//h1[@id='welcome-message']";
    public static final ActionType LBL_WELCOME_ACTION = ActionType.CLICK;

    // Flight Search
    public static final String TXT_FROM = "//input[@id='from-city']";
    public static final ActionType TXT_FROM_ACTION = ActionType.INPUT;

    public static final String TXT_TO = "//input[@id='to-city']";
    public static final ActionType TXT_TO_ACTION = ActionType.INPUT;

    public static final String BTN_SEARCH = "//button[@id='btn-search']";
    public static final ActionType BTN_SEARCH_ACTION = ActionType.CLICK;

    // Booking
    public static final String TXT_PASSENGER_NAME = "//input[@id='passenger-name']";
    public static final ActionType TXT_PASSENGER_NAME_ACTION = ActionType.INPUT;

    public static final String BTN_CONFIRM = "//button[@id='btn-confirm']";
    public static final ActionType BTN_CONFIRM_ACTION = ActionType.CLICK;

    public static final String LBL_CONFIRMATION = "//div[@id='confirmation-message']";
    public static final ActionType LBL_CONFIRMATION_ACTION = ActionType.CLICK;
}
