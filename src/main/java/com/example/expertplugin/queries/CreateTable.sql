USE expert_plugin;

CREATE TABLE users
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    name     VARCHAR(35)                                  NOT NULL,
    role     ENUM ('USER', 'ADMIN', 'ARCHITECT', 'GUIDE') NOT NULL DEFAULT 'USER',
    gold     INT                                          NOT NULL DEFAULT 0,
    uuid     VARCHAR(35)                                  NOT NULL,
    town_id  INT                                          NULL,
    CONSTRAINT fk_users_town FOREIGN KEY (town_id) REFERENCES towns (id) ON DELETE SET NULL
);

CREATE TABLE users_eco
(
    id      INT PRIMARY KEY AUTO_INCREMENT,
    level   SMALLINT                                                NOT NULL DEFAULT 1,
    exp     INT                                                     NOT NULL DEFAULT 0,
    class   ENUM ('FISHERMAN', 'MINER', 'COOK', 'FARMER', 'NEWBIE') NOT NULL DEFAULT 'NEWBIE',
    user_id INT                                                     NOT NULL,
    CONSTRAINT fk_users_eco_users FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE users_rpg
(
    id      INT PRIMARY KEY AUTO_INCREMENT,
    level   SMALLINT                                                               NOT NULL DEFAULT 1,
    exp     INT                                                                    NOT NULL DEFAULT 0,
    class   ENUM ('NEWBIE', 'WARRIOR', 'ASSASSIN', 'MAGICIAN', 'PRIEST', 'ARCHER') NOT NULL DEFAULT 'NEWBIE',
    user_id INT                                                                    NOT NULL,
    CONSTRAINT fk_users_rpg_users FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE users_play_time
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    created_at      DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    recent_join_at  DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    daily_play_time MEDIUMINT NOT NULL DEFAULT 0,
    user_id         INT       NOT NULL,
    CONSTRAINT fk_users_play_time_users FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE towns
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    name         VARCHAR(12) NOT NULL,
    level        SMALLINT    NOT NULL DEFAULT 1,
    town_point   INT         NOT NULL DEFAULT 0,
    created_at   DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    disbanded_at DATETIME    NULL,
    is_disbanded BOOLEAN     NOT NULL DEFAULT FALSE
);

CREATE TABLE town_members
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    joined_at DATETIME                              NOT NULL DEFAULT CURRENT_TIMESTAMP,
    left_at   DATETIME                              NULL,
    role      ENUM ('MASTER', 'DEPUTY', 'VILLAGER') NOT NULL DEFAULT 'VILLAGER',
    town_id   INT                                NOT NULL,
    user_id   INT                                   NOT NULL,
    CONSTRAINT fk_town_members_towns FOREIGN KEY (town_id) REFERENCES towns (id) ON DELETE CASCADE,
    CONSTRAINT fk_town_members_users FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


CREATE TABLE quests
(
    id                 int PRIMARY KEY AUTO_INCREMENT,
    name               VARCHAR(30)                                                       NOT NULL,
    quest_type         ENUM ('NORMAL', 'REPEAT', 'STORY', 'ACHIEVEMENT', 'CLASS_CHANGE') NOT NULL,
    scope_type         ENUM ('USER', 'PARTY', 'TOWN')                                    NOT NULL,
    description        VARCHAR(255)                                                      NOT NULL,
    is_repeatable      BOOLEAN                                                           NOT NULL DEFAULT FALSE,
    daily_repeat_limit SMALLINT                                                          NOT NULL DEFAULT 1,
    rewards            JSON                                                              NOT NULL,
    missions           JSON                                                              NOT NULL
);

CREATE TABLE user_quest_progresses
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    accepted_at  DATETIME                                       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    completed_at DATETIME                                       NULL,
    given_up_at  DATETIME                                       NULL,
    progress     JSON                                           NULL,
    status       ENUM ('BEFORE_START', 'ON_GOING', 'COMPLETED') NOT NULL DEFAULT 'BEFORE_START',
    user_id      INT                                            NOT NULL,
    quest_id     INT                                            NOT NULL,
    CONSTRAINT fk_user_quest_progresses_users FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_user_quest_progresses_quests FOREIGN KEY (quest_id) REFERENCES quests (id) ON DELETE CASCADE
);

CREATE TABLE town_quest_progresses
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    accepted_at  DATETIME                                       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    completed_at DATETIME                                       NULL,
    given_up_at  DATETIME                                       NULL,
    progress     JSON                                           NULL,
    status       ENUM ('BEFORE_START', 'ON_GOING', 'COMPLETED') NOT NULL DEFAULT 'BEFORE_START',
    town_id      INT                                            NOT NULL,
    quest_id     INT                                            NOT NULL,
    CONSTRAINT fk_town_quest_progresses_towns FOREIGN KEY (town_id) REFERENCES towns (id) ON DELETE CASCADE,
    CONSTRAINT fk_town_quest_progresses_quests FOREIGN KEY (quest_id) REFERENCES quests (id) ON DELETE CASCADE
);



