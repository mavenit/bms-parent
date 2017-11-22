package com.bms.eai.error.codes;

/**
 * @author kul_sudhakar
 *
 */
public class FrameworkErrorCodes {

	/**
     * Message:
     * Error getting page for entity {0}. Cause: {1}.
     */
    public static final String GET_PAGE_ERROR = "FW001";
    /**
     * Message:
     * Error getting all for entity {0}. Cause: {1}.
     */
    public static final String GET_ALL_ERROR = "FW002";
    /**
     * Message:
     * Error adding for entity {0}. Cause: {1}.
     */
    public static final String ADD_ERROR = "FW003";
    /**
     * Message:
     * Request body is invalid for add {0}. Errors: {1}.
     */
    public static final String ADD_VALIDATION_ERROR = "FW004";
    /**
     * Message:
     * Error adding all for entity {0}. Cause: {1}.
     */
    public static final String ADD_ALL_ERROR = "FW005";
    /**
     * Message:
     * Error getting by ID for entity {0} with ID {1}. Cause: {2}.
     */
    public static final String GET_BY_ID_ERROR = "FW006";
    /**
     * Message:
     * Error getting total count for entity {0}. Cause: {1}.
     */
    public static final String COUNT_ALL_ERROR = "FW007";
    /**
     * Message:
     * Error updating for entity {0} with ID {1}. Cause: {2}.
     */
    public static final String UPDATE_ERROR = "FW008";
    /**
     * Message:
     * Request body is invalid for update {0}. Errors: {1}.
     */
    public static final String UPDATE_VALIDATION_ERROR = "FW009";
    /**
     * Message:
     * Error deleting for entity {0} with ID {1}. Cause: {2}.
     */
    public static final String DELETE_ERROR = "FW010";
    /**
     * Message:
     * Error checking if entity {0} with name {1} exists. Cause: {2}.
     */
    public static final String CHECK_NAME_EXISTS_ERROR = "FW011";
    /**
     * Message:
     * Error getting entity {0} by name {1}. Cause: {2}.
     */
    public static final String GET_BY_NAME_ERROR = "FW012";
    /**
     * Message:
     * Failed to update. Entity {0} with ID {1} not found.
     */
    public static final String UPDATE_ENTITY_NOT_FOUND = "FW013";
    /**
     * Message:
     * Failed to delete. Entity {0} with ID {1} not found.
     */
    public static final String DELETE_ENTITY_NOT_FOUND = "FW014";
    /**
     * Message:
     * Error deleting all for entity {0}. Cause: {1}.
     */
    public static final String DELETE_ALL_ERROR = "FW015";
    /**
     * Message:
     * Failed to update status for entity {0}. Entity with ID {1} not found.
     */
    public static final String UPDATE_STATUS_ENTITY_NOT_FOUND = "FW016";
    /**
     * Message:
     * Error updating status for entity {0} with ID {1}. Cause: {2}.
     */
    public static final String UPDATE_STATUS_ERROR = "FW017";
    /**
     * Message:
     * Error searching for entity {0} with search term {1}. Cause: {2}.
     */
    public static final String SEARCH_ERROR = "FW018";
    /**
     * Message:
     * Search not supported for entity {0}.
     */
    public static final String SEARCH_NOT_SUPPORTED_ERROR = "FW019";
    /**
     * Message:
     * Failed to check whether entity is active. Entity {0} with ID {1} does not exist.
     */
    public static final String CHECK_IS_ACTIVE_ENTITY_NOT_FOUND = "FW020";
    /**
     * Message:
     * Error checking whether entity {0} with ID {1} is active. Cause: {2}.
     */
    public static final String CHECK_IS_ACTIVE_ERROR = "FW021";
    /**
     * Failed to update status for entity {0} with ID {1}. Invalid status: {2}.
     */
    public static final String UPDATE_STATUS_INVALID_STATUS = "FW022";
    /**
     * Message:
     * Error processing template with name {0}. Cause: {1}.
     */
    public static final String PROCESS_TEMPLATE_ERROR = "FW023";
    /**
     * Message:
     * Error creating PDF. Cause: {1}.
     */
    public static final String CREATE_PDF_ERROR = "FW024";
    /**
     * Message:
     * Error finding new tasks of type {0}. Cause: {1}.
     */
    public static final String FIND_NEW_TASK_ERROR = "FW025";
    /**
     * Message:
     * Error updating task of type {0} with ID {1} to processed. Cause: {2}.
     */
    public static final String UPDATE_TASK_TO_PROCESSED_ERROR = "FW026";
    /**
     * Message:
     * Invalid user access. User access record not found for user {1} with session {0}.
     */
    public static final String ACCESS_USER_ACCESS_NOT_FOUND = "FW027";
    /**
     * Message:
     * Failed to logout. User access record not found for user {1} with session {0}.
     */
    public static final String LOGOUT_USER_ACCESS_NOT_FOUND = "FW028";
    /**
     * Message:
     * Failed to process expired session. User access record not found for user {1} with session {0}.
     */
    public static final String SESSION_EXPIRED_USER_ACCESS_NOT_FOUND = "FW029";
    /**
     * Message:
     * Login credentials are invalid.
     */
    public static final String BAD_CREDENTIALS = "FW030";
    /**
     * Message:
     * Error sending email: {0}. Cause: {1}.
     */
    public static final String SEND_EMAIL_ERROR = "FW031";
    /**
     * Message:
     * Error sending email. No SMTP mail server is configured.
     */
    public static final String SMTP_MAIL_SERVER_NOT_SETUP_ERROR = "FW032";
    /**
     * Message:
     * Failed to update task of type {0} with ID {1} to retry. Task not found.
     */
    public static final String UPDATE_TASK_TO_RETRY_TASK_NOT_FOUND = "FW033";
    /**
     * Message:
     * Error updating task of type {0} with ID {1} to retry. Cause: {2}.
     */
    public static final String UPDATE_TASK_TO_RETRY_ERROR = "FW034";
    /**
     * Message:
     * Failed to update task of type {0} with ID {1} to retry exhausted. Task not found.
     */
    public static final String UPDATE_TASK_TO_RETRY_EXHAUSTED_TASK_NOT_FOUND = "FW035";
    /**
     * Message:
     * Error updating task of type {0} with ID {1} to retry exhausted. Cause: {2}.
     */
    public static final String UPDATE_TASK_TO_RETRY_EXHAUSTED_ERROR = "FW036";
    /**
     * Message:
     * Error processing task of type {0} with ID {1}. Cause: {2}.
     */
    public static final String PROCESS_TASK_ERROR = "FW037";
    /**
     * Message:
     * Unhandled Service error occurred. Error: {0}.
     */
    public static final String UNHANDLED_ERROR = "FW999";
    /**
     * Message:
     * Failed to delete email template with ID {0} because the email template is of system type.
     */
    public static final String DELETE_EMAIL_TEMPLATE_IS_SYSTEM = "FW038";
    /**
     * Message:
     * Failed to update status for email template with ID {0} because the email template is of system type.
     */
    public static final String UPDATE_EMAIL_TEMPLATE_STATUS_IS_SYSTEM = "FW039";
    /**
     * Message:
     * Error checking if email template name {0} exists. Cause: {1}.
     */
    public static final String CHECK_EMAIL_TEMPLATE_NAME_EXISTS_ERROR = "FW040";
    /**
     * Message:
     * Failed to create email template. Email template with the same name {0} already exists.
     */
    public static final String ADD_EMAIL_TEMPLATE_ALREADY_EXISTS = "FW041";
    /**
     * Message:
     * Failed to update system email template. The system email template's name {1} cannot be changed.
     */
    public static final String UPDATE_EMAIL_TEMPLATE_CANNOT_CHANGE_SYSTEM_NAME = "FW042";
    /**
     * Message:
     * Failed to update email template. Email template with the same name {0} already exists.
     */
    public static final String UPDATE_EMAIL_TEMPLATE_ALREADY_EXISTS = "FW043";
    /**
     * Message:
     * Error checking if password policy with name {0} exists. Cause: {1}.
     */
    public static final String CHECK_PASSWORD_POLICY_NAME_EXISTS_ERROR = "FW044";
    /**
     * Message:
     * Error searching policy by name {0}. Cause: {1}.
     */
    public static final String SEARCH_POLICY_BY_NAME = "FW045";
    /**
     * Message:
     * Error getting all active password policies. Cause: {0}.
     */
    public static final String GET_ALL_ACTIVE_PASSWORD_POLICY_ERROR = "FW046";
    /**
     * Message:
     * Failed to update status for password policy to INACTIVE. Password policy with ID {0} is already used by users.
     */
    public static final String DEACTIVATE_PASSWORD_POLICY_ERROR_USED = "FW047";
    /**
     * Message:
     * Failed to create password policy. Password policy with the same name {0} already exists.
     */
    public static final String ADD_PASSWORD_POLICY_ALREADY_EXISTS = "FW048";
    /**
     * Message:
     * Failed to update predefined password policy. Predefined password policy {1} cannot be updated.
     */
    public static final String UPDATE_PASSWORD_POLICY_CANNOT_UPDATE_PREDEFINED = "FW049";
    /**
     * Message:
     * Failed to update password policy. Password policy with the same name {0} already exists.
     */
    public static final String UPDATE_PASSWORD_POLICY_ALREADY_EXISTS = "FW050";
    /**
     * Message:
     * {0} with ID {1} cannot be found.
     */
    public static final String ID_DESERIALIZER_NOT_FOUND = "FW051";
    /**
     * Message:
     * Invalid password. The password must be at least {0} characters.
     */
    public static final String INVALID_PASSWORD_MIN_LENGTH = "FW052";
    /**
     * Message:
     * Invalid password. The password must contain at least one upper case character.
     */
    public static final String INVALID_PASSWORD_REQUIRE_UPPER_CASE = "FW053";
    /**
     * Message:
     * Invalid password. The password must contain at least one lower case character.
     */
    public static final String INVALID_PASSWORD_REQUIRE_LOWER_CASE = "FW054";
    /**
     * Message:
     * Invalid password. The password must contain at least one number.
     */
    public static final String INVALID_PASSWORD_REQUIRE_NUMBER = "FW055";
    /**
     * Message:
     * Invalid password. The password must contain at least one special character.
     */
    public static final String INVALID_PASSWORD_REQUIRE_SPECIAL_CHAR = "FW056";
    /**
     * Message:
     * Password policy with ID {0} not found.
     */
    public static final String VALIDATE_PASSWORD_PASSWORD_POLICY_NOT_FOUND = "FW057";
    /**
     * Message:
     * Unable to add entity {0}. ID {1} already exists.
     */
    public static final String ADD_ID_EXISTS = "FW058";
	
}
