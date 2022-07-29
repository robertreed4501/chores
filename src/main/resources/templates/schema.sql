CREATE TABLE assignment
(
    id       BIGINT NOT NULL,
    user_id  BIGINT NOT NULL,
    chore_id BIGINT NOT NULL,
    CONSTRAINT pk_assignment PRIMARY KEY (id)
);

CREATE TABLE chore
(
    id          BIGINT NOT NULL,
    name        VARCHAR(255) NULL,
    chore_level VARCHAR(255) NULL,
    frequency   VARCHAR(255) NULL,
    scope       VARCHAR(255) NULL,
    group_id    BIGINT NULL,
    CONSTRAINT pk_chore PRIMARY KEY (id)
);

CREATE TABLE message
(
    id            BIGINT NOT NULL,
    user_id_to    BIGINT NOT NULL,
    user_id_from  BIGINT NOT NULL,
    subject       VARCHAR(255) NULL,
    body          VARCHAR(255) NULL,
    sent_at       datetime NULL,
    read_at       datetime NULL,
    has_been_read BIT(1) NULL,
    CONSTRAINT pk_message PRIMARY KEY (id)
);

CREATE TABLE receipt
(
    id            BIGINT NOT NULL,
    assignment_id BIGINT NOT NULL,
    timestamp     datetime NULL,
    confirmed     BIT(1) NOT NULL,
    paid          BIT(1) NOT NULL,
    CONSTRAINT pk_receipt PRIMARY KEY (id)
);

CREATE TABLE token
(
    id           BIGINT       NOT NULL,
    token        VARCHAR(255) NOT NULL,
    created_at   datetime     NOT NULL,
    expires_at   datetime     NOT NULL,
    confirmed_at datetime NULL,
    user_id      BIGINT       NOT NULL,
    CONSTRAINT pk_token PRIMARY KEY (id)
);

CREATE TABLE user
(
    id            BIGINT NOT NULL,
    first_name    VARCHAR(255) NULL,
    last_name     VARCHAR(255) NULL,
    email         VARCHAR(255) NULL,
    password      VARCHAR(255) NULL,
    dob           VARCHAR(255) NULL,
    app_user_role VARCHAR(255) NULL,
    `locked`      BIT(1) NULL,
    enabled       BIT(1) NULL,
    group_id      BIGINT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_group
(
    id      BIGINT NOT NULL,
    user_id BIGINT NULL,
    created datetime NULL,
    CONSTRAINT pk_usergroup PRIMARY KEY (id)
);

ALTER TABLE assignment
    ADD CONSTRAINT FK_ASSIGNMENT_ON_CHORE FOREIGN KEY (chore_id) REFERENCES chore (id);

ALTER TABLE assignment
    ADD CONSTRAINT FK_ASSIGNMENT_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE chore
    ADD CONSTRAINT FK_CHORE_ON_GROUP FOREIGN KEY (group_id) REFERENCES user_group (id);

ALTER TABLE message
    ADD CONSTRAINT FK_MESSAGE_ON_USER_ID_FROM FOREIGN KEY (user_id_from) REFERENCES user (id);

ALTER TABLE message
    ADD CONSTRAINT FK_MESSAGE_ON_USER_ID_TO FOREIGN KEY (user_id_to) REFERENCES user (id);

ALTER TABLE receipt
    ADD CONSTRAINT FK_RECEIPT_ON_ASSIGNMENT FOREIGN KEY (assignment_id) REFERENCES assignment (id);

ALTER TABLE token
    ADD CONSTRAINT FK_TOKEN_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE user_group
    ADD CONSTRAINT FK_USERGROUP_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE user
    ADD CONSTRAINT FK_USER_ON_GROUP FOREIGN KEY (group_id) REFERENCES user_group (id);

CREATE VIEW `chores1`.`dashboard`AS (
    SELECT `chores1`.`user`.`first_name`, `user`.`id`, `user`.`group_id`, `chore`.`name`, `chore`.`frequency`,
    CASE WHEN EXISTS (SELECT `receipt`.`id` FROM `chores1`.`receipt` WHERE `receipt`.`assignment_id` = `assignment`.`id`) THEN 'true'
        ELSE 'false' END AS `done`
    FROM `chores1`.`assignment`
    JOIN `chores1`.`user` ON `assignment`.`user_id` = `user`.`id`
    JOIN `chores1`.`chore` ON `assignment`.`chore_id` = `chore`.`id`
    ORDER BY `first_name`, `frequency` DESC);
