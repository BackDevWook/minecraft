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

CREATE TABLE quests
(
    id                 BIGINT PRIMARY KEY AUTO_INCREMENT,
    name               VARCHAR(30)  NOT NULL,
    quest_type         ENUM ('NORMAL', 'REPEAT', 'STORY', 'ACHIEVEMENT', 'CLASS_CHANGE'),
    scope_type         ENUM ('USER', 'PARTY', 'TOWN'),
    description        VARCHAR(255) NOT NULL,
    is_repeatable      BOOLEAN      NOT NULL DEFAULT FALSE,
    daily_repeat_limit SMALLINT     NOT NULL DEFAULT 1
);

CREATE TABLE quest_rewards
(
    id       BIGINT PRIMARY KEY AUTO_INCREMENT,
    type     ENUM ('MATERIAL', 'RESOURCE'),
    target   VARCHAR(30) NOT NULL,
    value    MEDIUMINT   NOT NULL,
    quest_id BIGINT      NOT NULL,
    CONSTRAINT fk_quest_id_quest_reward FOREIGN KEY (quest_id) REFERENCES quests (id)
);

CREATE TABLE quest_objectives
(
    id             BIGINT PRIMARY KEY AUTO_INCREMENT,
    type           ENUM ('MATERIAL', 'MONSTER') NOT NULL,
    target         VARCHAR(30)                  NOT NULL,
    required_count MEDIUMINT                    NOT NULL,
    quest_id       BIGINT                       NOT NULL,
    CONSTRAINT fk_quest_id_quest_objectives FOREIGN KEY (quest_id) REFERENCES quests (id)
);

CREATE TABLE user_quest_progress
(
    id       BIGINT PRIMARY KEY AUTO_INCREMENT,
    quest_id BIGINT NOT NULL,
    user_id  INT    NOT NULL,
    CONSTRAINT fk_quest_id_quest_progress FOREIGN KEY (quest_id) REFERENCES quests (id),
    CONSTRAINT fk_user_id_quest_progress FOREIGN KEY (user_id) REFERENCES users (id)
);




